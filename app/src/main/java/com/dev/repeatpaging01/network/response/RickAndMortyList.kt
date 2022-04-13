package com.dev.repeatpaging01.network.response

data class RickAndMortyList (val info: Info, val results: List<CharacterData>)
data class Info(val count:String?, val pages:String?, val next:String?, val prev:String?)
data class CharacterData(val name:String?, val species:String?, val image: String?)
