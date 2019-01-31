package com.adil.kafka.publishsubscribe.controller;

public class KafkaUser {
	
	private String name;
	private String number;
	
	
	public KafkaUser(String name, String number) {
		this.name = name;
		this.number = number;
	}
	public KafkaUser() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	

}
