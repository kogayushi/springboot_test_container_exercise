package springboottestcontainerexercise.example

import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName


abstract class AbstractContainerBaseTest {

    companion object {

        val MY_SQL_CONTAINER = MySQLContainer<Nothing>(DockerImageName.parse("mysql")).apply {
            withUsername("example")
            withPassword("example")
            withDatabaseName("testcontainers")
            withConfigurationOverride("mysql")
            start()
        }
    }
}
