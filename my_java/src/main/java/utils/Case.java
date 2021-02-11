package utils;

/**
 * 保存 Case 的信息
 */
public class Case {
    private String caseId;
    private String desc;
    private String apiId;
    private String params;

    public String getCaseId() {
        return caseId;
    }

    public String getDesc() {
        return desc;
    }

    public String getApiId() {
        return apiId;
    }

    public String getParams() {
        return params;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public void setParams(String params) {
        this.params = params;
    }

    @Override
    public String toString() {
        return "Case{" +
                "caseId='" + caseId + '\'' +
                ", desc='" + desc + '\'' +
                ", apiId='" + apiId + '\'' +
                ", params='" + params + '\'' +
                '}';
    }
}
