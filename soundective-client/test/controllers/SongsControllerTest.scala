package controllers

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

  private val title = "Something"
  private var songToTest: Song = null;

  @Before
  def setUp {
    Fixtures.deleteAll
    Fixtures.load("songs/fixtures/songs.yml")

    songToTest = Song.findByTitle(title).head
  }

  @Test
  def songsTest {
    //One songs in test : Something

    val response = GET("/songs")
    response shouldBeOk()
    response contentTypeShouldBe("application/json")
    response charsetShouldBe("utf-8")
    response contentShouldBe("[{\"title\":\"" + songToTest.title + "\"," +
                              "\"number\":" + songToTest.number + "," +
                              "\"path\":\"" + songToTest.path + "\"," +
                              "\"mimeType\":\"" + songToTest.mimeType + "\"," +
                              "\"songType\":\"" + songToTest.songType + "\"," +
                              "\"id\":" + songToTest.id + "}]")
  }

  @Test
  def songTest {
    val response = GET("/song/" + songToTest.id)
    response shouldBeOk()
    response contentTypeShouldBe(songToTest.mimeType)
  }
}