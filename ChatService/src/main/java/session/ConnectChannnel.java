package session;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * Created by sober_yang@foxmail.com on 2019/8/29
 */
public class ConnectChannnel {
    //���ڼ�¼�͹������пͻ��˵�channel
    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
}
