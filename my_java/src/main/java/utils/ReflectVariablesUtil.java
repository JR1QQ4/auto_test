package utils;


public class ReflectVariablesUtil {
    public static String generateCityCountryCode() {
        String result = "AGO";
        String[] countryCodeArray = {"ABW", "AFG", "AGO", "AIA", "ALB", "AND", "ANT", "ARE", "ARG"};
        int length = countryCodeArray.length;
        int index = (int) (Math.random() * length);
        result = countryCodeArray[index];
        return result;
    }

    public static void main(String[] args) {
        System.out.println(generateCityCountryCode());
    }
}
