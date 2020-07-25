package com.adammcneilly.androidstudyguide.data.remote.androidessence

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

/**
 * When we consider pulling RSS feeds that are not Android Essence, this class may be reusable
 * as some RSS feed standard.
 */
@Root(name = "link", strict = false)
data class AndroidEssenceLink(
    @field:Attribute(name = "href")
    @param:Attribute(name = "href")
    val href: String? = null
)
