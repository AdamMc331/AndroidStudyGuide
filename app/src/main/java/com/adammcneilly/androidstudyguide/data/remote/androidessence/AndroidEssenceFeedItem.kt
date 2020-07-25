package com.adammcneilly.androidstudyguide.data.remote.androidessence

import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class AndroidEssenceFeedItem(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String = "",
    @field:Element(name = "id")
    @param:Element(name = "id")
    val url: String = "",
    @field:Element(name = "author")
    @param:Element(name = "author")
    val author: AndroidEssenceAuthor = AndroidEssenceAuthor()
)
