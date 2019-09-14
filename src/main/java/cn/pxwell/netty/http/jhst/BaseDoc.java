package cn.huazx.pxwell.dataexchange.expro.common;

import cn.huazx.pxwell.dataexchange.core.utils.ExChangeCodeBulider;
import cn.huazx.pxwell.dataexchange.core.utils.Global;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BaseDoc<T> {
    private String exchangeType;//交换类型
    private String exchangeCode ;
    @JSONField(format = "yyyyMMddHHmmss")
    private Date requestTime;
    private String version;
    private List<T> body;

    public BaseDoc(){}

    public BaseDoc(RequestWapper<T>  requestWapper) {
        if (requestWapper!=null){
            requestTime= new Date(  );
            exchangeCode =ExChangeCodeBulider.getCode();
            version= Global.getVersion();
            exchangeType = requestWapper.getExchangeType();

        }
    }
}
