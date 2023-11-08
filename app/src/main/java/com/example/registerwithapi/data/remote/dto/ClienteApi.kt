package com.example.registerwithapi.data.remote.dto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClienteApi {
    @GET("/api/Clientes")
    suspend fun getCliente():List<ClienteDto>

    @GET("/api/Clientes/{id}")
    suspend fun getClienteById(@Path("id") id: Int): ClienteDto

    @POST("/api/Clientes")
    suspend fun postCliente(@Body cliente: ClienteDto) : Response<ClienteDto>

    @PUT("/api/Clientes/{id}")
    suspend fun putCliente(@Path("id") id:Int, @Body cliente: ClienteDto): Response<Unit>

    @DELETE("/api/Clientes/{id}")
    suspend fun deleteCliente(@Path("id") id: Int, @Body cliente: ClienteDto): Response<Unit>
}