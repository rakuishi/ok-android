package com.rakuishi.ok.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(strict = false)
public class Feed {

    @Path("channel")
    @Element(name = "title")
    public String title;

    @Path("channel")
    @Element(name = "link")
    public String link;

    @Path("channel")
    @Element(name = "updated")
    public String updated;

    @Path("channel")
    @ElementList(inline = true)
    public List<FeedItem> list;
}
