import models.Song
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

    //TODO: FIXME: Do the test with a real pattern but plateform independant
    //response contentShouldBe("{'name':'Something'}")

  }

  @Test
  def songTest {
    val id = 1L
    val song = Song.findById(id).head

    val response = GET("/song/" + song.id)
    response shouldBeOk()
    response contentTypeShouldBe(song.mimeType)
  }
}