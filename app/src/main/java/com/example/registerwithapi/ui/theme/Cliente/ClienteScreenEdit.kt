package com.example.registerwithapi.ui.theme.Cliente

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ClienteScreenEdit(
    viewModel: ClienteViewModel = hiltViewModel(),
    navController: NavController,
    clienteId: Int,
    onSaveClick: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.isMessageShownFlow.collectLatest {
            if (it) {
                snackbarHostState.showSnackbar(
                    message = "Cliente guardado",
                    duration = SnackbarDuration.Short
                )
            }
        }
    }

    remember {
        viewModel.getClienteById(clienteId)
        0
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        modifier = Modifier.fillMaxSize(),
        topBar = {
            RefreshAppBar(title = "Clientes") {
                viewModel.limpiar()
            }
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(8.dp)
        ) {
            val keyboardController = LocalSoftwareKeyboardController.current
            Text(text = "Cliente detalles EDITADO", style = MaterialTheme.typography.titleMedium)

            CustomOutlinedTextField(
                value = viewModel.nombres,
                onValueChange = { viewModel.nombres = it },
                label = "Nombres",
                isError = viewModel.nombreError,
                imeAction = ImeAction.Next
            )
            CustomOutlinedTextField(
                value = viewModel.rnc,
                onValueChange = { viewModel.rnc = it },
                label = "Rnc",
                isError = viewModel.rncError,
                imeAction = ImeAction.Next
            )
            CustomOutlinedTextField(
                value = viewModel.direccion,
                onValueChange = { viewModel.direccion = it },
                label = "Direccion",
                isError = viewModel.direccionError,
                imeAction = ImeAction.Next
            )
            OutlinedTextField(
                value = viewModel.limiteCredito.toString(),
                label = { Text(text = "Limite credito") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {
                    val newValue = it.toIntOrNull()
                    if (newValue != null) {
                        viewModel.limiteCredito = newValue
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedButton(onClick = {
                keyboardController?.hide()
                if (viewModel.Validar()) {
                    viewModel.setMessageShown()
                    viewModel.updateCliente()
                    onSaveClick()
                    //navController.navigate(Destination.ConsultaClientes.route)
                }
            }, modifier = Modifier.fillMaxWidth())
            {
                Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Guardar")
                Text(text = "Guardar")
            }
        }
    }
}