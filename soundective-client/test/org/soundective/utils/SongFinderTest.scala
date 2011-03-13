package org.soundective.utils

import java.io.File
import org.junit._
import org.junit.Assert._
import play.test._
import play.Play.configuration

class SongFinderTest extends UnitTest {

  @Test
  def findAndCountTest {
    var count: Long = 0
    SongFinder.findFilterAndDo(new File(configuration.getProperty("soundective.song.directory")), file => true, file => count = count + 1)
    assertTrue(count > 0)
  }

}