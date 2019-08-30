package session;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by sober_yang@foxmail.com on 2019/8/29
 */
public class ConnectChannnel {
    //用于记录和管理所有客户端的channel
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
