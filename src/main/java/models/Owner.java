package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;

public class Owner {

    @SerializedName("login")
    @XmlElement(name = "login")
    private String login;
    @SerializedName("id")
    @XmlElement(name = "id")
    private int id;
    @SerializedName("avatar_url")
    @XmlElement(name = "avatar_url")
    private String avatar_url;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }
}
