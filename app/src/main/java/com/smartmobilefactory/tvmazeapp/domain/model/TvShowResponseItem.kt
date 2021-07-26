package com.smartmobilefactory.tvmazeapp.domain.model

import java.io.Serializable

data class TvShowResponseItem(
    val score: Double,
    val show: Show
): Serializable