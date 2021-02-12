package utils;

/**
 * 存放接口信息的类
 */
public class Rest {
    private String apiId;
    private String apiName;
    private String url;
    private String type;
    private String desc;

    public String getApiId() {
        return apiId;
    }

    public String getApiName() {
        return apiName;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }

    public String getDesc() {
        return desc;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "Rest{" +
                "apiId='" + apiId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}
