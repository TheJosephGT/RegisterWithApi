package com.example.registerwithapi.data.remote.dto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ClienteApi {
    @GET("/api/Clientes")
    suspend fun getCliente():List<ClienteDto>

    @GET("/api/Clientes/{id}")
    suspend fun getClienteById(@Path("id") clienteId: Int): ClienteDto

    @POST("/api/Clientes")
    suspend fun postCliente(@Body cliente: ClienteDto) : Response<ClienteDto>

    @DELETE("/api/Clientes/{id}")
    suspend fun deleteCliente(@Path("id") id: Int, @Body clienteDto: ClienteDto): Response<Unit>
}