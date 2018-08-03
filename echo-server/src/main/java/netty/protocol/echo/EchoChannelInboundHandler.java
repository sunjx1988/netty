package netty.protocol.echo;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.CharsetUtil;
import netty.protocol.common.DefaultChannelInboundHandler;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 14:53
 * @Description:
 */
public class EchoChannelInboundHandler extends DefaultChannelInboundHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf in = (ByteBuf) msg;
        try {
            System.out.println(in.toString(CharsetUtil.US_ASCII));
        } finally {
            in.release();
        }
    }

}
