package com.rabbit.controller;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
@RequestMapping("fanout")
public class FanoutController {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * 广播模式首页
     * @return
     */
    @RequestMapping("")
    public String fanout(){
        return "fanout";
    }

    /**
     * 生产消息
     * @param exchange 交换机名
     * @param message 消息内容
     */
    @ResponseBody
    @RequestMapping("production")
    public void production(String exchange,String message){
        rabbitTemplate.convertAndSend(exchange,message);
    }

    // 消费者1
    @RabbitListener(queues = "fanoutQueue_1")
    public void consume1(Message message){
        System.out.println("消费者1："+new String(message.getBody()));
    }

    // 消费者2
    @RabbitListener(queues = "fanoutQueue_2")
    public void consume2(String msg){
        System.out.println("消费者2："+msg);
    }

    // 消费者3_1
    @RabbitListener(queues = "fanoutQueue_3")
    public void consume3_1(String msg){
        System.out.println("消费者3-1："+msg);
    }

    // 消费者3_2
    @RabbitListener(queues = "fanoutQueue_3")
    public void consumeNo3_2(String msg){
        System.out.println("消费者3-2："+msg);
    }

    // 消费者3_3
    @RabbitListener(queues = "fanoutQueue_3")
    public void consumeNo3_3(String msg){
        System.out.println("消费者3-3："+msg);
    }

}
