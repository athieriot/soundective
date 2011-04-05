package org.soundective.utils.songFinders

import java.io.File
import org.junit._
import org.junit.Assert._
import play.test._
import play.Play.configuration

class SongFinderTest extends UnitTest {

  //TODO: An alternative to time_pause need to be find
  private val time_pause = 50

  @Test
  def countSongsASynchronously {
    var count: Long = 0
    new SongFinder(new File(configuration.getProperty("soundective.song.directory")), file => {count = count + 2; pause(time_pause)}).start

    assertTrue(count == 0)
  }

  @Test
  def songFinderDetailsTest {
    var songFinderTest = new SongFinder(new File(configuration.getProperty("soundective.song.directory")), file => None)
    songFinderTest.start
    pause(time_pause)

    var result = songFinderTest.getDetails
    assertNotNull(result)
    assertTrue(result._1 > 0)
    assertTrue(result._2 > 0)
  }

  @Test
  def countSongsAndWait {
    var count: Long = 0
    new SongFinder(new File(configuration.getProperty("soundective.song.directory")), file => count = count + 1).start
    pause(time_pause)

    assertTrue(count > 1)
  }

  @Test
  def nullFindTest {
    try {
      new SongFinder(null, null).start
    } catch {
      case e: Exception => fail
    }

    assertTrue(true)
  }

  @Test
  def findAndNullTest {
    try {
      new SongFinder(new File(configuration.getProperty("soundective.song.directory")), null).start
    } catch {
      case e: Exception => fail
    }

    assertTrue(true)
  }

  @Test
  def nullFindAndCountTest {
    var count: Long = 0
    new SongFinder(null, file => count = count + 1).start
    assertTrue(count == 0)
  }
}
