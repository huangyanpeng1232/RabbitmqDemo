package com.rabbit.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectRabbitConfig {

    // Direct交换机
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct",true,true);
    }

    //队列
    @Bean
    public Queue directQueue() {
        return new Queue("directQueue");
    }

    //绑定
    @Bean
    Binding bindingDirect() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("medicineQueue");
    }

}
