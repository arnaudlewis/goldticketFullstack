package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Repository {

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
    @XmlElement(name="commits")
    private List<Commit> commits;
    @XmlElement(name="committers")
    private List<Committer> commiters;

    public String getFullname() {
        return fullname;
    }

    public String getName() {
        return name;
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

    public List<Commit> getCommits() {
        return commits;
    }

    public List<Committer> getCommiters() {
        return commiters;
    }

    public void setCommits(List<Commit> commits) {
        this.commits = commits;
    }

    public void setCommiters(List<Committer> commiters) {
        this.commiters = commiters;
    }
}
