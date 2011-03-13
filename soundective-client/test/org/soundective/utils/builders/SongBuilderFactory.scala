package org.soundective.utils.builders

import org.junit.Assert._
import play.test._
import org.soundective.utils.SongTypes
import org.junit.Test

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 20:50
 */

class SongBuilderFactoryTest extends UnitTest {

  @Test
  def getMp3SongBuilderTest {
    val songBuilder = SongBuilderFactory.getSongBuilder(SongTypes.mp3)
    assertTrue(songBuilder.isInstanceOf[Mp3SongBuilder])
  }
}