package models;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Repositories {

    @SerializedName("total_count")
    @XmlElement(name="total_count")
    private int totalCount;
    @SerializedName("incomplete_results")
    @XmlElement(name="incomplete_results")
    private String incompleteResults;
    @SerializedName("items")
    @XmlElement(name="items")
    private List<Item> itemList;


    public int getTotalCount() {
        return totalCount;
    }

    public String getIncompleteResults() {
        return incompleteResults;
    }

    public List<Item> getItemList() {
        return itemList;
    }
}
