package cn.pxwell.netty.rpc.learn6.client;

import cn.pxwell.netty.rpc.learn6.entity.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import javax.xml.crypto.Data;
import java.util.Random;


public class ProtobufClientHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMessage msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        /*super.channelActive( ctx );*/
        /*DataInfo.Student student = DataInfo.Student.newBuilder().setName( "王伟贤" ).setAge( 23 ).setAddress( "成都" ).build();
        ctx.writeAndFlush( student );*/

        int randomInt = new Random().nextInt(3);
        DataInfo.MyMessage myMessage = null;
        if (randomInt == 0) {
            DataInfo.Student student = DataInfo.Student.newBuilder().setName( "潘舒曼" ).setAge( 3 ).setAddress( "佛祖岭" ).build();
            myMessage = DataInfo.MyMessage.newBuilder().setDataType( DataInfo.MyMessage.DataType.StudentType )
                    .setStudent( student ).build();
            ctx.writeAndFlush( myMessage );
        }else if (1 == randomInt){
            DataInfo.Father father = DataInfo.Father.newBuilder().setName( "潘晓巍" ).setCity( "老河口" ).build();
            myMessage = DataInfo.MyMessage.newBuilder().setDataType( DataInfo.MyMessage.DataType.FatherType )
                    .setFather( father ).build();
        }else if(2 == randomInt){
            DataInfo.Mather mather = DataInfo.Mather.newBuilder().setName( "舒瑶" ).setCity( "佛祖岭" ).build();
            myMessage = DataInfo.MyMessage.newBuilder().setDataType( DataInfo.MyMessage.DataType.MatherType )
                    .setMather( mather ).build();
        }
        ctx.writeAndFlush( myMessage );
    }
}
