package com.adil.kafka.publishsubscribe.configuration;

import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.adil.kafka.publishsubscribe.controller.KafkaUser;

@Configuration
@EnableKafka
public class SubscribeConfiguration {

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		// list of host:port pairs used for establishing the initial connections to the
		// Kafka cluster
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		// allows a pool of processes to divide the work of consuming and processing
		// records
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "testgroup");
		props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, "100");

	//	props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

		// automatically reset the offset to the earliest offset
	//	props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		return props;
	}
	
	@Bean
	public ConsumerFactory<String, KafkaUser> consumerObjectConfigs() {
		Map<String, Object> props = new HashMap<>();
		// list of host:port pairs used for establishing the initial connections to the
		// Kafka cluster
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		// allows a pool of processes to divide the work of consuming and processing
		// records
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "objectgroup");
		// automatically reset the offset to the earliest offset
		props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

		JsonDeserializer<KafkaUser> jsonDeserializer = new JsonDeserializer<>(KafkaUser.class);
		DefaultKafkaConsumerFactory<String, KafkaUser> consumerFactory =
		          new DefaultKafkaConsumerFactory<>(props);
	    consumerFactory.setValueDeserializer(new JsonDeserializer<>(KafkaUser.class));
	    return consumerFactory;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());

		return factory;
	}
	
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, KafkaUser> userKafkaListenerFactory()
	{
		ConcurrentKafkaListenerContainerFactory<String, KafkaUser> factory=new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerObjectConfigs());
		return factory;
	}

}
