package com.rakuishi.ok.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
class Rss {
    @set:Element(name = "channel")
    @get:Element(name = "channel")
    var channel: Channel? = null
}