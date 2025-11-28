package id.bytezilla.mysimplejetblog.data

import id.bytezilla.mysimplejetblog.model.Blog
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class BlogRepository {

    private val dummyBlogs = mutableListOf<Blog>()

    init {
        if (dummyBlogs.isEmpty()) {
            dummyBlogs.addAll(
                listOf(
                    Blog(
                        id = 1,
                        title = "Understanding Jetpack Compose",
                        fullContent = "Jetpack Compose is Android’s modern toolkit for building native UI. It simplifies and accelerates UI development on Android. \n\nIn this article, we will explore the basics of Composables, Modifiers, and state management. We will start by setting up a new project and building a simple 'Hello World' application. Then we will dive deeper into layouts using Column, Row, and Box. Finally, we'll touch upon State hoisting and recomposition.\n\nCompose is declarative, meaning you describe what your UI should look like, and the framework handles the rendering updates when the state changes.",
                        imageUrl = "https://picsum.photos/seed/compose/600/400",
                        author = "Rizki Kurniawan, S.Kom",
                        date = "May 10, 2024"
                    ),
                    Blog(
                        id = 2,
                        title = "Mastering Kotlin Coroutines",
                        fullContent = "Coroutines are a powerful feature in Kotlin that simplify asynchronous programming. They allow you to write asynchronous code in a sequential manner.\n\nThis post covers the basics of suspend functions, CoroutineScopes, and Dispatchers. We'll also look at how to use structured concurrency to ensure no coroutines are leaked. We will demonstrate fetching data from a network API and updating the database without blocking the main thread.\n\nBy the end of this guide, you'll be comfortable using launch and async builders in your Android apps.",
                        imageUrl = "https://picsum.photos/seed/coroutines/600/400",
                        author = "Sarah Dev",
                        date = "May 12, 2024"
                    ),
                    Blog(
                        id = 3,
                        title = "Dependency Injection with Hilt",
                        fullContent = "Hilt is a dependency injection library for Android that reduces the boilerplate of doing manual dependency injection in your project.\n\nWe will discuss how to set up Hilt in your project, create modules, and inject dependencies into ViewModels and Activities. Hilt integrates seamlessly with Jetpack libraries and helps in making your code more testable and maintainable.\n\nWe'll walk through a practical example of injecting a Repository into a ViewModel.",
                        imageUrl = "https://picsum.photos/seed/hilt/600/400",
                        author = "Rizki Kurniawan, S.Kom",
                        date = "May 15, 2024"
                    ),
                    Blog(
                        id = 4,
                        title = "Building Beautiful UIs with Material 3",
                        fullContent = "Material 3 is the latest evolution of Google's open-source design system. It brings dynamic color, new typography scales, and updated components.\n\nIn this blog, we will see how to implement Material 3 in a Jetpack Compose application. We will explore dynamic color theming based on the user's wallpaper, updated card designs, and navigation components. Let's make your app look modern and consistent with the Android ecosystem.",
                        imageUrl = "https://picsum.photos/seed/material3/600/400",
                        author = "Sulistya Ningrum, S.E.",
                        date = "May 18, 2024"
                    ),
                    Blog(
                        id = 5,
                        title = "Room Database Essentials",
                        fullContent = "Room provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.\n\nThis tutorial covers defining Entities, DAOs (Data Access Objects), and the Database class. We will also discuss how to handle migrations and use TypeConverters for complex data types. Storing data locally is crucial for offline-first applications.",
                        imageUrl = "https://picsum.photos/seed/room/600/400",
                        author = "Rizki Kurniawan, S.Kom",
                        date = "May 20, 2024"
                    ),
                    Blog(
                        id = 6,
                        title = "Navigation in Compose",
                        fullContent = "Navigation Compose is the go-to library for handling navigation in Jetpack Compose apps. It allows you to define your navigation graph using a Kotlin DSL.\n\nWe will learn how to set up the NavController, define routes, and pass arguments between screens. We will also cover nested navigation graphs and integrating the bottom navigation bar. Proper navigation structure is key to a good user experience.",
                        imageUrl = "https://picsum.photos/seed/navigation/600/400",
                        author = "Bytezilla Studio",
                        date = "May 22, 2024"
                    ),
                    Blog(
                        id = 7,
                        title = "Optimizing App Performance",
                        fullContent = "Performance matters. Users expect apps to be fast and responsive. In this article, we discuss tools like Profiler and Layout Inspector.\n\nWe will look at common performance pitfalls such as memory leaks, overdraw, and unoptimized bitmaps. We'll also discuss Baseline Profiles to improve app startup time. Small optimizations can lead to significant improvements in user satisfaction.",
                        imageUrl = "https://picsum.photos/seed/perform/600/400",
                        author = "Rizki Kurniawan, S.Kom",
                        date = "May 25, 2024"
                    ),
                    Blog(
                        id = 8,
                        title = "The Power of Retrofit",
                        fullContent = "Retrofit is a type-safe HTTP client for Android and Java. It turns your HTTP API into a Java interface.\n\nWe will guide you through setting up Retrofit with Moshi or Gson for JSON parsing. We'll cover handling GET, POST, PUT, and DELETE requests, as well as managing authentication headers and error handling. Networking is a fundamental part of most modern apps.",
                        imageUrl = "https://picsum.photos/seed/retrofit/600/400",
                        author = "Bytezilla Studio",
                        date = "May 28, 2024"
                    ),
                    Blog(
                        id = 9,
                        title = "Unit Testing in Android",
                        fullContent = "Testing is not just an afterthought; it's a crucial part of the development process. Unit tests verify the correctness of small units of code.\n\nIn this post, we will explore writing unit tests for ViewModels and UseCases. We will use MockK for mocking dependencies and Truth for assertions. Writing tests gives you the confidence to refactor and add features without breaking existing functionality.",
                        imageUrl = "https://picsum.photos/seed/testing/600/400",
                        author = "Sulistya Ningrum, S.E.",
                        date = "May 30, 2024"
                    ),
                    Blog(
                        id = 10,
                        title = "Clean Architecture Guide",
                        fullContent = "Clean Architecture separates your code into layers with specific responsibilities: Presentation, Domain, and Data.\n\nWe will discuss the benefits of this separation of concerns. The Domain layer contains the business logic, the Data layer handles data retrieval, and the Presentation layer manages the UI. This architecture makes your app easier to test, maintain, and scale over time.",
                        imageUrl = "https://picsum.photos/seed/cleanarch/600/400",
                        author = "Sulistya Ningrum, S.E.",
                        date = "June 1, 2024"
                    ),
                    Blog(
                        id = 11,
                        title = "Animating with Compose",
                        fullContent = "Animations can make your app feel more polished and intuitive. Compose provides high-level APIs like AnimatedVisibility and low-level APIs for custom animations.\n\nWe will create simple fade-in effects, animate content size changes, and build complex transitions between screens. Learn how to create delightful interactions that engage your users.",
                        imageUrl = "https://picsum.photos/seed/animation/600/400",
                        author = "Rizki Kurniawan, S.Kom",
                        date = "June 3, 2024"
                    ),
                    Blog(
                        id = 12,
                        title = "WorkManager for Background Tasks",
                        fullContent = "WorkManager is the recommended solution for persistent background work that needs to run even if the app exits or the device restarts.\n\nThis article explains when to use WorkManager versus other background solutions. We will implement a OneTimeWorkRequest and a PeriodicWorkRequest. We'll also cover constraints like requiring network connectivity or charging status.",
                        imageUrl = "https://picsum.photos/seed/workmanager/600/400",
                        author = "Rizki Kurniawan, S.Kom",
                        date = "June 5, 2024"
                    )
                )
            )
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
