package com.star.cloud.plat.dao;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.star.cloud.plat.model.StarCloudPlatException;

public class PlatDaoBus {
	
	private static final Log log = LogFactory.getLog(PlatDaoBus.class);
	
	private static IMachineDao machineDao;
	
	private static IClusterDao clusterDao;
	
	private static IVMDao vmDao;
	
	private static IIcingaDao icingaDao;
	
	public static void init() {
		InputStream is1 = null;
		InputStream is2 = null;
		try {
			is1 = Resources.getResourceAsStream("mybatis-config1.xml");
			SqlSessionFactory ssFactory1 = new SqlSessionFactoryBuilder().build(is1);
			machineDao = new MachineDaoImpl(ssFactory1);
			clusterDao = new ClusterDaoImpl(ssFactory1);
			vmDao = new VMDaoImpl(ssFactory1);
			
			is2 = Resources.getResourceAsStream("mybatis-config2.xml");
			SqlSessionFactory ssFactory2 = new SqlSessionFactoryBuilder().build(is2);
			icingaDao = new IcingaDaoImpl(ssFactory2);
		} catch (IOException e) {
			log.error("", e);
			throw new StarCloudPlatException(e);
		} finally {
			try {
				is1.close();
				is2.close();
			} catch (IOException e) {
				log.error("Problems happened when closing mybatis configure file reader.", e);
				throw new StarCloudPlatException(e);
			}
		}
	}
	
	public static IMachineDao getMachineDao() {
		return machineDao;
	}
	
	public static IClusterDao getClusterDao() {
		return clusterDao;
	}

	public static IVMDao getVMDao() {
		return vmDao;
	}

	public static IIcingaDao getIcingaDao() {
		return icingaDao;
	}

}
