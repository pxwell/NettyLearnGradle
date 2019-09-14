package cn.pxwell.netty.http.jhst;


import com.alibaba.fastjson.annotation.JSONField;


import java.util.Date;
import java.util.List;


public class BaseDoc<T> {
    private String exchangeType;//交换类型
    private String exchangeCode ;
    @JSONField(format = "yyyyMMddHHmmss")
    private Date requestTime;
    private String version;
    private List<T> body;

    public BaseDoc(){}

    public String getExchangeType() {
        return exchangeType;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public String getExchangeCode() {
        return exchangeCode;
    }

    public void setExchangeCode(String exchangeCode) {
        this.exchangeCode = exchangeCode;
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }
}
