package web_test;

import java.util.ArrayList;
import java.util.List;

public class RegisterUtil {
    public static List<RegisterData> registerDataArrayList = new ArrayList<>();

    static {
        List<RegisterData> registerDataList = ExcelUtil.loadPlus(PropertiesUtil.getRegisterExcelPath(), "Cases", RegisterData.class);
        registerDataArrayList.addAll(registerDataList);
    }

    public static Object[][] getNegativeOrPositiveData(String flag, String[] columnNames) {
        Class<RegisterData> clazz = RegisterData.class;
        List<Object> satisfied = new ArrayList<>();
        for (RegisterData registerData:
             registerDataArrayList) {
            if (flag.equals(registerData.getIsNegative())){
                satisfied.add(registerData);
            }
        }
        return BaseUtil.getObjects(columnNames, clazz, satisfied);
    }



    public static void main(String[] args) {
        System.out.println("测试");
        String[] columnNames = {"PhoneNumber", "Password", "PasswordConfirm", "VerifyCode", "ExpectedErrorMsg"};
        Object[][] objectArrays = getNegativeOrPositiveData("0", columnNames);
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
