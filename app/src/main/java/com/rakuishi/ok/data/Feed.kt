package com.rakuishi.ok.data

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "item", strict = false)
class Feed {
    @get:Element(name = "title")
    @set:Element(name = "title")
    var title: String = ""

    @get:Element(name = "link")
    @set:Element(name = "link")
    var link: String = ""

    @get:Element(name = "description")
    @set:Element(name = "description")
    var description: String = ""

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false
        return true
    }

    override fun hashCode(): Int {
        return javaClass.hashCode()
    }
}