package org.soundective.utils

import java.io.File
import play.Play.configuration
import play._
import models.Song
import com.mpatric.mp3agic.Mp3File

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 */

object SongFinder {

  var songDirectory = configuration.getProperty("soundective.song.directory")

  def defautSongFinder = findAndAddWithSongTypeFilter

  def findAndAddWithSongTypeFilter {
    Logger.info("Finding songs in directory : " + songDirectory)
    findFilterAndDo(new File(songDirectory), SongFilter.songTypesFilter, addSong)
  }

  def findFilterAndDo(directory: File, filter: Function[File, Boolean], action: Function[File, Unit]) {
    directory.listFiles.foreach(file => recursiveAction(file, filter, action))
  }

  private def recursiveAction(file: File, filter: Function[File, Boolean], action: Function[File, Unit]) {
    if(file.isDirectory) {
      findFilterAndDo(file, filter, action)
    } else {
      if(filter(file)) action(file)
    }
  }


  def addSong(file: File) {
    Logger.info("Finded song : ")

    var mp3file = new Mp3File(file.getAbsolutePath);
    Logger.info("Artist : " + mp3file.getId3v2Tag.getArtist)

    new Song(mp3file.getId3v2Tag.getTitle, 1, file.getAbsolutePath).save
  }
}