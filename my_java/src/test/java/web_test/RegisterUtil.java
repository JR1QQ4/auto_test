package web_test;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class RegisterUtil {
    public static List<RegisterData> registerDataArrayList = new ArrayList<RegisterData>();

    static {
        List<RegisterData> registerDataList = ExcelUtil.loadPlus(PropertiesUtil.getRegisterExcelPath(), "Cases", RegisterData.class);
        registerDataArrayList.addAll(registerDataList);
    }

    public static Object[][] getNegativeOrPositiveData(String flag, String[] columnNames) {
        Class<RegisterData> clazz = RegisterData.class;
        List<RegisterData> registerDataList = new ArrayList<RegisterData>();
        for (RegisterData registerData:
             registerDataArrayList) {
            if (flag.equals(registerData.getIsNegative())){
                registerDataList.add(registerData);
            }
        }
        Object[][] data = new Object[registerDataList.size()][columnNames.length];
        for (int i = 0; i < registerDataList.size(); i++) {
            RegisterData registerData = registerDataList.get(i);
            for (int j = 0; j < columnNames.length; j++) {
                String methodName = "get" + columnNames[j];
                Method method;
                try {
                    method = clazz.getMethod(methodName);
                    String value = (String) method.invoke(registerData);
                    data[i][j] = value;
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
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
