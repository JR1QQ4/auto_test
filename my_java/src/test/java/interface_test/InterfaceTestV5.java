package interface_test;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import utils.BaseProcessor;
import utils.CaseUtil;
import utils.ExcelUtil;

public class InterfaceTestV5 extends BaseProcessor {
    @DataProvider(name = "data")
    public Object[][] getData() {
        return CaseUtil.getCasesDatesApiId("1", columnNames);
    }

    @AfterSuite
    public void batchWriteBackData() {
        ExcelUtil.batchWriteBackData("src/main/resources/baiduInterface_v5.xlsx");
    }
}
