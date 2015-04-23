package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;

public class Item {

    @SerializedName("full_name")
    @XmlElement(name = "full_name")
    private String fullname;
    @SerializedName("name")
    @XmlElement(name = "name")
    private String name;
    @SerializedName("id")
    @XmlElement(name = "id")
    private int id;
    @SerializedName("owner")
    @XmlElement(name = "owner")
    private Owner owner;
    @SerializedName("stargazers_count")
    @XmlElement(name="stargazers_count")
    private int stargazers_count;
    @SerializedName("forks_count")
    @XmlElement(name="forks_count")
    private int forks_count;
    @SerializedName("language")
    @XmlElement(name="language")
    private String language;

    public String getFullname() {
        return fullname;
    }

    public int getId() {
        return id;
    }

    public Owner getOwner() {
        return owner;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public int getForks_count() {
        return forks_count;
    }

    public String getLanguage() {
        return language;
    }

    public String getName() {
        return name;
    }
}

