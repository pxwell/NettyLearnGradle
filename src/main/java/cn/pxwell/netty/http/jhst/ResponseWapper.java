package cn.pxwell.netty.http.jhst;




import java.util.List;


public class ResponseWapper<T>{
    private String exchangeType; //交换类型
    private String exchangeCode; //交换识别码
    private String jkId; //接口标识
    private String responseTime;
    private String code; //回执结果
    private String message; //回执说明
    private String version; //交换版本
    private List<T>  body;

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

    public String getJkId() {
        return jkId;
    }

    public void setJkId(String jkId) {
        this.jkId = jkId;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
