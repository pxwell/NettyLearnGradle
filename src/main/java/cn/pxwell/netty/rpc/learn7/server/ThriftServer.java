package cn.pxwell.netty.rpc.learn7.server;

import cn.pxwell.netty.rpc.learn7.thrift.PersonServiceImpl;
import cn.pxwell.netty.rpc.learn7.thrift.generated.PersonService;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFastFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

public class ThriftServer {

    public static void main(String[] args) throws TTransportException {
        TNonblockingServerSocket serverSocket = new TNonblockingServerSocket( 8899 );

        THsHaServer.Args arg = new THsHaServer.Args( serverSocket ).minWorkerThreads(2).maxWorkerThreads( 4 );

        PersonService.Processor<PersonServiceImpl> personServiceProcessor = new PersonService.Processor<>( new PersonServiceImpl() );

        arg.protocolFactory( new TCompactProtocol.Factory(  ) );
        arg.transportFactory( new TFastFramedTransport.Factory(  ) );
        arg.processorFactory( new TProcessorFactory( personServiceProcessor ) );
        TServer server = new THsHaServer( arg );

        server.serve();

    }
}
