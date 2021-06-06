package com.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class HeadersRabbitConfig {

    // Headers交换机
    @Bean
    HeadersExchange headersExchange() {
        return new HeadersExchange("headers",true,true);
    }

    //队列
    @Bean
    public Queue headersQueue1() {
        return new Queue("headersQueue-1");
    }

    //队列
    @Bean
    public Queue headersQueue2() {
        return new Queue("headersQueue-2");
    }

    //队列
    @Bean
    public Queue headersQueue3() {
        return new Queue("headersQueue-3");
    }

    //将对列绑定到Headers交换器
    @Bean
    Binding bindingHeadersExchange() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue1");
        // whereAll 标识所有头信息中，必须全部匹配
        header.put("bindType", "whereAll");
        return BindingBuilder.bind(headersQueue1()).to(headersExchange()).whereAll(header).match();
    }
    //将对列绑定到Headers交换器
    @Bean
    Binding bindingHeadersExchange2() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue2");
        // whereAny 表示所有头信息中，只要匹配一个就可以
        header.put("bindType", "whereAny");
        return BindingBuilder.bind(headersQueue2()).to(headersExchange()).whereAll(header).match();
    }
    //将对列绑定到Headers交换器
    @Bean
    Binding bindingHeadersExchange3() {
        HashMap<String, Object> header = new HashMap<>();
        header.put("queue", "queue");
        // whereAny 表示所有头信息中，只要匹配一个就可以
        header.put("bindType", "whereAny");
        return BindingBuilder.bind(headersQueue3()).to(headersExchange()).whereAll(header).match();
    }

}
