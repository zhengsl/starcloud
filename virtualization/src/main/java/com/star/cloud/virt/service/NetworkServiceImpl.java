package com.star.cloud.virt.service;

import java.util.LinkedList;
import java.util.List;

import org.jclouds.openstack.neutron.v2_0.domain.Network;
import org.jclouds.openstack.neutron.v2_0.features.NetworkApi;

import com.star.cloud.virt.model.StarNetwork;

public class NetworkServiceImpl implements INetworkService {
	
	private final NetworkApi networkApi;

	public NetworkServiceImpl(NetworkApi networkApi) {
		this.networkApi = networkApi;
	}

	@Override
	public List<StarNetwork> getAllNetworks() {
		List<StarNetwork> networks = new LinkedList<StarNetwork>();
		for (Network nw : networkApi.listInDetail().concat()) {
			StarNetwork sn = new StarNetwork();
			sn.setId(nw.getId());
			sn.setName(nw.getName());
			networks.add(sn);
		}
		return networks;
	}

	@Override
	public StarNetwork getNetwork(String id) {
		Network n = networkApi.get(id);
		StarNetwork sn = new StarNetwork();
		sn.setId(n.getId());
		sn.setName(n.getName());
		return sn;
	}

}
