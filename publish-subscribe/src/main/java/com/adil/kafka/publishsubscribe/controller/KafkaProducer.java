package com.adil.kafka.publishsubscribe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("kafka")
public class KafkaProducer {

	@Autowired
	KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	KafkaTemplate<String, KafkaUser> kafkaTemplateObject;

	private static final String TOPIC = "test";

	@GetMapping("/publish/{message}")
	public String post(@PathVariable("message") final String message) {

		kafkaTemplate.send(TOPIC, message);
		return "Published succesfully";

	}

	@GetMapping("/publish/json/{name}")
	public String postObject(@PathVariable("name") final String name) {
		try {
			kafkaTemplateObject.send("test", new KafkaUser(name, "9151047758"));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "Published succesfully";

	}
}
