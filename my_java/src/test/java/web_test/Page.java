package web_test;

import java.util.List;

public class Page {
    private String keyword;
    private List<UIElement> UIElementS;

    public String getKeyword() {
        return keyword;
    }

    public List<UIElement> getUIElementS() {
        return UIElementS;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setUIElementS(List<UIElement> UIElementS) {
        this.UIElementS = UIElementS;
    }
}
