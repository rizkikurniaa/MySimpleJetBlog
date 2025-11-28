package id.bytezilla.mysimplejetblog.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import id.bytezilla.mysimplejetblog.data.BlogRepository
import id.bytezilla.mysimplejetblog.model.Blog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn

class BlogViewModel(private val repository: BlogRepository) : ViewModel() {

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState: StateFlow<List<Blog>> = _query
        .flatMapLatest { query ->
            repository.searchBlogs(query)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    fun searchBlogs(newQuery: String) {
        _query.value = newQuery
    }

    fun getBlogById(id: Long): Blog? {
        return repository.getBlogById(id)
    }

    fun addBlog() {
        val newId = System.currentTimeMillis()
        val topics = listOf(
            "Advanced Kotlin Tips",
            "Understanding ProGuard",
            "Gradle Build Optimization",
            "Android Security Best Practices",
            "Accessibility in Compose",
            "Testing with Espresso"
        )
        val randomTopic = topics.random()

        val newBlog = Blog(
            id = newId,
            title = randomTopic,
            fullContent = "This is a new article about $randomTopic. \n\nHere we will dive deep into the implementation details and best practices. \n\nAndroid development is constantly evolving, and keeping up with $randomTopic is essential for modern developers.",
            imageUrl = "https://picsum.photos/seed/${newId}/600/400",
            author = "Admin Blog",
            date = "Just Now"
        )
        repository.addBlog(newBlog)
    }

    fun removeBlog(blogId: Long) {
        repository.removeBlog(blogId)
    }
}

class ViewModelFactory(private val repository: BlogRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BlogViewModel::class.java)) {
            return BlogViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
