package com.star.cloud.virt.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.jclouds.openstack.nova.v2_0.domain.Address;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.domain.ServerCreated;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;
import org.jclouds.openstack.nova.v2_0.options.CreateServerOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.star.cloud.virt.model.StarCloudVirtualizationException;
import com.star.cloud.virt.model.StarServer;

public class ServerServiceImpl implements IServerService {
	
	private static final Logger log = LoggerFactory.getLogger(ServerServiceImpl.class);
	
	private final ServerApi serverApi;

	public ServerServiceImpl(ServerApi serverApi) {
		this.serverApi = serverApi;
	}

	@Override
	public StarServer newServer(String name, String imageId, String flavorId, String networkId) {
		CreateServerOptions options = new CreateServerOptions();
		options.networks(networkId);
		ServerCreated sc = serverApi.create(name, imageId, flavorId, options);
		return getServer(sc.getId());
	}

	@Override
	public void removeServer(String id) {
		serverApi.delete(id);
	}

	@Override
	public void startServer(String id) {
		serverApi.start(id);
	}

	@Override
	public void stopServer(String id) {
		serverApi.stop(id);
	}

	@Override
	public StarServer getServer(String id) {
		Server server = serverApi.get(id);
		StarServer ss = new StarServer();
		ss.setFixedIp("");
		while (server.getStatus().name().equals("BUILD") || server.getStatus().name().equals("ACTIVE")) {
			
			Iterator<Address> addrIter = server.getAddresses().values().iterator();
			if (!addrIter.hasNext()) {
				try {
					Thread.sleep(100);
					server = serverApi.get(id);
				} catch (InterruptedException e) {
					throw new StarCloudVirtualizationException(e);
				}
			} else {
				ss.setFixedIp(addrIter.next().getAddr());
				break;
			}
		}
		ss.setFlavorId(server.getFlavor().getId());
		IFlavorService fservice = VirtualizationServiceBus.getFlavorService();
		ss.setFlavorName(fservice.getFlavor(server.getFlavor().getId()).getName());
		ss.setId(server.getId());
		ss.setName(server.getName());
		ss.setHostName(server.getExtendedAttributes().get().getHostName());
		ss.setStatus(server.getStatus().name());
		
		return ss;
	}

	@Override
	public List<StarServer> getAllServers() {
		List<StarServer> ssList = new LinkedList<StarServer>();
		for (Server s : serverApi.listInDetail().concat()) {
			StarServer ss = new StarServer();
			ss.setId(s.getId());
			ss.setName(s.getName());
			ss.setHostName(s.getExtendedAttributes().get().getHostName());
			ss.setStatus(s.getStatus().name());
			ss.setFlavorId(s.getFlavor().getId());
			ss.setFixedIp("");
			log.debug("To get fixedIP address of " + s.getName());
			
			while (s.getStatus().name().equals("BUILD") || s.getStatus().name().equals("ACTIVE")) {
				Iterator<Address> addrIter = s.getAddresses().values().iterator();
				if (!addrIter.hasNext()) {
					log.debug("Don't get fixIP address of " + s.getName());
					try {
						Thread.sleep(100);
						s = serverApi.get(s.getId());
					} catch (InterruptedException e) {
						throw new StarCloudVirtualizationException(e);
					}
				} else {
					log.debug("Got fixedIP address of " + s.getName());
					ss.setFixedIp(addrIter.next().getAddr());
					break;
				}
			}
			ssList.add(ss);
		}
		return ssList;
	}

}
