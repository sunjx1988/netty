package netty.protocol.common;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 16:55
 * @Description:
 */
@ChannelHandler.Sharable
public class DefaultChannelInboundHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
