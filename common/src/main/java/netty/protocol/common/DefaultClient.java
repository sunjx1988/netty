package netty.protocol.common;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 16:35
 * @Description:
 */
public class DefaultClient {

    protected EventLoopGroup group = new NioEventLoopGroup();
    protected Bootstrap bootstrap = new Bootstrap();
    protected String host;
    protected int port;

    public DefaultClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public DefaultClient start(final ChannelInboundHandlerAdapter channelInboundHandler) {
        try {
            bootstrap
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(channelInboundHandler);
                        }
                    });
            System.out.println("客户端已启动...host：" + host + ",port：" + port);
            try {
                ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
                channelFuture.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            group.shutdownGracefully();
        }
        return this;
    }
}
