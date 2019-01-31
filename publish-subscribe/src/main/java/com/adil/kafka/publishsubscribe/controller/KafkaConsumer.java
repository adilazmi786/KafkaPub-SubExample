package com.adil.kafka.publishsubscribe.controller;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

	@KafkaListener(topics="ADD_YOUR_TOPIC_HERE",groupId="testgroup")
	public void consume(String message)
	{
		System.out.println("consumed message:- "+message);
	}
	
	@KafkaListener(topics="ADD_YOUR_TOPIC_HERE",groupId="objectgroup",containerFactory="userKafkaListenerFactory")
	public void consumeJson(KafkaUser kafkaUser)
	{
		System.out.println("consumed json message:- "+kafkaUser);
	}
}
