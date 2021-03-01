package web_test;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class UILibraryUtil {
    public static List<Page> pageArrayList = new ArrayList<Page>();

    static {
        loadPages(PropertiesUtil.getUILibraryPath());
    }

    /**
     * 解析 UI 文件
     *
     * @param uiLibraryPath xml文件路径
     */
    private static void loadPages(String uiLibraryPath) {
        // 解析 xml
        SAXReader saxReader = new SAXReader();
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(new File("src/test/java/web_test/UILibrary.xml"));
            Document document = saxReader.read(inputStream);
            Element root = document.getRootElement();
            List<Element> pages = root.elements("Page");
            for (Element pageElement :
                    pages) {
                String pageKeyWord = pageElement.attributeValue("keyword");
                // 获取 page 元素的子元素
                List<Element> uiElements = pageElement.elements("UIElement");
                // 将每个 UIElement 元素解析封装成 UIElement 类型的对象，再保存到集合中去
                List<UIElement> uiElementList = new ArrayList<UIElement>();
                for (Element uiElement :
                        uiElements) {
                    String uiElementKeyWord = uiElement.attributeValue("keyword");
                    String by = uiElement.attributeValue("by");
                    String value = uiElement.attributeValue("value");
                    UIElement uiEle = new UIElement(uiElementKeyWord, by, value);
                    uiElementList.add(uiEle);
                }
                Page pageObject = new Page(pageKeyWord, uiElementList);
                pageArrayList.add(pageObject);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据页面关键字和元素关键字获取元素
     * @param pageKeyword 页面关键字
     * @param uiElementKeyword 元素关键字
     * @return 元素
     */
    public static WebElement getElementByKeyword(String pageKeyword, String uiElementKeyword) {
        WebElement webElement = null;
        for (Page pageObject :
                pageArrayList) {
            if (pageKeyword.equals(pageObject.getKeyword())) {
                List<UIElement> uiElementList = pageObject.getUIElements();
                for (UIElement uiElement :
                        uiElementList) {
                    if (uiElementKeyword.equals(uiElement.getKeyword())) {
                        String by = uiElement.getBy();
                        String value = uiElement.getValue();
                        webElement = getVisibleElement(by, value);
                    }
                }
            }
        }
        return webElement;
    }

    /**
     * 获取可见元素
     * @param by 定位方式
     * @param value 定位值
     * @return 元素
     */
    public static WebElement getVisibleElement(String by, String value) {
        WebDriverWait wait = new WebDriverWait(Base.driver, 30);
        By locator = null;
        WebElement webElement = null;
        switch (by) {
            case "id":
                locator = By.id(value);
                break;
            case "className":
                locator = By.className(value);
                break;
            case "linkText":
                locator = By.linkText(value);
                break;
            case "name":
                locator = By.name(value);
                break;
            case "partialLinkText":
                locator = By.partialLinkText(value);
                break;
            case "cssSelector":
                locator = By.cssSelector(value);
                break;
            default:
                System.out.println("不支持的 locator: 【" + by + "】");
                break;
        }
        try{
            webElement = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//            webElement = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e) {
            if (e instanceof TimeoutException){
                System.out.println("获取元素失败: by=【" + by + "】, value=【" + value + "】");
            }else {
                e.printStackTrace();
            }
        }
        return webElement;
    }

    public static void main(String[] args) {
        getElementByKeyword("注册页面", "手机号").sendKeys("18428308899");
    }
}
