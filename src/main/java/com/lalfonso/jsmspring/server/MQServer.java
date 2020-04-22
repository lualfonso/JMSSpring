/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.server;

import lombok.Data;

@Data
public class MQServer {
    private String name;
    private String queueManager; 
    private String connName; 
    private String channel; 
    private String user;
    private String password;
    private String concurrency;
            
}
