package base;

public class MyCDCatalog {
    public String TITLE;
    public String ARTIST;
    public String COUNTRY;
    public String COMPANY;
    public String PRICE;
    public String YEAR;

    public String getTITLE() {
        return TITLE;
    }

    public String getARTIST() {
        return ARTIST;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public String getCOMPANY() {
        return COMPANY;
    }

    public String getPRICE() {
        return PRICE;
    }

    public String getYEAR() {
        return YEAR;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public void setARTIST(String ARTIST) {
        this.ARTIST = ARTIST;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public void setCOMPANY(String COMPANY) {
        this.COMPANY = COMPANY;
    }

    public void setPRICE(String PRICE) {
        this.PRICE = PRICE;
    }

    public void setYEAR(String YEAR) {
        this.YEAR = YEAR;
    }

    public MyCDCatalog() {
    }

    public MyCDCatalog(String TITLE, String ARTIST, String COUNTRY, String COMPANY, String PRICE, String YEAR) {
        this.TITLE = TITLE;
        this.ARTIST = ARTIST;
        this.COUNTRY = COUNTRY;
        this.COMPANY = COMPANY;
        this.PRICE = PRICE;
        this.YEAR = YEAR;
    }
}
