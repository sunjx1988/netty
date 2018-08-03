package netty.protocol.time;

import netty.protocol.common.DefaultClient;

/**
 * @Auther: sunjx
 * @Date: 2018/8/3 0003 16:52
 * @Description: 时间协议客户端
 */
public class TimeClient extends DefaultClient {

    public TimeClient(String host, int port) {
        super(host, port);
    }

    public static void main(String[] args) {
        new TimeClient("localhost", 8000).start(new TimeClientChannelInboundHandler());
    }
}
