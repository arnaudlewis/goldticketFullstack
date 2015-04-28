package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;

public class Committer {
    @SerializedName("id")
    @XmlElement(name = "id")
    private int id;
    @SerializedName("login")
    @XmlElement(name = "login")
    private String login;
    @SerializedName("avatar_url")
    @XmlElement(name = "avatar_url")
    private String avatarUrl;
    @SerializedName("html_url")
    @XmlElement(name = "html_url")
    private String htmlUrl;
    @SerializedName("followers_url")
    @XmlElement(name = "followers_url")
    private String followerUrl;
    @SerializedName("following_url")
    @XmlElement(name = "following_url")
    private String followingUrl;
    @SerializedName("starred_url")
    @XmlElement(name = "starred_url")
    private String starredUrl;
    @SerializedName("repos_url")
    @XmlElement(name = "repos_url")
    private String reposUrl;

    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getFollowerUrl() {
        return followerUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }
}
