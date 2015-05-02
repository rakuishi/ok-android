package com.rakuishi.ok.api.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by rakuishi on 15/05/02.
 */
@Root(name = "item", strict = false)
public class FeedItem {

    @Element(name = "title")
    private String mTitle;

    @Element(name = "link")
    private String mLink;

    @Element(name = "pubDate")
    private String mPubDate;

    @Element(name = "description")
    private String mDescription;

    public String getTitle() {
        return mTitle;
    }

    public String getLink() {
        return mLink;
    }

    public String getPubDate() {
        return mPubDate;
    }

    public String getDescription() {
        return mDescription;
    }

    @Override
    public String toString() {
        return "title: " + mTitle + "\n" +
               "link: " + mLink + "\n" +
               "pubDate: " + mPubDate + "\n" +
               "description: " + mDescription;
    }
}
