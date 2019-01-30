package nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.charset.Charset;
import java.util.Set;

public class NioServer {


    public static void main(String[] args) throws  Exception {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        //获取服务端的socket对象
        ServerSocket socket = serverSocketChannel.socket();

        //将socket对象绑定到8899端口
        socket.bind(new InetSocketAddress(8899));


        Selector selector = Selector.open();

        //将serverSocketChannel注册到selector
        serverSocketChannel.register(selector,SelectionKey.OP_ACCEPT);


        for(;;){
            try{
                //当监听到事件发生就返回  返回事件的数量
                int num = selector.select();

                Set<SelectionKey> selectionKeys = selector.selectedKeys();


                selectionKeys.forEach(selectionKey -> {

                    //客户端和服务器端发生连接
                    if(selectionKey.isAcceptable()){

                        //获取通道  我们将ServerSocketChannel注册到selector上,所以selectionKey对应的channel就是ServerSocketChannel
                        ServerSocketChannel server = (ServerSocketChannel)selectionKey.channel();


                        try {
                            //接受连接
                            SocketChannel accept = server.accept();
                            accept.configureBlocking(false);
                            //将这个SocketChannel祖册到selector上面  监听读事件
                            accept.register(selector,SelectionKey.OP_READ);




                        } catch (IOException e) {
                            e.printStackTrace();
                        }



                    }else if (selectionKey.isReadable()){
                        //注册到读事件的对象是SocketChannel,所以直接强转
                        SocketChannel channel = (SocketChannel)selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        try {
                            int read = channel.read(byteBuffer);

                            if(read > 0){

                                //写模式
                                byteBuffer.flip();

                                Charset charset = Charset.forName("UTF-8");

                                String s = String.valueOf(charset.decode(byteBuffer).array());


                                System.out.println("接受的消息:--->"+s);


                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                    //处理完后清空,不然会再次进行处理
                    selectionKeys.clear();



                });

            }catch (Exception e){

                e.printStackTrace();

            }
        }



    }
}
