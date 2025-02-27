package com.example.bistroftchallenge.domain.model

import com.example.bistroftchallenge.data.remote.joke.dto.Flags

data class Joke(
    val error: Boolean,
    val category: String,
    val type: String,
    val setup: String? = null,
    val delivery: String? = null,
    val joke: String? = null,
    val flags: Flags,
    val safe: Boolean,
    val id: Int,
    val lang: String
)
