package com.tritiumstudios.phasmophobiaevidencepicker

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform