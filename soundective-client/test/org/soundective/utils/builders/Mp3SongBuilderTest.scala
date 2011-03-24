package org.soundective.utils.Builders

import play.test.UnitTest
import org.junit.{Before, Test}
import java.io.File
import org.junit.Assert._
import models.Song
import play.Play.configuration

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 21:21
 */

class Mp3SongBuilderTest extends UnitTest {

  var mp3builder: Mp3SongBuilder = null
  var testPath: String = null

  @Before
  def setup {
    mp3builder = new Mp3SongBuilder
    testPath = configuration.getProperty("soundective.song.directory") + "/02 - Something.mp3"
  }

  @Test
  def buildASongTest {
    val song: Song = mp3builder.buildASong(new File(testPath))
    assertNotNull(song)
    assertEquals("Something", song.name)
    assertEquals(new File(testPath).getAbsolutePath, song.path)
  }

  @Test
  def buildANullSongTest {
    val song: Song = mp3builder.buildASong(null)
    assertNull(song)
  }
}