/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lalfonso.jsmspring.MQConfig;

import com.lalfonso.jsmspring.server.MQServer;
import com.lalfonso.jsmspring.writer.MQWriter;
import com.lalfonso.jsmspring.reader.MQReader;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "mq")
@Data
public class MQConfig {

    private List<MQServer> server;
    private List<MQReader> reader;
    private List<MQWriter> writer;
}
