/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.MQConfig;

import com.lalfonso.jsmspring.reader.RestMessageListener;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jms.core.JmsTemplate;


@Configuration
@EnableJms
public class ReaderConfig implements JmsListenerConfigurer {

    @Autowired
    private MQConfig mqConfig;

    @Autowired
    private HashMap<String, DefaultJmsListenerContainerFactory> containerFactories;
    
    @Autowired
    private HashMap<String, JmsTemplate> writerFactory;

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        mqConfig.getReader().forEach((reader) -> {
            System.out.println("Registrando reader: " + reader.getName() + " SERVER: " + reader.getServerName());
            SimpleJmsListenerEndpoint endpoint = new SimpleJmsListenerEndpoint();
            endpoint.setId(reader.getName());
            endpoint.setDestination(reader.getQueue());
            endpoint.setMessageListener(new RestMessageListener(writerFactory));
            System.out.println("containerFactories: " + containerFactories.size());
            registrar.registerEndpoint(endpoint, containerFactories.get(reader.getServerName()));
        });
    }
}
