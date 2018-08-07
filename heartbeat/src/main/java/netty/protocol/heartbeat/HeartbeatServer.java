package netty.protocol.heartbeat;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import netty.protocol.heartbeat.channel.HeartbeatHandlerInitializer;

/**
 * Created by sunjx on 2018/8/7.
 */
public class HeartbeatServer{

    private static final int PORT = 8000;

    public static void main(String[] args) {
        EventLoopGroup serverGrop = new NioEventLoopGroup();
        EventLoopGroup workerGrop = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        try {
            serverBootstrap.group(serverGrop, workerGrop)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new HeartbeatHandlerInitializer());

            ChannelFuture channelFuture = serverBootstrap.bind(PORT).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workerGrop.shutdownGracefully();
            serverGrop.shutdownGracefully();
        }

    }
}
