package com.victor.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.IntegerRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val nameCourseResourceId: Int,
    val codeCourseResourceId: Int,
    @DrawableRes val imageCourseResourceId: Int
)
