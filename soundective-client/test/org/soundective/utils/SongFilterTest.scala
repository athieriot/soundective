package org.soundective.utils

import java.io.File
import org.junit._
import org.junit.Assert._
import play.test._
import org.soundective.utils.SongFilter

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/03/11
 * Time: 12:48
 */
class SongFilterTest extends UnitTest {

  @Test
  def typeFilterTestTrue {
    assertTrue(SongFilter.songTypesFilter(new File("test/songs/The Limes - Morning Noon  Night.mp3")))
  }

  @Test
  def typeFilterTestFalse {
    assertFalse(SongFilter.songTypesFilter(new File("test/SongFinderTest.scala")))
  }

  @Test
  def typeFilterTestDirectory {
    assertFalse(SongFilter.songTypesFilter(new File("test/")))
  }

  @Test
  def typeFilterTestNull {
    assertFalse(SongFilter.songTypesFilter(null))
  }
}