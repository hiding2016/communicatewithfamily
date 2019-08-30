package hello;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Created by sober_yang@foxmail.com on 2019/8/29
 */
public class HelloHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {


        System.out.println("\n\n\n ’µΩ£∫"+msg);
        if (!(msg instanceof HttpRequest)){
            ctx.close();
        }
        Channel channel = ctx.channel();


        System.out.println(channel.remoteAddress());

        ByteBuf byteBuf = Unpooled.copiedBuffer("hello netty", CharsetUtil.UTF_8);

        FullHttpResponse res = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);

        System.out.println("ªÿ∏¥£∫"+res);
        channel.writeAndFlush(res);
    }
}
