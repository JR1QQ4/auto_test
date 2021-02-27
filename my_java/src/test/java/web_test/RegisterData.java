package web_test;

public class RegisterData {
    private String isNegative;
    private String desc;
    private String phoneNumber;
    private String password;
    private String passwordConfirm;
    private String verifyCode;
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

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getVerifyCode() {
        return verifyCode;
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

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public void setExpectedErrorMsg(String expectedErrorMsg) {
        this.expectedErrorMsg = expectedErrorMsg;
    }

    public RegisterData(String isNegative, String desc, String phoneNumber, String password, String passwordConfirm, String verifyCode, String expectedErrorMsg) {
        this.isNegative = isNegative;
        this.desc = desc;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.verifyCode = verifyCode;
        this.expectedErrorMsg = expectedErrorMsg;
    }

    public RegisterData() {
    }

    @Override
    public String toString() {
        return "RegisterData{" +
                "isNegative='" + isNegative + '\'' +
                ", desc='" + desc + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                ", expectedErrorMsg='" + expectedErrorMsg + '\'' +
                '}';
    }
}
