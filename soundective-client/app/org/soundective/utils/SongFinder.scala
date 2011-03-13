package org.soundective.utils

import builders.SongBuilderFactory
import java.io.File
import play.Play.configuration
import play._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 */

object SongFinder {

  val songDirectory = configuration.getProperty("soundective.song.directory")
  val songBuilder = SongBuilderFactory.getSongBuilder(SongTypes.mp3)

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
    //songBuilder.buildASong(file).save
  }
}