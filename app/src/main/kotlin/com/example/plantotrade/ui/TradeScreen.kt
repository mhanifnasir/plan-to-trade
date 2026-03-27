import androidx.compose.material.*nimport androidx.compose.runtime.*nimport androidx.compose.ui.tooling.preview.Previewnimport androidx.compose.ui.unit.dp

@Composable
fun TradeScreen() {
    var selectedCoin by remember { mutableStateOf("Bitcoin") }
    var quantity by remember { mutableStateOf("") }
    var livePrice by remember { mutableStateOf("$0.00") }

    val coins = listOf("Bitcoin", "Ethereum", "Litecoin")

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Select Coin:")
        DropdownMenu(expanded = true, onDismissRequest = { /* TODO: Dismiss */ }) {
            coins.forEach { coin ->
                DropdownMenuItem(onClick = { selectedCoin = coin }) {
                    Text(text = coin)
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = quantity,
            onValueChange = { quantity = it },
            label = { Text("Enter Quantity") }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Live Price: $livePrice")

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = { /* TODO: Calculate signals */ }) {
            Text(text = "Calculate Signals")
        }
    }
}

@Preview
@Composable
fun PreviewTradeScreen() {
    TradeScreen()
}