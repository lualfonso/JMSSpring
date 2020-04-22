/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.reader;

import java.util.HashMap;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.springframework.jms.core.JmsTemplate;

public class RestMessageListener implements MessageListener {

    private HashMap<String, JmsTemplate> writer;

    public RestMessageListener(HashMap<String, JmsTemplate> writer) {
        super();
        this.writer = writer;
    }

    @Override
    public void onMessage(Message message) {
        System.out.println("Listener using config file:" + message);
        System.out.println("writers :" + writer.size());
      //  writer.get("default").convertAndSend("Hello");
    }

}
