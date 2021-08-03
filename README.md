苏格拉没有底、:
https://github.com/chuondev/reactor

苏格拉没有底、:
https://blog.csdn.net/weixin_44730681/article/details/113728895

苏格拉没有底、:
https://blog.csdn.net/wangwei19871103/article/details/104080859?spm=1001.2014.3001.5501

苏格拉没有底、:
http://www.itxm.cn/post/648.html

苏格拉没有底、:
3.7. TCP 参数优化
常用的 TCP 参数，例如 TCP 层面的接收和发送缓冲区大小设置，在 Netty 中分别对应 ChannelOption 的 SO_SNDBUF 和 SO_RCVBUF，需要根据推送消息的大小，合理设置，对于海量长连接，通常 32K 是个不错的选择

苏格拉没有底、:
.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT)
            .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
            .childOption(ChannelOption.CONNECT_TIMEOUT_MILLIS, 10000)
            .childOption(ChannelOption.SO_TIMEOUT, 10)
            .childOption(ChannelOption.TCP_NODELAY, true)
            .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
//这是我加的keepalive检测
            .childOption(NioChannelOption.of(StandardSocketOptions.SO_KEEPALIVE), true);

苏格拉没有底、:
https://my.oschina.net/u/3457546/blog/4767429

苏格拉没有底、:
https://blog.csdn.net/qq_25805331/article/details/110956104

苏格拉没有底、:
https://blog.csdn.net/linuu/article/details/51509847?spm=1001.2014.3001.5501

苏格拉没有底、:
https://blog.csdn.net/alex_xfboy/article/details/89643638

苏格拉没有底、:
https://blog.csdn.net/m0_45406092/article/details/117921987

苏格拉没有底、:
https://www.cnblogs.com/codderYouzg/p/14741627.html

苏格拉没有底、:
https://lux-sun.blog.csdn.net/article/details/101352493

苏格拉没有底、:
https://www.jianshu.com/p/0fead0912ef3

苏格拉没有底、:
https://blog.csdn.net/lgj123xj/article/details/78577945

苏格拉没有底、:
https://blog.csdn.net/bdmh/article/details/49928197
