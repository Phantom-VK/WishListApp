import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.wishlistapp.AppBarView
import com.example.wishlistapp.Screen
import com.example.wishlistapp.data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailView(
    id: Long, // ID of the wish, used to determine if we are editing or adding a new wish
    viewModel: WishViewModel, // ViewModel for managing UI-related data
    navController: NavController // Navigation controller to handle navigation between screens
) {
    // Mutable state to hold the message to be displayed in the snackbar
    val snackMessage = remember { mutableStateOf("") }
    val scope = rememberCoroutineScope() // Coroutine scope to launch coroutines
    val snackbarHostState = remember { SnackbarHostState() } // State for managing Snackbar

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }, // Display the Snackbar when needed
        topBar = {
            AppBarView(
                title = if (id != 0L) "Update Wish" else "Add Wish", // Set title based on the action
                onBackNavClicked = { navController.navigateUp() } // Navigate up on back press
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
            // TextField for entering the wish title
            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChange = { newTitle -> viewModel.onWishTitleChange(newTitle) }
            )
            Spacer(modifier = Modifier.height(10.dp))
            // TextField for entering the wish description
            WishTextField(
                label = "Description",
                value = viewModel.wishDescription,
                onValueChange = { newDescription -> viewModel.onWishDescriptionChange(newDescription) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            // Button for saving or updating the wish
            Button(onClick = {
                if (viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescription.isNotEmpty()) {
                    if (id != 0L) {
                        // Logic for updating an existing wish
                        // viewModel.updateWish(...)
                    } else {
                        // Logic for adding a new wish
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
                // Show the snackbar with the appropriate message
                scope.launch {
                    snackbarHostState.showSnackbar(snackMessage.value)
                }
            }) {
                Text(
                    text = if (id != 0L) "Update Wish" else "Add Wish", // Button text changes based on the action
                    style = TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String, // Label for the TextField
    value: String, // Current value of the TextField
    onValueChange: (String) -> Unit // Lambda to handle value change
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) }, // Display label
        modifier = Modifier.fillMaxWidth(), // TextField takes up the full width
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text), // Set keyboard type to text
        colors = OutlinedTextFieldDefaults.colors() // Default colors for the TextField
    )
}
