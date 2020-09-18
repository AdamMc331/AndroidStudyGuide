package com.adammcneilly.androidstudyguide.data.remote.androidessence

import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Root(name = "category", strict = false)
data class AndroidEssenceCategory(
    @field:Attribute(name = "term")
    @param:Attribute(name = "term")
    val term: String? = null
)
