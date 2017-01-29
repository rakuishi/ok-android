package com.rakuishi.ok.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class FeedItem {

    @Element(name = "title")
    public final String title;

    @Element(name = "link")
    public final String link;

    @Element(name = "pubDate")
    public final String pubDate;

    @Element(name = "description")
    public final String description;

    public FeedItem(String title, String link, String pubDate, String description) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.description = description;
    }

    @Override
    public String toString() {
        return "title: " + title + "\n" +
               "link: " + link + "\n" +
               "pubDate: " + pubDate + "\n" +
               "description: " + description;
    }
}
