package websocket;

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

/**
 * Created by SoberYang@foxmail.com on 2019/8/29
 */
public class WsServer  {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        EventLoopGroup subGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(mainGroup, subGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            pipeline.addLast(new HttpServerCodec());
                            //�Դ���������֧�� that adds support for writing a large data stream
                            pipeline.addLast(new ChunkedWriteHandler());
                            //It is useful when you don't want to take care of HTTP messages whose transfer encoding is 'chunked'.
                            //��httpmesg���оۺϣ��ۺϳ�FullHttpRequest ���� FullHttpResponse!!!�ܳ���
                            pipeline.addLast(new HttpObjectAggregator(1024 * 64));

                            //���������websocket���д���Э�飬����ָ���ͻ������ӷ��ʵ�·�ɣ���/ws��
                            //This handler does all the heavy lifting for you to run a websocket server.���������ֶ������ı������������ݵȵĴ���
                            pipeline.addLast(new WebSocketServerProtocolHandler("/ws"));

                            pipeline.addLast(null);
                        }
                    });
            int port = 8081;
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }
    }
}
