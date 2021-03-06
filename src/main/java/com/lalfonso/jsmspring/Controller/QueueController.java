/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.Controller;

import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class QueueController {

    @Autowired
    private HashMap<String, JmsTemplate> writerFactory;
    
    @GetMapping("/send")
    public String send() {
        writerFactory.get("default").convertAndSend("Hello", messagePostProcessor -> {
            messagePostProcessor.setStringProperty("type",
                "default");
            return messagePostProcessor;
          });
        return "OK";
    }
}
