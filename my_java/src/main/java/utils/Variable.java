package utils;

public class Variable {
    private String name;
    private String value;
    private String remarks;

    // 保存反射相关的信息
    private String reflectClass;
    private String reflectMethod;
    private String reflectValue;

    public String getReflectClass() {
        return reflectClass;
    }

    public String getReflectMethod() {
        return reflectMethod;
    }

    public String getReflectValue() {
        return reflectValue;
    }

    public void setReflectClass(String reflectClass) {
        this.reflectClass = reflectClass;
    }

    public void setReflectMethod(String reflectMethod) {
        this.reflectMethod = reflectMethod;
    }

    public void setReflectValue(String reflectValue) {
        this.reflectValue = reflectValue;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Variable() {
    }
}
