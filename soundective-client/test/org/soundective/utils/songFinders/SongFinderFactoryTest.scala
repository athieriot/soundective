package org.soundective.utils.songFinders

import play.test.UnitTest
import org.junit._
import org.junit.Assert._
import play.test._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 03/04/11
 * Time: 21:54
 */

class SongFinderFactoryTest extends UnitTest {

  //TODO: Reactive this test when stopping will be possible
  /*@Test
  def getSongFingerDetailsNullTest {
    assertNull(SongFinderFactory.getSongFinderDetails)
  }*/

  @Test
  def getSongFinderTest {
    assertTrue(SongFinderFactory.getSongFinder.isInstanceOf[SongFinder])
  }

  @Test
  def getSongFingerDetailsTest {
    var songFinderTest = SongFinderFactory.getSongFinder
    songFinderTest.start
    pause(10)

    assertNotNull(SongFinderFactory.getSongFinderDetails)
    assertTrue(SongFinderFactory.getSongFinderDetails.isInstanceOf[Tuple2[Long, Long]])
  }
}