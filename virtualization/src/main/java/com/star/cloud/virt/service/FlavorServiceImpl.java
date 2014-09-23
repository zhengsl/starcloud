package com.star.cloud.virt.service;

import org.jclouds.openstack.nova.v2_0.domain.Flavor;
import org.jclouds.openstack.nova.v2_0.features.FlavorApi;

import com.star.cloud.virt.model.StarFlavor;

public class FlavorServiceImpl implements IFlavorService {

	private final FlavorApi flavorApi;
	
	public FlavorServiceImpl(FlavorApi flavorApi) {
		this.flavorApi = flavorApi;
	}

	@Override
	public void addFlavor(StarFlavor starFlavor) {
		Flavor flavor = Flavor.builder()
							.id(starFlavor.getId())
							.name(starFlavor.getName())
							.vcpus(starFlavor.getVcpu())
							.ram(starFlavor.getRam())
							.disk(starFlavor.getDisk())
							.build();
		flavorApi.create(flavor);
	}

	@Override
	public void removeFlavor(String id) {
		flavorApi.delete(id);
	}

	@Override
	public StarFlavor getFlavor(String id) {
		Flavor flavor = flavorApi.get(id);
		return StarFlavor.fromFlavor(flavor);
	}
	
}
