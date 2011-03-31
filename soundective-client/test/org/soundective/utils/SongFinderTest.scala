package org.soundective.utils

import java.io.File
import org.junit._
import org.junit.Assert._
import play.test._
import play.Play.configuration

class SongFinderTest extends UnitTest {

  @Test
  def countSongsASynchronously {
    var count: Long = 0
    new SongFinder(new File(configuration.getProperty("soundective.song.directory")), file => count = count + 1).start

    assertTrue(count == 0)
  }

  @Test
  def countSongsSynchronously {
    var count: Long = 0
    new SongFinder(new File(configuration.getProperty("soundective.song.directory")), file => count = count + 1).act

    assertTrue(count > 0)
  }

  @Test
  def nullFindTest {
    try {
      new SongFinder(null, null).act
    } catch {
      case e: Exception => fail
    }

    assertTrue(true)
  }

  @Test
  def findAndNullTest {
    try {
      new SongFinder(new File(configuration.getProperty("soundective.song.directory")), null).act
    } catch {
      case e: Exception => fail
    }

    assertTrue(true)
  }

  @Test
  def nullFindAndCountTest {
    var count: Long = 0
    new SongFinder(null, file => count = count + 1).act
    assertTrue(count == 0)
  }
}
