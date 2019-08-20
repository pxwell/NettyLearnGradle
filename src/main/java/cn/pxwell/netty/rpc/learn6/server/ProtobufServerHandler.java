package cn.pxwell.netty.rpc.learn6.server;


import cn.pxwell.netty.rpc.learn6.entity.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtobufServerHandler extends SimpleChannelInboundHandler<DataInfo.MyMessage> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MyMessage msg) throws Exception {

       if (msg.getDataType() == DataInfo.MyMessage.DataType.StudentType){
           System.out.println("学生: " + msg.getStudent().getName());
       }
        if (msg.getDataType() == DataInfo.MyMessage.DataType.FatherType){
            System.out.println("父亲: " + msg.getFather().getName());
        }
        if (msg.getDataType() == DataInfo.MyMessage.DataType.MatherType){
            System.out.println("母亲: " + msg.getMather().getName());
        }

    }


}
