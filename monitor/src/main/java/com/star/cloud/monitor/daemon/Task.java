package com.star.cloud.monitor.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Task implements Runnable {
	
	private static final Logger log = LoggerFactory.getLogger(Task.class);
	
	private long id;
	private TaskManager taskManager;
	
	@Override
	public void run() {
		try {
			execute();
		} catch (Exception e) {
			log.error("Daemon Task execution error!", e);
			taskManager.notifyException(id);
		}
	}
	
	public abstract String getTaskName();
	
	public abstract void init();
	
	public abstract void execute() throws Exception;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}

	public TaskManager getTaskManager() {
		return taskManager;
	}

	public void setTaskManager(TaskManager taskManager) {
		this.taskManager = taskManager;
	}
	
}
