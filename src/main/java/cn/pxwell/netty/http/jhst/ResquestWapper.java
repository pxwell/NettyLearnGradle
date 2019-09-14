package cn.pxwell.netty.http.jhst;


public class ResquestWapper {
        private String jkId; //接口标识
        private String jkSqm; //接口授权码
        private String jkYhm;//接口用户名
        private String queryJsonDoc; //交换数据 AES加密后数据
        private String crcCode; //交换识别码

        public String getJkId() {
                return jkId;
        }

        public void setJkId(String jkId) {
                this.jkId = jkId;
        }

        public String getJkSqm() {
                return jkSqm;
        }

        public void setJkSqm(String jkSqm) {
                this.jkSqm = jkSqm;
        }

        public String getJkYhm() {
                return jkYhm;
        }

        public void setJkYhm(String jkYhm) {
                this.jkYhm = jkYhm;
        }

        public String getQueryJsonDoc() {
                return queryJsonDoc;
        }

        public void setQueryJsonDoc(String queryJsonDoc) {
                this.queryJsonDoc = queryJsonDoc;
        }

        public String getCrcCode() {
                return crcCode;
        }

        public void setCrcCode(String crcCode) {
                this.crcCode = crcCode;
        }
}
