package com.lee.activiti.simple.base;

public class ProcessMain {
	
	public static void main(String[] args) throws InterruptedException {
		VacationRequestFlow flow = new VacationRequestFlow();
//		flow.deploy();
//		flow.start();
		flow.approveRequest();
//		Thread.sleep(60000);
	}
	
}
