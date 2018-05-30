package com.community.domain.bo.websocket;

import lombok.Data;

import java.io.Serializable;

@Data
public class WsChannelBO implements Serializable {

    private static final long serialVersionUID = 5569636063909935263L;
    private Integer type;
    private String body;


}
