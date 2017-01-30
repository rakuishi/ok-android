package com.rakuishi.ok.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "item", strict = false)
public class FeedItem {

    @Element(name = "title")
    public String title;

    @Element(name = "link")
    public String link;

    @Element(name = "pubDate")
    public String pubDate;

    @Element(name = "description")
    public String description;
}
