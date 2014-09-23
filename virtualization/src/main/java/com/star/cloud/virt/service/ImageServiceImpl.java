package com.star.cloud.virt.service;

import java.util.LinkedList;
import java.util.List;

import org.jclouds.openstack.nova.v2_0.domain.Image;
import org.jclouds.openstack.nova.v2_0.features.ImageApi;

import com.star.cloud.virt.model.StarImage;

public class ImageServiceImpl implements IImageService {

	private final ImageApi imageApi;
	
	public ImageServiceImpl(ImageApi imageApi) {
		this.imageApi = imageApi;
	}

	@Override
	public List<StarImage> getAllImages() {
		List<StarImage> images = new LinkedList<StarImage>();
		for (Image img : imageApi.listInDetail().concat()) {
			StarImage simg = new StarImage();
			simg.setImageId(img.getId());
			simg.setImageName(img.getName());
			images.add(simg);
		}
		return images;
	}

}
