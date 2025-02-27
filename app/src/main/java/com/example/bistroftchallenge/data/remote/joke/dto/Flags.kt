package com.example.bistroftchallenge.data.remote.joke.dto

import kotlinx.serialization.Serializable

@Serializable
data class Flags(
    val nsfw: Boolean,
    val religious: Boolean,
    val political: Boolean,
    val racist: Boolean,
    val sexist: Boolean,
    val explicit: Boolean
)
