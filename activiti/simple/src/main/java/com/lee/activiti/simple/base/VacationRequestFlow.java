package com.lee.activiti.simple.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VacationRequestFlow {
	
	private static Logger log = LoggerFactory.getLogger(VacationRequestFlow.class);
	
	public void deploy() {
		RepositoryService res = SimpleProcessEngine.getRepositoryService();
		res.createDeployment().addClasspathResource("process/VacationRequest.bpmn20.xml").deploy();
		log.info("Number of process definitions: " + res.createProcessDefinitionQuery().count());
	}
	
	public ProcessInstance start() {
		Map<String, Object> variables = new HashMap<String, Object>();
		variables.put("employeeName", "Kermit");
		variables.put("numberOfDays", new Integer(4));
		variables.put("vacationMotivation", "I'm really tired!");

		RuntimeService runtimeService = SimpleProcessEngine.getRuntimeService();
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("vacationRequest", variables);
		log.info("start processInstance proId="+processInstance.getProcessInstanceId());
		return processInstance;
	}
	
	public void rejectRequest() {
		TaskService taskService = SimpleProcessEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		  log.info("Task available: " + task.getName());
		}
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "false");
		taskVariables.put("managerMotivation", "We have a tight deadline!");
		taskService.complete(task.getId(), taskVariables);
	}
	
	public void approveRequest() {
		TaskService taskService = SimpleProcessEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("management").list();
		for (Task task : tasks) {
		  log.info("Task available: " + task.getName());
		}
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("vacationApproved", "true");
		taskVariables.put("managerMotivation", "have a good day!");
		taskService.complete(task.getId(), taskVariables);
	}
	
	public void adjustRequest() {
		TaskService taskService = SimpleProcessEngine.getTaskService();
		List<Task> tasks = taskService.createTaskQuery().taskAssignee("Kermit").list();
		Task task = tasks.get(0);
		Map<String, Object> taskVariables = new HashMap<String, Object>();
		taskVariables.put("numberOfDays", new Integer(4));
		taskVariables.put("vacationMotivation", "I'm really tired!");
		taskVariables.put("resendRequest", "true");
		taskService.complete(task.getId(), taskVariables);
	}
}
