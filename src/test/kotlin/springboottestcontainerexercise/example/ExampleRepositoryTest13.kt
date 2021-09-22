package springboottestcontainerexercise.example

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
private open class ExampleRepositoryTest13 : AbstractContainerBaseTest() {

    companion object {

        @DynamicPropertySource
        @JvmStatic
        fun setUp(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl)
            registry.add("spring.datasource.username", MY_SQL_CONTAINER::getUsername)
            registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword)
        }
    }

    @AfterEach
    fun tearDown() {
        exampleRepository.deleteAll()
    }

    @Autowired
    private lateinit var exampleRepository: ExampleRepository


    @Test
    fun execute() {

        // before check
        val all = exampleRepository.findAll()
        assertThat(all).isEmpty()

        // set up
        val mockContent = "2569eddf-521b-4a50-a6f4-b036b4206180"
        val example = Example(
            id = null,
            content = mockContent,
        )

        // exercise
        exampleRepository.save(
            example
        )

        // verify
        val inserted = exampleRepository.findByContent(mockContent)

        assertThat(inserted.content).isEqualTo(mockContent)

        val waitTime = 5000L
        println("will wait for $waitTime milliseconds")
        Thread.sleep(waitTime)
    }
}
