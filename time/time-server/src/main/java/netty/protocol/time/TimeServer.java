package netty.protocol.time;

import netty.protocol.common.DefaultServer;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 15:47
 * @Description: 时间协议服务器
 */
public class TimeServer extends DefaultServer {

    public TimeServer(){}

    public TimeServer(int port) { this.port = port;}

    public static void main(String[] args) {
        new TimeServer().start(new TimeServerChannelInboundHandler());
    }
}
