package com.lee.activiti.simple.base;

import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;

public class SimpleProcessEngine {
	
	private static ProcessEngine processEngine = null;
	
	static {
		processEngine = ProcessEngines.getDefaultProcessEngine();
	}
	
	public static ProcessEngine getProcessEngine() {
		return processEngine;
	}
	public static RuntimeService getRuntimeService() {
		return processEngine.getRuntimeService();
	}
	public static RepositoryService getRepositoryService() {
		return processEngine.getRepositoryService();
	}
	public static TaskService getTaskService() {
		return processEngine.getTaskService();
	}
	public static ManagementService getManagementService() {
		return processEngine.getManagementService();
	}
	public static IdentityService getIdentityService() {
		return processEngine.getIdentityService();
	}
	public static HistoryService getHistoryService() {
		return processEngine.getHistoryService();
	}
	public static FormService getFormService() {
		return processEngine.getFormService();
	}
}
