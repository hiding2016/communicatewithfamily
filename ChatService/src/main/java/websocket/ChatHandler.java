package websocket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import session.ConnectChannnel;

import java.time.LocalDateTime;

/**
 * Created by SoberYang@foxmail.com on 2019/8/29
 * ������Ϣ��handler
 * TextWebSocketFrame �� ��netty��ר��Ϊwebsocket�����ı��Ķ���frame����Ϣ������
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        String requestMsg = msg.text();
        System.out.println("�յ������ݣ�"+requestMsg);

        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame("[���������յ���Ϣ\"]"+ LocalDateTime.now()+"\":"+requestMsg+"  ���ԣ�"+ctx.channel().remoteAddress());
        ConnectChannnel.channels.writeAndFlush(textWebSocketFrame);

    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        ConnectChannnel.channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //������handlerRemoved channelGroup���Զ��Ƴ���Ӧ�Ŀͻ���channel
        ConnectChannnel.channels.remove(ctx.channel());
        System.out.println("�ͻ��˶Ͽ�����Ӧ��ID"+ctx.channel().id().asLongText());
        System.out.println("            ��Ӧ��ID"+ctx.channel().id().asShortText());
    }
}
