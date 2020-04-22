/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.reader;

import lombok.Data;


@Data
public class MQReader {
    private String name;
    private String queue;
    private String serverName;
    private String type;
}
