package com.kimmandoo.nutrisport

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform