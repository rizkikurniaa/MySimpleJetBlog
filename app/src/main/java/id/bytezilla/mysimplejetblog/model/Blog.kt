package id.bytezilla.mysimplejetblog.model

data class Blog(
    val id: Long,
    val title: String,
    val fullContent: String,
    val imageUrl: String,
    val author: String,
    val date: String
)
