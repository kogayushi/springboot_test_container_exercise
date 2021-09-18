package springboottestcontainerexercise.example

import org.springframework.data.repository.CrudRepository

interface ExampleRepository : CrudRepository<Example, String?> {

    fun findByContent(content: String): Example
}
