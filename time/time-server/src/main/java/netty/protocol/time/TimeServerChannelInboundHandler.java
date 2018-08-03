package netty.protocol.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import netty.protocol.common.DefaultChannelInboundHandler;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 16:06
 * @Description:
 */
public class TimeServerChannelInboundHandler extends DefaultChannelInboundHandler {

    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        final ByteBuf now = ctx.alloc().buffer(4);
        now.writeInt((int) (System.currentTimeMillis() / 1000 + + 2208988800L));
        final ChannelFuture channelFuture = ctx.writeAndFlush(now);
        channelFuture.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                ctx.close();
            }
        });
    }

}
