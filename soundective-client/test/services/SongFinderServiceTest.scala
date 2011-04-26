package services

import org.junit._
import org.junit.Assert._
import play.test._
import play.modules.soundective.core.utils.songFinders.SongFinder
import actors.Actor

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 03/04/11
 * Time: 21:54
 */

class SongFinderServiceTest extends UnitTest {

  private val time_pause = 50

  //TODO: Reactive this test when stopping will be possible
  /*@Test
  def getSongFingerDetailsNullTest {
    assertNull(SongFinderService.getSongFinderDetails)
  }*/

  @Test
  def getSongFinderTest {
    assertTrue(SongFinderService.getSongFinder.isInstanceOf[SongFinder])
  }

  @Test
  def getSongFingerDetailsTest {
    SongFinderService.getSongFinder.start
    pause(time_pause)

    assertNotNull(SongFinderService.getSongFinderDetails)
    assertTrue(SongFinderService.getSongFinderDetails.isInstanceOf[Tuple3[Actor.State.Value, Long, Long]])
  }
}