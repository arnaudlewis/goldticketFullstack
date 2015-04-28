package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;

public class Commit {

    @SerializedName("committer")
    @XmlElement(name = "committer")
    private Committer committer;
    @SerializedName("commit")
    @XmlElement(name = "commit")
    private CommitDetails commitDetails;

    public Committer getCommitter() {
        return committer;
    }

    public CommitDetails getCommitDetails() {
        return commitDetails;
    }
}

