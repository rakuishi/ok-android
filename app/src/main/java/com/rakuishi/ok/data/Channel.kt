package com.rakuishi.ok.data

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
class Channel {
    @set:ElementList(name = "item", inline = true)
    @get:ElementList(name = "item", inline = true)
    var list: List<Feed> = arrayListOf()
}