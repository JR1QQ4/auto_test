package web_test;

public class UIElement {
    private String keyword;
    private String by;
    private String value;

    public String getKeyword() {
        return keyword;
    }

    public String getBy() {
        return by;
    }

    public String getValue() {
        return value;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setBy(String by) {
        this.by = by;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public UIElement(String keyword, String by, String value) {
        this.keyword = keyword;
        this.by = by;
        this.value = value;
    }

    public UIElement() {
    }

    @Override
    public String toString() {
        return "UIElement{" +
                "keyword='" + keyword + '\'' +
                ", by='" + by + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
