package com.adammcneilly.androidstudyguide.data.remote.androidessence

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class AndroidEssenceFeedItem(
    @field:Element(name = "title")
    @param:Element(name = "title")
    val title: String? = null,
    @field:Element(name = "author")
    @param:Element(name = "author")
    val author: AndroidEssenceAuthor? = null,
    @field:Element(name = "link")
    @param:Element(name = "link")
    val link: AndroidEssenceLink? = null,
    @field:ElementList(name = "category", inline = true)
    @param:ElementList(name = "category", inline = true)
    val categories: List<AndroidEssenceCategory>? = null,
    @field:Element(name = "published")
    @param:Element(name = "published")
    val publishedDate: String? = null
)
