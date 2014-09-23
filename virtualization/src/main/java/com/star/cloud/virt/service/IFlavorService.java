package com.star.cloud.virt.service;

import com.star.cloud.virt.model.StarFlavor;

public interface IFlavorService {
	
	void addFlavor(StarFlavor starFlavor);
	
	void removeFlavor(String id);
	
	StarFlavor getFlavor(String id);

}
