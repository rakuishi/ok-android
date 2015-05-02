package com.rakuishi.ok.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by rakuishi on 15/05/02.
 */
@Root(strict = false)
public class Feed {

    @Path("channel")
    @Element(name = "title")
    private String mTitle;

    @Path("channel")
    @Element(name = "link")
    private String mLink;

    @Path("channel")
    @Element(name = "updated")
    private String mUpdated;

    @Path("channel")
    @ElementList(inline = true)
    private List<FeedItem> mList;

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getUpdated() {
        return mUpdated;
    }

    public List<FeedItem> getList() {
        return mList;
    }

    @Override
    public String toString() {
        return "title: " + mTitle + "\n" +
               "link: " + mLink + "\n" +
               "updated: " + mUpdated + "\n" +
               "list: " + mList.toString();
    }
}
