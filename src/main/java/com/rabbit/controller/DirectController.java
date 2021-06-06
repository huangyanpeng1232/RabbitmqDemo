package com.rabbit.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.annotation.Resource;

@Controller
@RequestMapping({"direct"})
public class DirectController {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 直接模式首页
     * @return
     */
    @RequestMapping("")
    public String direct(){
        return "direct";
    }

    /**
     * 生产消息
     * @param exchange 交换机名
     * @param routingKey 交换机与队列绑定的key
     * @param message 消息内容
     */
    @ResponseBody
    @RequestMapping("production")
    public void production(String exchange,String routingKey,String message){
        rabbitTemplate.convertAndSend(exchange,routingKey,message);
    }

    // 消费者1
    @RabbitListener(queues = "directQueue")
    public void consume1(Message message){
        System.out.println("消费者1："+new String(message.getBody()));
    }

    // 消费者1
    @RabbitListener(queues = "directQueue")
    public void consume2(Message message){
        System.out.println("消费者2："+new String(message.getBody()));
    }

    // 消费者3
    @RabbitListener(queues = "directQueue")
    public void consume3(String msg){
        System.out.println("消费者3："+msg);
    }

}
