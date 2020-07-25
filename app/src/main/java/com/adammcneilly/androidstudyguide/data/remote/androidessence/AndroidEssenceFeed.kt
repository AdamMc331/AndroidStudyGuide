package com.adammcneilly.androidstudyguide.data.remote.androidessence

import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

/**
 * Need to annotate use sites: https://kotlinlang.org/docs/reference/annotations.html#annotation-use-site-targets
 */
@Root(name = "feed", strict = false)
data class AndroidEssenceFeed(
    @field:ElementList(name = "entry", inline = true)
    @param:ElementList(name = "entry", inline = true)
    val items: List<AndroidEssenceFeedItem>
)
