package bkh.com.silverspell.models;

public class Languages {
    private String language;
    private String country;
    private String display_name;

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public Languages(String language, String country, String display_name) {
        this.language = language;
        this.country = country;
        this.display_name = display_name;
    }
}
