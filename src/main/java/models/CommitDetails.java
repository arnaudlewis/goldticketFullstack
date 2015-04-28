package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by Arnaud on 28/04/2015.
 */
public class CommitDetails {

    @SerializedName("committer")
    @XmlElement(name = "committer")
    private CommitOwner commitOwner;

    public CommitOwner getCommitOwner() {
        return commitOwner;
    }
}
