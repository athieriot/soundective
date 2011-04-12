package services

import org.junit._
import org.junit.Assert._
import play.test._
import play.modules.soundective.core.utils.songFinders.SongFinder

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 03/04/11
 * Time: 21:54
 */

class SongFinderFactoryTest extends UnitTest {

  private val time_pause = 50

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
    SongFinderFactory.getSongFinder.start
    pause(time_pause)

    assertNotNull(SongFinderFactory.getSongFinderDetails)
    assertTrue(SongFinderFactory.getSongFinderDetails.isInstanceOf[Tuple2[Long, Long]])
  }
}