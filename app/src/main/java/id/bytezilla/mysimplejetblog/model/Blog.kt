package id.bytezilla.mysimplejetblog.model

data class Blog(
    val id: Long,
    val title: String,
    val fullContent: String,
    val imageUrl: Int,
    val author: String,
    val date: String
)
