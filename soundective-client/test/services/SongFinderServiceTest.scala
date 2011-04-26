package services

import org.junit._
import org.junit.Assert._
import play.test._
import play.modules.soundective.core.utils.songFinders.SongFinder
import actors.Actor
import play.Play.configuration
import models.Song
import java.io.File
import play.db.jpa.{JPA, JPAPlugin}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 03/04/11
 * Time: 21:54
 */

class SongFinderServiceTest extends FunctionalTest {

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
  def addSongTest {
    SongFinderService.addSong(new File(configuration.getProperty("soundective.song.directory") + "/02 - Something.mp3"))

    JPAPlugin.startTx(false);
    assertFalse(Song.findByTitle("Something").isEmpty)
    Song.findByTitle("Something").head.delete
    JPAPlugin.closeTx(false);
  }

  @Test
  def addBadSongTest {
    SongFinderService.addSong(new File(configuration.getProperty("soundective.song.directory") + "/06 - Mr. Tambourine Man.mp3"))

    JPAPlugin.startTx(false);
    assertTrue(Song.findByTitle("Mr. Tambourine Man").isEmpty)
    JPAPlugin.closeTx(false);
  }

  @Test
  def getSongFingerDetailsTest {
    SongFinderService.getSongFinder.start
    pause(time_pause)

    assertNotNull(SongFinderService.getSongFinderDetails)
    assertTrue(SongFinderService.getSongFinderDetails.isInstanceOf[Tuple3[Actor.State.Value, Long, Long]])
  }
}