package web_test;

import java.util.List;

public class Page {
    private String keyword;
    private List<UIElement> UIElements;

    public String getKeyword() {
        return keyword;
    }

    public List<UIElement> getUIElements() {
        return UIElements;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUIElementS(List<UIElement> UIElements) {
        this.UIElements = UIElements;
    }

    public Page() {
    }

    public Page(String keyword, List<UIElement> UIElements) {
        this.keyword = keyword;
        this.UIElements = UIElements;
    }
}
