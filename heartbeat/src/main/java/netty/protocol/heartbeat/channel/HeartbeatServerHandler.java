package netty.protocol.heartbeat.channel;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;

/**
 * Created by sunjx on 2018/8/7.
 */
public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {

    private static final ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(
            Unpooled.copiedBuffer("Heartbeat", CharsetUtil.UTF_8));

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent) evt;
            String type = "";
            if(event.state() == IdleState.READER_IDLE){
                type = "读空闲";
            }else if(event.state() == IdleState.WRITER_IDLE){
                type = "写空闲";
            }else if(event.state() == IdleState.ALL_IDLE){
                type = "全空闲";
            }

            ctx.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate())
                    .addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
            System.out.println(ctx.channel().remoteAddress() + ": 空闲类型 => " + type);
        }
    }
}
