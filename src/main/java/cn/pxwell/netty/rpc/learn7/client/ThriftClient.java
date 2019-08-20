package cn.pxwell.netty.rpc.learn7.client;

import cn.pxwell.netty.rpc.learn7.thrift.generated.DataException;
import cn.pxwell.netty.rpc.learn7.thrift.generated.Person;
import cn.pxwell.netty.rpc.learn7.thrift.generated.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;

public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport( new TSocket( "localhost",8899 ),600 );
        TProtocol protocol = new TCompactProtocol( transport );
        PersonService.Client client = new PersonService.Client( protocol );

        try {
            transport.open();
            Person person = client.getPersonByUserName( "张三" );
            person.setUsername( "王五" );
            client.savePerson( person );

        } catch (TTransportException e) {
            e.printStackTrace();
        } catch (DataException e) {
            e.printStackTrace();
        } catch (TException e) {
            e.printStackTrace();
        }


    }
}
