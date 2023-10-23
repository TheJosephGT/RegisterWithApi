package com.example.registerwithapi.data.remote.dto


data class ClienteDto(
    val clienteId: Int?=null,
    var nombres:String="",
    var rnc:String= "",
    var direccion:String="",
    var limiteCredito:Int?=null
)