package interface_test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import utils.BaseProcessor;
import utils.CaseUtil;
import utils.ExcelUtil;

public class InterfaceTestV4 extends BaseProcessor {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return CaseUtil.getCasesDatesApiId("1", columnNames);
    }

    @AfterSuite
    public void batchWriteBackData() {
        ExcelUtil.batchWriteBackData("src/main/resources/baiduInterface_v4.xlsx");
    }
}
