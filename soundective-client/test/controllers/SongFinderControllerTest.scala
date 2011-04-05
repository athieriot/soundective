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

  @Test
  def songFinderTest {
    val response = GET("/finderState")
    response shouldBeOk()
    response contentTypeShouldBe("application/json")
    response charsetShouldBe("utf-8")
  }

  /* TODO: A test for when we'll be able to control finding
  def songFinderNoContent {
    //Stop the finding service
    val response = GET("/finderState")
    response statusShouldBe(204)
  }
  */
}