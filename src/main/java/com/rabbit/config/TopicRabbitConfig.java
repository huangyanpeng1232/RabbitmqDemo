package com.rabbit.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    // topic交换机
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topic",true,true);
    }

    //队列
    @Bean
    public Queue topicQueue1() {
        return new Queue("topicQueue_1");
    }

    //队列
    @Bean
    public Queue topicQueue2() {
        return new Queue("topicQueue_2");
    }

    //队列
    @Bean
    public Queue topicQueue3() {
        return new Queue("topicQueue_3");
    }

    //将对列绑定到topic交换器
    @Bean
    Binding bindingTopicExchange1(Queue topicQueue1,TopicExchange topicExchange) {
        // 只有 routingKey 为 topic.message 时才会分配至topicQueue1
        return BindingBuilder.bind(topicQueue1).to(topicExchange).with("topic.message");
    }
    //将对列绑定到topic交换器
    @Bean
    Binding bindingTopicExchange2_1(Queue topicQueue2, TopicExchange topicExchange) {
        // routingKey 以topic开头且.后面有一个单词才会分配到topicQueue2
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with("topic.*");
    }
    //将对列绑定到topic交换器
    @Bean
    Binding bindingTopicExchange2_2(Queue topicQueue2, TopicExchange topicExchange) {
        // routingKey 包含 topic 且 topic前后 各包含一个单词才会分配到topicQueue2
        return BindingBuilder.bind(topicQueue2).to(topicExchange).with(".*topic.*");
    }
    //将对列绑定到topic交换器
    @Bean
    Binding bindingTopicExchange3(Queue topicQueue3, TopicExchange topicExchange) {
        // routingKey 包含 topic 且 topic后包含0个或多个别的单词时才会分配到topicQueue3
        return BindingBuilder.bind(topicQueue3).to(topicExchange).with("topic.#");
    }

}
