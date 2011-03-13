import play.test._
import org.junit._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:41
 */

class SongsControllerTest extends FunctionalTest with Browser with Matchers {

  @Test
  def songsTest {
    //Two songs in test : Something, Morning Noon & Night

    val response = GET("/songs")
    response shouldBeOk()
    response contentTypeShouldBe("application/json")
    response charsetShouldBe("utf-8")
    response contentShouldBe("[" +
                              "{" +
                                "\"name\":\"Something\"," +
                                "\"number\":1," +
                                "\"path\":\"/Users/aurelien/Dropbox/sources/soundective/soundective-client/test/songs/02 - Something.mp3\"," +
                                "\"id\":1" +
                              "}," +
                              "{" +
                                "\"name\":\"Morning Noon \\u0026 Night\"," +
                                "\"number\":1," +
                                "\"path\":\"/Users/aurelien/Dropbox/sources/soundective/soundective-client/test/songs/The Limes - Morning Noon  Night.mp3\"," +
                                "\"id\":2" +
                              "}" +
                             "]")
  }
}