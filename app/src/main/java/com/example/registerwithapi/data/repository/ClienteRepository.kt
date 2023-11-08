package com.example.registerwithapi.data.repository

import com.example.registerwithapi.data.remote.dto.ClienteApi
import com.example.registerwithapi.data.remote.dto.ClienteDto
import com.example.registerwithapi.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class ClienteRepository @Inject constructor(private val api: ClienteApi) {

    fun getCliente(): Flow<Resource<List<ClienteDto>>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val cliente = api.getCliente() //descarga las monedas de internet, se supone quedemora algo

            emit(Resource.Success(cliente)) //indicar que se cargo correctamente.
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }
    fun getTicketsId(id: Int): Flow<Resource<ClienteDto>> = flow {
        try {
            emit(Resource.Loading()) //indicar que estamos cargando

            val tickets =
                api.getClienteById(id) //descagar la lista de tickets por el id

            emit(Resource.Success(tickets)) //indicar que se cargo correctamente
        } catch (e: HttpException) {
            //error general HTTP
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))
        } catch (e: IOException) {
            //debe verificar tu conexion a internet
            emit(Resource.Error(e.message ?: "verificar tu conexion a internet"))
        }
    }

    suspend fun postCliente(cliente: ClienteDto) {
        api.postCliente(cliente)
    }
    suspend fun deleteCliente(id: Int, cliente: ClienteDto) {
        api.deleteCliente(id, cliente)
    }
    suspend fun putCliente(id:Int, cliente: ClienteDto) {
        api.putCliente(id, cliente)
    }
}