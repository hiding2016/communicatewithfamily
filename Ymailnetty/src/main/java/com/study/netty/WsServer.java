package com.study.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.util.internal.ConstantTimeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by sober_yang@foxmail.com on 2019/8/29
 */
@Component
public class WsServer {
    private static class Inner {
        private static final WsServer instance = new WsServer();
    }

    public static final WsServer getInstance() {
        return Inner.instance;
    }

    private EventLoopGroup mainGroup;
    private EventLoopGroup subGroup;
    private ServerBootstrap serverBootstrap;
    private ChannelFuture channelFuture;

    private WsServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        pipeline.addLast(new HttpServerCodec());

                        //对大数据流的支持 that adds support for writing a large data stream
                        pipeline.addLast(new ChunkedWriteHandler());

                        //It is useful when you don't want to take care of HTTP messages whose transfer encoding is 'chunked'.
                        //对httpmesg进行聚合，聚合成FullHttpRequest 或者 FullHttpResponse!!!很常用
                        pipeline.addLast(new HttpObjectAggregator(1024 * 64));

                        //服务器针对websocket进行处理协议，用于指定客户端连接访问的路由：（/ws）
                        //This handler does all the heavy lifting for you to run a websocket server.并处理握手动作和文本、二进制数据等的传输
                        pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

                        pipeline.addLast(new ChatHandler());
                    }
                });

    }

    public void start(){
        this.channelFuture = this.serverBootstrap.bind(8088);
        System.err.println(new Date()+" netty start success： ["+8088+"]");
    }

   public void stop(){
       this.mainGroup.shutdownGracefully();
       this.subGroup.shutdownGracefully();
   }
}
