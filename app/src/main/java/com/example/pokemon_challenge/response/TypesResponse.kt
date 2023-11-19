package com.example.pokemon_challenge.response

import com.google.gson.annotations.SerializedName

data class TypesResponse(

	@field:SerializedName("generation")
	val generation: Generation? = null,

	@field:SerializedName("game_indices")
	val gameIndices: List<GameIndicesItem?>? = null,

	@field:SerializedName("move_damage_class")
	val moveDamageClass: MoveDamageClass? = null,

	@field:SerializedName("names")
	val names: List<NamesItem?>? = null,

	@field:SerializedName("pokemon")
	val pokemon: List<PokemonItem?>? = null,

	@field:SerializedName("damage_relations")
	val damageRelations: DamageRelations? = null,

	@field:SerializedName("moves")
	val moves: List<MovesItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("past_damage_relations")
	val pastDamageRelations: List<Any?>? = null,

	@field:SerializedName("id")
	val id: Int? = null
)

data class Generation(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class NoDamageToItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Language(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class PokemonItem(

	@field:SerializedName("pokemon")
	val pokemon: Pokemon? = null,

	@field:SerializedName("slot")
	val slot: Int? = null
)

data class DamageRelations(

	@field:SerializedName("no_damage_from")
	val noDamageFrom: List<NoDamageFromItem?>? = null,

	@field:SerializedName("half_damage_from")
	val halfDamageFrom: List<Any?>? = null,

	@field:SerializedName("no_damage_to")
	val noDamageTo: List<NoDamageToItem?>? = null,

	@field:SerializedName("half_damage_to")
	val halfDamageTo: List<HalfDamageToItem?>? = null,

	@field:SerializedName("double_damage_to")
	val doubleDamageTo: List<Any?>? = null,

	@field:SerializedName("double_damage_from")
	val doubleDamageFrom: List<DoubleDamageFromItem?>? = null
)

data class MovesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class NoDamageFromItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class GameIndicesItem(

	@field:SerializedName("generation")
	val generation: Generation? = null,

	@field:SerializedName("game_index")
	val gameIndex: Int? = null
)

data class DoubleDamageFromItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class Pokemon(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class HalfDamageToItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)

data class NamesItem(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("language")
	val language: Language? = null
)

data class MoveDamageClass(

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null
)
