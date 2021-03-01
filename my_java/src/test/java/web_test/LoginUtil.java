package web_test;

import java.util.ArrayList;
import java.util.List;

public class LoginUtil {
    public static List<LoginData> loginDataArrayList = new ArrayList<>();

    static {
        List<LoginData> loginDataList = ExcelUtil.loadPlus(PropertiesUtil.getLoginExcelPath(), "Cases", LoginData.class);
        loginDataArrayList.addAll(loginDataList);
    }

    public static Object[][] getNegativeOrPositiveData(String flag, String[] columnNames) {
        Class<LoginData> clazz = LoginData.class;
        List<Object> loginDataList = new ArrayList<>();
        for (LoginData loginData:
                loginDataArrayList) {
            if (flag.equals(loginData.getIsNegative())){
                loginDataList.add(loginData);
            }
        }
        return BaseUtil.getObjects(columnNames, clazz, loginDataList);
    }

    public static void main(String[] args) {
        System.out.println("测试");
        String[] columnNames = {"PhoneNumber", "Password", "ExpectedErrorMsg"};
        Object[][] objectArrays = getNegativeOrPositiveData("1", columnNames);
        for (Object[] objects:
                objectArrays) {
            for (Object object:
                    objects) {
                System.out.print("【" + object + "】");
            }
            System.out.println();
        }
    }
}
