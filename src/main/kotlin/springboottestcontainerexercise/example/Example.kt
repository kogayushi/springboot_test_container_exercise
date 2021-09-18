package springboottestcontainerexercise.example

import org.springframework.data.annotation.Id

data class Example(
    @Id
    val id: Int?,
    val content: String,
)
