package com.example.registerwithapi.ui.theme.Cliente

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.registerwithapi.data.remote.dto.ClienteDto
import com.example.registerwithapi.ui.theme.Navigation.Destination
import kotlinx.coroutines.launch

@Composable
fun Consult(clientes: List<ClienteDto>, navController: NavController, onClienteClick: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = "Lista de clientes", style = MaterialTheme.typography.titleMedium)

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(clientes) { cliente ->
                ClienteItem(cliente, navController = navController){
                    onClienteClick(it)
                }
            }
        }
    }
}

@Composable
fun ClienteItem(
    cliente: ClienteDto,
    viewModel: ClienteViewModel = hiltViewModel(),
    navController: NavController,
    onClienteClick: (Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(onClick = { cliente.clienteId?.let { onClienteClick(it) } }),
    ) {
        Column(
            modifier = Modifier.padding(16.dp).clickable(onClick = { cliente.clienteId?.let { onClienteClick(it) } }),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = cliente.nombres, style = MaterialTheme.typography.titleMedium)
            Text(text = cliente.direccion, style = MaterialTheme.typography.titleMedium)
            Text(text = cliente.rnc, style = MaterialTheme.typography.titleMedium)
            Text(
                text = cliente.limiteCredito.toString(),
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}