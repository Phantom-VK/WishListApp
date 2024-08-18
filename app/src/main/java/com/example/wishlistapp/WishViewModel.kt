import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishlistapp.data.Graph
import com.example.wishlistapp.data.Wish
import com.example.wishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository: WishRepository = Graph.wishRepository // Dependency injection of the WishRepository
) : ViewModel() {

    // State to hold the title of the wish
    var wishTitleState by mutableStateOf("")
    // State to hold the description of the wish
    var wishDescription by mutableStateOf("")

    // Function to update the wish title state
    fun onWishTitleChange(newString: String) {
        wishTitleState = newString
    }

    // Function to update the wish description state
    fun onWishDescriptionChange(newString: String) {
        wishDescription = newString
    }

    // StateFlow to hold the list of all wishes, initialized lazily
    val getAllWishes: StateFlow<List<Wish>> = wishRepository.getWishes()
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    // Function to add a new wish
    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }

    // Function to get a wish by its ID
    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

    // Function to update an existing wish
    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }

    // Function to delete a wish
    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
}
