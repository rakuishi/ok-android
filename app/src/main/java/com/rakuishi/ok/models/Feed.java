package com.rakuishi.ok.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class Feed {

    @Path("channel")
    @Element(name = "title")
    public final String title;

    @Path("channel")
    @Element(name = "link")
    public final String link;

    @Path("channel")
    @Element(name = "updated")
    public final String updated;

    @Path("channel")
    @ElementList(inline = true)
    public final List<FeedItem> list;

    public Feed(String title, String link, String updated, List<FeedItem> list) {
        this.title = title;
        this.link = link;
        this.updated = updated;
        this.list = list;
    }

    @Override
    public String toString() {
        return "title: " + title + "\n" +
               "link: " + link + "\n" +
               "updated: " + updated + "\n" +
               "list: " + list.toString();
    }
}
