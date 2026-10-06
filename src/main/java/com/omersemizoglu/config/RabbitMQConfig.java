package com.omersemizoglu.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	public static final String SALE_QUEUE = "sale-queue";

	@Bean
	public Queue saleQueue() {
		return new Queue(SALE_QUEUE, true);
	}
}
