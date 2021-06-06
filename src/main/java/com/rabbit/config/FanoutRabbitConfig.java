package com.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutRabbitConfig {

    // Fanout交换机
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout",true,true);
    }

    //队列
    @Bean
    public Queue fanoutQueue1() {
        return new Queue("fanoutQueue_1");
    }

    //队列
    @Bean
    public Queue fanoutQueue2() {
        return new Queue("fanoutQueue_2");
    }

    //队列
    @Bean
    public Queue fanoutQueue3() {
        return new Queue("fanoutQueue_3");
    }

    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingFanoutExchange1(Queue fanoutQueue1,FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }
    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingFanoutExchange2(Queue fanoutQueue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
    //将对列绑定到Fanout交换器
    @Bean
    Binding bindingFanoutExchange3(Queue fanoutQueue3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue3).to(fanoutExchange);
    }

}
