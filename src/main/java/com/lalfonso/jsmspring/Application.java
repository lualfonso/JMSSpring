/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring;

import com.lalfonso.jsmspring.server.MQServer;
import com.lalfonso.jsmspring.MQConfig.MQConfig;
import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import java.util.HashMap;
import javax.jms.JMSException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

@SpringBootApplication
@EnableJms
public class Application {

    @Autowired
    private MQConfig mqConfig;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public HashMap<String, DefaultJmsListenerContainerFactory> containerFactories() {
        HashMap<String, DefaultJmsListenerContainerFactory> factories = new HashMap<>();
        mqConfig.getServer().forEach((server) -> {
            System.out.println("Registrando container: " + server.getName());
            DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
            factory.setConnectionFactory(connectionFactories().get(server.getName()));
            factory.setConcurrency(server.getConcurrency());
            factories.put(server.getName(), factory);
        });

        return factories;
    }

    @Bean
    public HashMap<String, JmsTemplate> writerFactory() {
        HashMap<String, JmsTemplate> writers = new HashMap<>();
        mqConfig.getWriter().forEach((writer) -> {
            System.out.println("Registrando writer: " + writer.getName() + " server: " + writer.getServerName());
            JmsTemplate factory = new JmsTemplate();
            factory.setConnectionFactory(connectionFactories().get(writer.getServerName()));
            factory.setDefaultDestinationName(writer.getQueue());
            writers.put(writer.getName(), factory);
        });
        System.out.println("Registrado writers: " + writers.size());
        return writers;
    }

    @Bean
    public HashMap<String, MQConnectionFactory> connectionFactories() {
        HashMap<String, MQConnectionFactory> factories = new HashMap<>();
        try {
            for (MQServer server : mqConfig.getServer()) {
                System.out.println("Registrando server: " + server.getName());
                MQConnectionFactory cf = new MQConnectionFactory();
                cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, server.getQueueManager());
                cf.setStringProperty(WMQConstants.WMQ_CONNECTION_NAME_LIST, server.getConnName());
                cf.setStringProperty(WMQConstants.WMQ_CHANNEL, server.getChannel());
                cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
                cf.setStringProperty(WMQConstants.USERID, server.getUser());
                cf.setStringProperty(WMQConstants.PASSWORD, server.getPassword());
                cf.setBooleanProperty(WMQConstants.USER_AUTHENTICATION_MQCSP, false);
                factories.put(server.getName(), cf);
            }
            return factories;
        } catch (JMSException j) {
            throw JmsUtils.convertJmsAccessException(j);
        }
    }

}
