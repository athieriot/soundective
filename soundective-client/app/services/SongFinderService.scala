package services

import play.modules.soundective.core.utils.songFinders.SongFinder
import play.Logger
import java.io.File
import play.modules.soundective.core.utils.SupportedSongTypes
import play.modules.soundective.core.utils.builders.SongBuilder
import models.Song
import play.Play.configuration
import play.db.jpa.{JPA, JPAPlugin}
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

  def getSongFinderDetails: Tuple3[Actor.State.Value, Long, Long] = {
    if(globalSongFinder == null) return null
    else return globalSongFinder.getDetails
  }

  def addSong(file: File) {
    JPAPlugin.startTx(false);

    val song:Song = SongBuilder.buildASong(file)

    if(song != null) {
      if(Song.findByTitle(song.title).isEmpty) song.save()
      else Logger.info("The song : " + song.title + " is already present in database")
    } else {
      Logger.info("A problem happen during building the song : " + file.getName)
    }

    JPAPlugin.closeTx(false);
  }
}