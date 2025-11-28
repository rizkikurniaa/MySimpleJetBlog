package id.bytezilla.mysimplejetblog.data

import id.bytezilla.mysimplejetblog.model.Blog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update

class BlogRepository {

    private val dummyBlogs = mutableListOf<Blog>()

    init {
        if (dummyBlogs.isEmpty()) {
            for (i in 1..15) {
                dummyBlogs.add(
                    Blog(
                        id = i.toLong(),
                        title = "Blog Post Title $i",
                        description = "This is a short description for blog post $i. It gives a quick overview.",
                        fullContent = "This is the full content for blog post $i. It contains much more detail than the description. Here you can read about all the specifics of the topic covered in this blog post. It is very interesting and informative. \n\nAdditional paragraph for detail.",
                        imageUrl = "https://picsum.photos/id/${10 + i}/300/200",
                        author = "Author Name $i",
                        date = "May ${i}, 2024"
                    )
                )
            }
        }
    }

    private val _blogs = MutableStateFlow<List<Blog>>(dummyBlogs)
    val blogs: Flow<List<Blog>> get() = _blogs

    fun getBlogById(id: Long): Blog? {
        return _blogs.value.find { it.id == id }
    }

    fun searchBlogs(query: String): Flow<List<Blog>> {
        return _blogs.map { list ->
            if (query.isBlank()) {
                list
            } else {
                list.filter { it.title.contains(query, ignoreCase = true) }
            }
        }
    }

    fun addBlog(blog: Blog) {
        val currentList = _blogs.value.toMutableList()
        currentList.add(0, blog) // Add to top
        _blogs.value = currentList
    }

    fun removeBlog(blogId: Long) {
        val currentList = _blogs.value.toMutableList()
        currentList.removeAll { it.id == blogId }
        _blogs.value = currentList
    }
    
    companion object {
        @Volatile
        private var instance: BlogRepository? = null
        
        fun getInstance(): BlogRepository =
            instance ?: synchronized(this) {
                instance ?: BlogRepository().also { instance = it }
            }
    }
}
