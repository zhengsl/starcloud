package com.star.cloud;
import java.io.Closeable;
import java.io.IOException;
import java.util.Set;

import org.jclouds.ContextBuilder;
import org.jclouds.logging.slf4j.config.SLF4JLoggingModule;
import org.jclouds.openstack.keystone.v2_0.KeystoneApi;
import org.jclouds.openstack.keystone.v2_0.features.TokenApi;
import org.jclouds.openstack.nova.v2_0.NovaApi;
import org.jclouds.openstack.nova.v2_0.domain.Host;
import org.jclouds.openstack.nova.v2_0.domain.HostResourceUsage;
import org.jclouds.openstack.nova.v2_0.domain.Server;
import org.jclouds.openstack.nova.v2_0.domain.ServerExtendedAttributes;
import org.jclouds.openstack.nova.v2_0.extensions.HostAdministrationApi;
import org.jclouds.openstack.nova.v2_0.features.ServerApi;

import com.google.common.collect.ImmutableSet;
import com.google.common.io.Closeables;
import com.google.inject.Module;

public class JCloudsNova implements Closeable {
	private ContextBuilder cb;
    private NovaApi novaApi;
    private KeystoneApi keystoneApi;
    private Set<String> zones;

    public static void main(String[] args) throws IOException {
        JCloudsNova jcloudsNova = new JCloudsNova();

        try {
        	jcloudsNova.listServers();
            //jcloudsNova.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jcloudsNova.close();
        }
    }

    public JCloudsNova() {
        Iterable<Module> modules = ImmutableSet.<Module>of(new SLF4JLoggingModule());

        String provider = "openstack-nova";
        String identity = "demo:admin"; // tenantName:userName
        String credential = "admin";

        cb = ContextBuilder.newBuilder(provider)
                .endpoint("http://10.0.63.232:5000/v2.0/")
                .credentials(identity, credential)
                .modules(modules);
        //keystoneApi = cb.buildApi(KeystoneApi.class);
        novaApi = cb.buildApi(NovaApi.class);
        //zones = novaApi.getConfiguredZones();
    }
    
    private void updateAuth() {
    	TokenApi tokenApi = null;
    	if (keystoneApi.getTokenApi().isPresent()) {
    		tokenApi = keystoneApi.getTokenApi().get();
    	}
    	
    	
    }

    private void listServers() {
        //for (String zone : zones) {
    	String zone = "regionOne";
    	ServerApi serverApi = novaApi.getServerApiForZone(zone);
//    	FloatingIPApi floatingIPApi = novaApi.getFloatingIPExtensionForZone(zone).get();
//    	for (FloatingIP fip : floatingIPApi.list()) {
//    		System.out.println("IP: " + fip.getFixedIp() + " " + fip.getIp());
//    	}
    	
        for (Server server : serverApi.listInDetail().concat()) {
            //System.out.println("*********" + server);
        	System.out.println(server.getHostId());
        	ServerExtendedAttributes exAttr = server.getExtendedAttributes().get();
        	System.out.println("*********" + exAttr.getHostName());
        }
//    	FlavorApi flavorApi = novaApi.getFlavorApiForZone(zone);
//    	for (Flavor flavor : flavorApi.listInDetail().concat()) {
//    		System.out.println("*********" + flavor);
//    		
//    	}
    	
//    	ImageApi imageApi = novaApi.getImageApiForZone(zone);
//    	for (Image image : imageApi.listInDetail().concat()) {
//    		System.out.println("*********" + image);
//    	}
    	HostAdministrationApi hApi = novaApi.getHostAdministrationExtensionForZone(zone).get();
    	for (Host host : hApi.list()) {
    		System.out.println("*********" + host);
    		for (HostResourceUsage hru : hApi.listResourceUsage(host.getName())) {
    			System.out.println("*****************" + hru);
    		}
    	}
        //}
    }

    public void close() throws IOException {
        Closeables.close(novaApi, true);
    }
}

