package netty.protocol.time;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import netty.protocol.common.DefaultChannelInboundHandler;

import java.util.Date;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 16:57
 * @Description:
 */
public class TimeClientChannelInboundHandler extends DefaultChannelInboundHandler {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf m = (ByteBuf) msg;
        try {
            long currentTimeMillis = (m.readUnsignedInt() - 2208988800L) * 1000L;
            System.out.println(new Date(currentTimeMillis));
            ctx.close();
        } finally {
            m.release();
        }
    }
}
