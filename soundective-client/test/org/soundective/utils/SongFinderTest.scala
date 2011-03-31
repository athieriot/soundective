package org.soundective.utils

import java.io.File
import org.junit._
import org.junit.Assert._
import play.test._
import play.Play.configuration

class SongFinderTest extends UnitTest {

  @Test
  def defaultFindAndCountWithAlwaysFilterTest {
    var count: Long = 0
    SongFinder.findFilterAndDo(new File(configuration.getProperty("soundective.song.directory")), file => true, file => count = count + 1)
    assertTrue(count > 0)
  }

  @Test
  def nullFindTest {
    try {
      SongFinder.findFilterAndDo(null, null, null)
    } catch {
      case e: Exception => fail
    }

    assertTrue(true)
  }

  @Test
  def findAndNullWithAlwaysFilterTest {
    try {
      SongFinder.findFilterAndDo(new File(configuration.getProperty("soundective.song.directory")), file => true, null)
    } catch {
      case e: Exception => fail
    }

    assertTrue(true)
  }

  @Test
  def nullFindAndCountWithAlwaysFilterTest {
    var count: Long = 0
    SongFinder.findFilterAndDo(null, file => true, file => count = count + 1)
    assertTrue(count == 0)
  }

  @Test
  def defaultFindAndCountWithNullFilterTest {
    var count: Long = 0
    SongFinder.findFilterAndDo(new File(configuration.getProperty("soundective.song.directory")), null, file => count = count + 1)
    assertTrue(count > 0)
  }
}