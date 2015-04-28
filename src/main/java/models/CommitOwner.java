package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Arnaud on 28/04/2015.
 */
public class CommitOwner {

    @SerializedName("name")
    @XmlElement(name = "name")
    private String name;

    @SerializedName("email")
    @XmlElement(name = "email")
    private String email;

    @SerializedName("date")
    @XmlElement(name = "date")
    private String date;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDate() {
        return date;
    }
}
