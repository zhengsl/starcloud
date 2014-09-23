package com.star.cloud.monitor.util;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.star.cloud.monitor.model.Metadata;
import com.star.cloud.monitor.model.MetadataException;

public class RedisMetadataHelper {
	
	private final JedisPool jedisPool;
	
	public RedisMetadataHelper(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
	
	public List<String> getList(Metadata metadata) {
		if (!metadata.getValueType().equals(Metadata.ValueType.LIST)) {
			throw new MetadataException("Illegal type:" + metadata.getValueType() 
					+ ", 'list' expected.", metadata);
		}
		Jedis jClient = jedisPool.getResource();
		long len = jClient.llen(metadata.getKey());
		List<String> result = jClient.lrange(metadata.getKey(), 0, len-1);
		jedisPool.returnResource(jClient);
		return result;
	}
	
	public String getSingle(Metadata metadata) {
		if (!metadata.getValueType().equals(Metadata.ValueType.SINGLE)) {
			throw new MetadataException("Illegal type:" + metadata.getValueType() 
					+ ", 'single' expected.", metadata);
		}
		Jedis jClient = jedisPool.getResource();
		String result = jClient.get(metadata.getKey());
		jedisPool.returnResource(jClient);
		return result;
	}

}
