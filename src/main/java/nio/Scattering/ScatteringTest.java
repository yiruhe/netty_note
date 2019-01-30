package nio.Scattering;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class ScatteringTest {


    public static void main(String[] args) throws Exception {

        //新建NIO通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);

        //创建基于NIO通道的socket连接
        serverSocketChannel.socket().bind(inetSocketAddress);

        int msgLength = 2+3+4;
        ByteBuffer[] byteBuffers = new ByteBuffer[3];

        byteBuffers[0] = ByteBuffer.allocate(2);
        byteBuffers[1] = ByteBuffer.allocate(3);
        byteBuffers[2] = ByteBuffer.allocate(4);

        SocketChannel accept = serverSocketChannel.accept();

        while(true){

            int bytesRead = 0;

            while ( bytesRead < msgLength) {

                long l = accept.read(byteBuffers);
                bytesRead+=l;

            }


            Arrays.asList(byteBuffers).forEach((s) -> {
                s.flip();
            });


            int bytesWritten = 0;
            while(bytesWritten < msgLength){

                long l = accept.write(byteBuffers);

                bytesWritten += l;
            }

        }

    }
}
