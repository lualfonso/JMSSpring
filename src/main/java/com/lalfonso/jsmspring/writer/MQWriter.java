/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.writer;

import lombok.Data;


@Data
public class MQWriter {
    private String name;
    private String serverName;
    private String queue;
}
