package cn.pxwell.netty.rpc.learn7.thrift;

import cn.pxwell.netty.rpc.learn7.thrift.generated.DataException;
import cn.pxwell.netty.rpc.learn7.thrift.generated.Person;
import cn.pxwell.netty.rpc.learn7.thrift.generated.PersonService;
import org.apache.thrift.TException;

import javax.jnlp.PersistenceService;

public class PersonServiceImpl  implements PersonService.Iface {
    @Override
    public Person getPersonByUserName(String username) throws DataException, TException {
        System.out.println("getPersonByUserName " + username);
        Person person = new Person(  );
        person.setAge( 32 );
        person.setUsername( username);
        person.setAgeIsSet( true );
        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("savePerson"  +person.getUsername() + person.getAge());
    }
}
