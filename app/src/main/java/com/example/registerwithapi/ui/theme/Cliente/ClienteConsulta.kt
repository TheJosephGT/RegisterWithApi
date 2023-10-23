package com.example.registerwithapi.ui.theme.Cliente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.registerwithapi.data.remote.dto.ClienteDto

@Composable
fun Consult(clientes: List<ClienteDto>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Lista de clientes", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(clientes) { cliente ->
                ClienteItem(cliente)
            }
        }
    }
}

@Composable
fun ClienteItem(cliente: ClienteDto, viewModel: ClienteViewModel = hiltViewModel()) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = cliente.nombres, style = MaterialTheme.typography.titleMedium)
            Text(text = cliente.direccion, style = MaterialTheme.typography.titleMedium)
            Text(text = cliente.rnc, style = MaterialTheme.typography.titleMedium)
            Text(
                text = cliente.limiteCredito.toString(),
                style = MaterialTheme.typography.titleMedium
            )

            Button(
                onClick = {
                    cliente.clienteId?.let { viewModel.deleteCliente(it, cliente) }
                }
            ) {
                Text(text = "Eliminar")
            }
        }
    }
}