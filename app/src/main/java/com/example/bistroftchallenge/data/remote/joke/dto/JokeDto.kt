package com.example.bistroftchallenge.data.remote.joke.dto

import com.example.bistroftchallenge.domain.model.Joke
import kotlinx.serialization.Serializable

@Serializable
data class JokeDto(
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
){
    fun toDomain() : Joke{
       return Joke(
            error = this.error,
            category = this.category,
            type = this.type,
            setup = this.setup,
            delivery = this.delivery,
            joke = this.joke,
            flags = this.flags,
            safe = this.safe,
            id = this.id,
            lang = this.lang
        )
    }
}
