package com.rabbit.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping({"headers"})
public class HeadersController {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 头模式首页
     * @return
     */
    @RequestMapping("")
    public String headers(){
        return "headers";
    }

    /**
     * 生产消息
     * @param queue 交换机名
     * @param bindType 交换机与队列绑定的key
     * @param message 消息内容
     */
    @ResponseBody
    @RequestMapping("production")
    public void production(String queue,String bindType,String message){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("queue", queue);
        messageProperties.setHeader("bindType", bindType);
        messageProperties.setHeader("name", "wandy");
        Message msg = new Message(message.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("headers", null, msg);
    }

    // 消费者1
    @RabbitListener(queues = "headersQueue-1")
    public void consume1(Message message){
        System.out.println("消费者1："+new String(message.getBody()));
    }

    // 消费者2
    @RabbitListener(queues = "headersQueue-2")
    public void consume2(Message message){
        System.out.println("消费者2："+new String(message.getBody()));
    }

    // 消费者3
    @RabbitListener(queues = "headersQueue-3")
    public void consume3(String msg){
        System.out.println("消费者3："+msg);
    }

}
