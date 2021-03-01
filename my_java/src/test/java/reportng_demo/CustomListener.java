package reportng_demo;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import utils.ScreenshotUtils;

import java.io.File;
import java.util.Date;

/**
 * 用于生成测试报告中的截图
 */
public class CustomListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult tr) {
        super.onTestFailure(tr);
        String baseDir = "target" + File.separator + "surefire-reports";
        String screenshotDir = "screenshot";

        // 获取测试上下文 -> 获取当前正在执行的test测试集 -> 获取测试集的name值
        String testNameDir = tr.getTestContext().getCurrentXmlTest().getName();

        Date now = new Date();
        String dateDir = DateFormatUtils.format(now, "yyyy-MM-dd");
        String fileName = now.getTime() + ".jpg";
        String filePath = baseDir + File.separator + screenshotDir + File.separator
                + testNameDir + File.separator + dateDir + File.separator + fileName;

        // filePath: test-output\screenshot\注册\2021-03-01\1614569294313.jpg
        System.out.println("filePath: " + filePath);

        File file = ScreenshotUtils.saveScreenshot(filePath);

        String toBeReplaced =  tr.getTestContext().getCurrentXmlTest().getParameter("apacheDocumentRoot");
        String replacement = tr.getTestContext().getCurrentXmlTest().getParameter("host");
        String imgString = getAccessPath(toBeReplaced, replacement, file);

        // imgString: <img src="http://localhost\screenshot\注册\2021-03-01\1614569294313.jpg" height='100px' width='100px'><a href="http://localhost\screenshot\注册\2021-03-01\1614569294313.jpg" target="_blank">点击查看大图</a></img>
        System.out.println("imgString: " + imgString);
        Reporter.log(imgString);
    }

    /**
     * 获取页面展示图片的 html 代码
     * @param toBeReplaced 要替换的地址
     * @param replacement 替换后的值
     * @param file 文件
     * @return html 代码
     */
    private String getAccessPath(String toBeReplaced, String replacement, File file) {
        String absolutePath = file.getAbsolutePath();
//        absolutePath = absolutePath.replace("\\", "/");
        String accessPath = absolutePath.replace(toBeReplaced, replacement);
        String img = "<img src=\"" + accessPath + "\" height='100px' width='100px'>" +
                "<a href=\"" + accessPath + "\" target=\"_blank\">点击查看大图</a></img>";
        return img;
    }

    public static void main(String[] args) {
        Date date = new Date();
        String dateDir = DateFormatUtils.format(date, "yyyy-MM-dd");
        System.out.println(dateDir);
    }
}
