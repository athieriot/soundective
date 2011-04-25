package controllers

import play.test.{FunctionalTest, Browser, Matchers}
import play.test._
import org.junit._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 05/04/11
 * Time: 21:28
 */

class SongFinderControllerTest extends FunctionalTest with Browser with Matchers {

  @Before
  def setUp {
    Fixtures.deleteAllModels
    Fixtures.loadModels("songs/fixtures/songs.yml")
  }

  @Test
  def songFinderTest {
    val response = GET("/finderState")
    response shouldBeOk()
    response contentTypeShouldBe("application/json")
    response charsetShouldBe("utf-8")
    response contentShouldBe("{files: 9, songs: 6}")
  }

  def songFinderNoContent {
    Fixtures.deleteAllModels

    val response = GET("/finderState")
    response statusShouldBe(204)
  }

}