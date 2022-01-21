package com.snowalker.shield.match.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author snowalker
 * @version 1.0
 * @date 2022/1/21 11:29
 * @className
 * @desc
 */
public class HelloNettyServer {


    private int port;

    public HelloNettyServer(int port) {
        this.port = port;
    }

    /**
     * 事件循环方法核心逻辑
     * @throws Exception
     */
    public void run() throws Exception {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {

            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new HelloNettyServerHandler());
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, Boolean.TRUE);
            ChannelFuture future = bootstrap.bind(port).sync();
            future.channel().closeFuture().sync();

        } finally {

            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();

        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("开始建立Netty服务器连接.....");
        int port = 8080;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new HelloNettyServer(port).run();
    }
}
