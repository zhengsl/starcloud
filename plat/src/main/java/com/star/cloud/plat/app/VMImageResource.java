package com.star.cloud.plat.app;

import java.util.List;

import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.cloud.virt.model.StarImage;
import com.star.cloud.virt.service.IImageService;
import com.star.cloud.virt.service.VirtualizationServiceBus;

public class VMImageResource extends ServerResource {
	
	private static final Logger log = LoggerFactory.getLogger(VMImageResource.class);
	
	private static final ObjectMapper objMapper = new ObjectMapper();
	
	@Get
	public String getAllImages() {
		try {
			IImageService serv = VirtualizationServiceBus.getImageService();
			List<StarImage> images = serv.getAllImages();
			return objMapper.writeValueAsString(images);
		} catch (Exception e) {
			log.error("To get all Images failed!", e);
			return new ErrorMessage(e.getMessage()).toJsonString();
		}
	}

}
