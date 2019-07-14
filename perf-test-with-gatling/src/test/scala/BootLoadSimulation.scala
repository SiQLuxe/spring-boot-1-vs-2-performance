import ch.qos.logback.classic.{Level, Logger}
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import org.slf4j.LoggerFactory

class BootLoadSimulation extends Simulation {

  LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME).asInstanceOf[Logger].setLevel(Level.ERROR)

  private val baseUrl = "http://localhost:8090"
  private val endpoint = "/persons"
  private val contentType = "application/json"
  private val requestCount = 1

  private val simUsers = System.getProperty("SIM_USERS", "30000").toInt

  private val httpConf = http
    .baseURL(baseUrl)
    .acceptHeader("application/json;charset=UTF-8")

  private val addPersonTest = repeat(requestCount, "n") {
    exec(http("add-person-test")
      .post(endpoint)
      .header("Content-Type", contentType)
      .body(StringBody(
        s"""
           | {
           |  "firstName": "test-firstName",
           |  "lastName": "test-lastName"
           | }
         """.stripMargin
      )).check(status.is(201)))
  }
  private val scn = scenario("BootLoadSimulation")
    .exec(addPersonTest)

  setUp(scn.inject(atOnceUsers(simUsers))).protocols(httpConf)
}
