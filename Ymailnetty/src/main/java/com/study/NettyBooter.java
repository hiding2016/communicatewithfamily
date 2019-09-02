package com.study;

import com.study.netty.WsServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class NettyBooter implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //启动netty服务
        if (contextRefreshedEvent.getApplicationContext().getParent() == null){
            WsServer.getInstance().start();
        }
    }
}
