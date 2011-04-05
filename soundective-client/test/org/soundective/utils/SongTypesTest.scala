package org.soundective.utils

import play.test.UnitTest
import org.junit.Test
import org.junit.Assert._


/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 24/03/11
 * Time: 21:31
 */

class SongTypesTest extends UnitTest {

  val testedName = "mp3"
  val testedExtension = ".mp3"
  val testedMime = "audio/mpeg"

  val testedSongType = SongTypes.mp3

  @Test
  def songTypeTest = {
    assertEquals(testedName, testedSongType.name)
    assertEquals(testedExtension, testedSongType.extension)
    assertEquals(testedMime, testedSongType.mime)
  }

  @Test
  def toStringTest = {
    assertEquals(testedExtension, testedSongType.toString)
  }

  @Test
  def allTest = {
    assertTrue(SongTypes.all.contains(testedSongType))
  }

  @Test
  def withNameTest = {
    assertEquals(testedSongType, SongTypes.withName(testedName))
  }

  @Test
  def withNameNullTest = {
    assertNull(SongTypes.withName("DontExistType"))
  }
}