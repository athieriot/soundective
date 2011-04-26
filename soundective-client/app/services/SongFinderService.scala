package services

import play.modules.soundective.core.utils.songFinders.SongFinder
import play.Logger
import java.io.File
import play.modules.soundective.core.utils.builders.{SongBuilderFactory, SongBuilder}
import play.modules.soundective.core.utils.SongTypes
import models.Song
import play.Play.configuration
import play.db.jpa.{JPA, JPAPlugin, Transactional}
import actors.Actor

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 03/04/11
 * Time: 20:32
 */

//TODO: Adapt to connect to other services. Question: Responsibilty to server or client?
object SongFinderService {

  private var globalSongFinder: SongFinder = null
  private val songDirectory = configuration.getProperty("soundective.song.directory")

  //TODO: Need stop the process to relaunch
  def getSongFinder: SongFinder = {
    if(globalSongFinder == null) {

      Logger.info("Create a new finder in directory : " + songDirectory)
      globalSongFinder = new SongFinder(new File(songDirectory), addSong)
    }

    return globalSongFinder
  }

  //TODO: We can get the state of the actor
  def getSongFinderDetails: Tuple3[Actor.State.Value, Long, Long] = {
    if(globalSongFinder == null) return null
    else return globalSongFinder.getDetails
  }

  //TODO: Add a test for that
  def addSong(file: File) {
    JPAPlugin.startTx(false);

    var songBuilder: SongBuilder = SongBuilderFactory.getSongBuilder(SongTypes.mp3)
    var song:Song = songBuilder.buildASong(file)

    if(song != null) {
      if(Song.findByTitle(song.title).isEmpty) song.save()
      else Logger.info("The song : " + song.title + " is already present in database")
    } else {
      Logger.info("A problem happen during building the song : " + file.getName)
    }

    JPAPlugin.closeTx(false);
  }
}