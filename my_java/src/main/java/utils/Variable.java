package utils;

public class Variable {
    private String name;
    private String value;
    private String remarks;

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
