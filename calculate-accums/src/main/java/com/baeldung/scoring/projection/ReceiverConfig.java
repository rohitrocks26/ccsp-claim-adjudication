package com.baeldung.scoring.projection;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncListenableTaskExecutor;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class ReceiverConfig {

	@Value("${kafka.bootstrap-servers}")
	private String bootstrapServers;

	@Bean
	public Map<String, Object> consumerConfigs() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		props.put(ConsumerConfig.GROUP_ID_CONFIG, "springLeadKafka");

		return props;
	}

	@Bean
	public ConsumerFactory<String, String> consumerFactory() {
		return new DefaultKafkaConsumerFactory<>(consumerConfigs());
	}

	@Bean
	public AsyncListenableTaskExecutor execC() {
		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(15);
		return tpte;
	}

	@Bean
	public AsyncListenableTaskExecutor execL() {
		ThreadPoolTaskExecutor tpte = new ThreadPoolTaskExecutor();
		tpte.setCorePoolSize(15);
		return tpte;
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(consumerFactory());
		//factory.setConcurrency(15);
		//factory.getContainerProperties().setConsumerTaskExecutor(execC());
		//factory.getContainerProperties().setListenerTaskExecutor(execL());
		//factory.getContainerProperties().setPollTimeout(3000);
		return factory;
	}

}