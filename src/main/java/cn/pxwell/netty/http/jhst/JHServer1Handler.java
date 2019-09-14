package cn.pxwell.netty.http.jhst;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.Attribute;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;

import io.netty.util.CharsetUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class JHServer1Handler extends SimpleChannelInboundHandler<HttpObject> {



    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {

        String jkId=null ;
        String jkYhm = null;
        String exchangeCode = null;
        String QueryJsonDoc = null;
        BaseDoc baseDoc = null;

        if(msg instanceof HttpRequest){
            HttpRequest request = (HttpRequest) msg;
            //System.out.println("请求方法名"+request.method());
            System.out.println( request.uri() );

            if (request.method() == HttpMethod.POST) {
                HttpPostRequestDecoder  decoder = new HttpPostRequestDecoder( request );
                List<InterfaceHttpData> parmList = decoder.getBodyHttpDatas();

                for (InterfaceHttpData parm :parmList){
                    Attribute data = (Attribute) parm;
                    System.out.println("name: "+data.getName() +" value: "+data.getValue());
                    if (data.getName().equals( "jkId" )){
                        jkId=data.getValue();
                    }
                    if (data.getName().equals( "jkYhm" )){
                        jkYhm = data.getValue();
                    }
                    if (data.getName().equals( "QueryJsonDoc" )){
                        QueryJsonDoc = data.getValue();
                    }


                }
                baseDoc =   JSONObject.parseObject(QueryJsonDoc,BaseDoc.class );
                //exchangeCode = resquestWapper.get
                if (baseDoc!=null){
                    exchangeCode = baseDoc.getExchangeCode();
                }
            }

        }

        SimpleDateFormat dateFormat = new SimpleDateFormat( "yyyyMMddHHmmss" );
        Thread.sleep( 1000 );
        ResponseWapper<Object> responseWapper  = new ResponseWapper<>();
        responseWapper.setCode( "1" );
        responseWapper.setJkId( jkId );
        responseWapper.setResponseTime(dateFormat.format( new Date(  ) ));
        responseWapper.setMessage( "this is a test" );
        responseWapper.setVersion( "3.1" );
        responseWapper.setExchangeType( "01" );
        responseWapper.setExchangeCode( exchangeCode );



        ByteBuf content = Unpooled.copiedBuffer( JSONObject.toJSONString( responseWapper ), CharsetUtil.UTF_8 );
        FullHttpResponse response = new DefaultFullHttpResponse( HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,content);
        response.headers().set( HttpHeaderNames.CONTENT_TYPE,"application/json" );
        response.headers().set( HttpHeaderNames.CONTENT_LENGTH ,content.readableBytes());



        ctx.writeAndFlush( response );

    }


    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("新增连接 +"+ ctx.channel().id());
        super.handlerAdded( ctx );
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        System.out.println("删除连接 +"+ ctx.channel().id());
        super.handlerRemoved( ctx );
    }
}
