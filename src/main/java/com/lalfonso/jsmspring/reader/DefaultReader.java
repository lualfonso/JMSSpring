/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.reader;


import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class DefaultReader {

  @JmsListener(destination = "DEV.QUEUE.1", selector="type = 'default'")
  public void receiveMessage(String message) {
    System.out.println("Received <" + message + ">");
  }

}