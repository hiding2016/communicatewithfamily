package websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import session.ConnectChannnel;

import java.time.LocalDateTime;

/**
 * Created by SoberYang@foxmail.com on 2019/8/29
 * 处理消息的handler
 * TextWebSocketFrame ： 在netty中专门为websocket处理文本的对象，frame是消息的载体
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String requestMsg = msg.text();
        System.out.println("收到的数据："+requestMsg);

        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame("[服务器接收到消息\"]"+ LocalDateTime.now()+"\":"+requestMsg+"  来自："+ctx.channel().remoteAddress());
        ConnectChannnel.channels.writeAndFlush(textWebSocketFrame);

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ConnectChannnel.channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //当触发handlerRemoved channelGroup会自动移除对应的客户端channel
        ConnectChannnel.channels.remove(ctx.channel());
        System.out.println("客户端断开，对应长ID"+ctx.channel().id().asLongText());
        System.out.println("            对应短ID"+ctx.channel().id().asShortText());
    }
}
