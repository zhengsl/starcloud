package com.star.cloud.plat.util;

import java.util.UUID;

import com.star.cloud.plat.model.StarCloudPlatException;

public class IDUtil {
	
	public static String generateID() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}
	
	public static void checkIdAndName(long id, String name) {
		if (id <= 0 && (name == null || name.isEmpty())) {
			throw new StarCloudPlatException("Illegal argument as ID and name: " + id + "+" + name);
		}
	}
	
	public static void checkIdAndName(String id, String name) {
		if ((id == null || id.isEmpty()) && (name == null || name.isEmpty())) {
			throw new StarCloudPlatException("Illegal argument as ID and name: " + id + "+" + name);
		}
	}
	
	public static void checkId(long id) {
		if (id <= 0) {
			throw new StarCloudPlatException("Illegal argument as ID: " + id);
		}
	}
	
	public static void checkId(String id) {
		if (id == null || id.isEmpty()) {
			throw new StarCloudPlatException("Illegal argument as ID: " + id);
		}
	}
	
	public static boolean exists(String str) {
		return str != null && !str.isEmpty();
	}

}
