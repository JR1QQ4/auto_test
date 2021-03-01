package web_test;

public class LoginData {
    private String isNegative;
    private String desc;
    private String phoneNumber;
    private String password;
    private String expectedErrorMsg;

    public String getIsNegative() {
        return isNegative;
    }

    public String getDesc() {
        return desc;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getExpectedErrorMsg() {
        return expectedErrorMsg;
    }

    public void setIsNegative(String isNegative) {
        this.isNegative = isNegative;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setExpectedErrorMsg(String expectedErrorMsg) {
        this.expectedErrorMsg = expectedErrorMsg;
    }

    public LoginData() {
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "isNegative='" + isNegative + '\'' +
                ", desc='" + desc + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", expectedErrorMsg='" + expectedErrorMsg + '\'' +
                '}';
    }
}
