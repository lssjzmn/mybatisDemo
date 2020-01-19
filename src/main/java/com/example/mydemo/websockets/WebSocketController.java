package com.example.mydemo.websockets;

import com.example.mydemo.core.bo.WebMessagePayload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public void sendWebMessage(WebMessagePayload msg) {
        if (msg == null) {
            return;
        }
        simpMessagingTemplate.convertAndSend("/topic/serverMsg", msg);
        logger.info("##### sendWebMessage:" + msg.toString());
    }

    @MessageMapping("/frontMsg")
    public void receiveWebMessage(Object msg) {
        logger.info("收到前端发来的消息：" + msg.toString());
    }

}
