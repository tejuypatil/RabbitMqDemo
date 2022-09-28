package com.example.practrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingConfig {
//    public static final String QUEUE1 ="exp_queue";
//    public static final String EXCHANGE1 = "exchanges";
//    public static final String ROUTING_KEY1 = "routingKeys";

    @Bean
    public Queue queue(){
        return new Queue("QUEUE1");
    }

    @Bean
    public TopicExchange exchange(){
        return  new TopicExchange("EXCHANGE1");
    }


    @Bean
    public Binding binding(Queue queue,TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("ROUTING_KEY1");

    }
    @Bean
    public MessageConverter messageConverter (){

        return new  Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
}
