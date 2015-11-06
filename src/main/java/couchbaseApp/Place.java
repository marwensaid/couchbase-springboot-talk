package couchbaseApp;

import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.Field;
import org.springframework.social.facebook.api.Page;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by msaidi on 11/6/15.
 */
@Document(expiry = 0)
class Place {

    @Id
    private String id;

    @Field
    private Location location;

    @Field
    @NotNull
    private String name;

    @Field
    private String affilitation, category, description, about;

    @Field
    private Date insertionDate;

    public String getName() {
        return name;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }

    public String getId() {
        return id;
    }

    public Place(Page p) {
        this.affilitation = p.getAffiliation();
        this.id = p.getId();
        this.name = p.getName();
        this.category = p.getCategory();
        this.description = p.getDescription();
        this.about = p.getAbout();
        this.insertionDate = new Date();
        org.springframework.social.facebook.api.Location pageLocation = p.getLocation();
        this.location = new Location(pageLocation);
    }

    public Place() {
    }

    public Place(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "couchbaseApp.Place{" + "id='" + id + '\'' + ", location=" + location + ", name='"
                + name + '\'' + ", affilitation='" + affilitation + '\'' + ", category='"
                + category + '\'' + ", description='" + description + '\'' + ", about='"
                + about + '\'' + ", insertionDate=" + insertionDate + '}';
    }

    public Location getLocation() {
        return location;
    }

    public String getAffilitation() {
        return affilitation;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getAbout() {
        return about;
    }
}

