package default

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class RecordedSimulation extends Simulation {

    val httpConf = http
        .baseURL("###TOKEN_VALID_URL###")
        .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
        .doNotTrackHeader("1")
        .acceptLanguageHeader("en-US,en;q=0.5")
        .acceptEncodingHeader("gzip, deflate")
        .userAgentHeader("Mozilla/5.0 (Windows NT 5.1; rv:31.0) Gecko/20100101 Firefox/31.0")

    val scn = scenario("RecordedSimulation")
        .exec(http("request_1")
        .get("/"))
        .pause(7)

    setUp(scn.inject(rampUsers(100) over (10 seconds)))
        .protocols(httpConf)
        .assertions(global.responseTime.max.lessThan(###TOKEN_RESPONSE_TIME###))
}
