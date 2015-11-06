/**
 * Created by msaidi on 11/6/15.
 */
public class Location {

    private String city;
    private String country;
    private String description;
    private double latitude;
    private double longitude;
    private String state;
    private String street;
    private String zip;

    public Location() {
    }

    public Location(org.springframework.social.facebook.api.Location pageLocation) {
        this(pageLocation.getCity(), pageLocation.getCountry(), pageLocation
                        .getDescription(), pageLocation.getLatitude(), pageLocation
                        .getLongitude(), pageLocation.getState(), pageLocation.getStreet(),
                pageLocation.getZip());
    }

    public Location(String city, String country, String description, double latitude,
                    double longitude, String state, String street, String zip) {
        this.city = city;
        this.country = country;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.state = state;
        this.street = street;
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getState() {
        return state;
    }

    public String getStreet() {
        return street;
    }

    public String getZip() {
        return zip;
    }
}
