package com.star.cloud.monitor.daemon;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaskManager {
	
	private static final Logger log = LoggerFactory.getLogger(TaskManager.class);
	
	private final Map<Long, Task> taskMap = new HashMap<Long, Task>();
	private final Map<Long, Integer> threadCountMap = new HashMap<Long, Integer>();
	
	private final LinkedList<Long> failedTask = new LinkedList<Long>();
	
	public long registerTask(Task task) {
		return registerTask(task, 1);
	}
	
	public long registerTask(Task task, int threadCount) {
		long id = generateId();
		task.setId(id);
		task.setTaskManager(this);
		task.init();
		taskMap.put(id, task);
		threadCountMap.put(id, threadCount);
		return id;
	}
	
	public void unregisterTask(long id) {
		taskMap.remove(id);
		threadCountMap.remove(id);
	}
	
	public void startAllTask() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Set<Long> taskIds = taskMap.keySet();
		for (Long tid : taskIds) {
			Task task = taskMap.get(tid);
			int n = threadCountMap.get(tid);
			for (int i = 0; i < n; ++i) {
				Thread t = new Thread(task);
				t.start();
			}
		}
		new TaskMaintainThread().start();
	}
	
	public void startTask(Long tid) {
		Task task = taskMap.get(tid);
		Thread t = new Thread(task);
		t.start();
	}
	
	public void notifyException(long tid) {
		synchronized (failedTask) {
			log.info("Task " + tid + " failed, ready to restart.");
			failedTask.add(tid);
		}
	}
	
	private long generateId() {
		return UUID.randomUUID().getLeastSignificantBits();
	}
	
	private class TaskMaintainThread extends Thread {
		
		public void run() {
			while (true) {
				synchronized (failedTask) {
					while (!failedTask.isEmpty()) {
						Task task = taskMap.get(failedTask.poll());
						log.info("Task " + task.getTaskName() + ":" + task.getId() + " is going to restart.");
						new Thread(task).start();
					}
				}
				try {
					Thread.sleep(100000);
				} catch (InterruptedException e) {
					// TODO 
				}
			}
		}
	}

}
