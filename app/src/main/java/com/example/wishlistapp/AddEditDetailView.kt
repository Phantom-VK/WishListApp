import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp.AppBarView
import com.example.wishlistapp.Screen
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long, viewModel: WishViewModel, navController: NavController
) {
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            AppBarView(
                title = if (id != 0L) "Update Wish" else "Add Wish",
                onBackNavClicked = { navController.navigateUp() }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChange = { newTitle -> viewModel.onWishTitleChange(newTitle) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = viewModel.wishDescription,
                onValueChange = { newDescription -> viewModel.onWishDescriptionChange(newDescription) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescription.isNotEmpty()) {
                    if (id != 0L) {
                        // UpdateWish logic
                        // viewModel.updateWish(...)
                    } else {
                        viewModel.addWish(
                            Wish(
                                title = viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescription.trim()
                            )
                        )
                        snackMessage.value = "Wish has been created"
                        navController.navigate(Screen.HomeScreen.route)
                    }
                } else {
                    snackMessage.value = "Enter data to create a wish"
                }
                scope.launch {
                    snackbarHostState.showSnackbar(snackMessage.value)
                }
            }) {
                Text(
                    text = if (id != 0L) "Update Wish" else "Add Wish",
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors()
    )
}
