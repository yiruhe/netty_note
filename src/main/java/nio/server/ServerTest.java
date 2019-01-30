package nio.server;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ServerTest {

    public static void main(String[] args) throws  Exception {

        //创建selector
        Selector open = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        serverSocketChannel.configureBlocking(false);

        ServerSocket socket = serverSocketChannel.socket();

        socket.bind(new InetSocketAddress(8899));

        //channel注册到Selector
        serverSocketChannel.register(open, SelectionKey.OP_ACCEPT);


        while(true){

            //几个channel被监听到事件
            int num = open.select();

            //有事件发生的集合  可能存在多个
            Set<SelectionKey> selectionKeys = open.selectedKeys();


            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            while(iterator.hasNext()){

                SelectionKey next = iterator.next();


            }

        }

    }
}
