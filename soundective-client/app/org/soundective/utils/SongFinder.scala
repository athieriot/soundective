package org.soundective.utils

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

  var songDirectory = configuration.getProperty("soundective.song.directory")

  def findAndAnalyse {
    Logger.info("Analysing directory : " + songDirectory)
    analyseAndAdd(new File(songDirectory))
  }

  def analyseAndAdd(directory: File) {
    directory.listFiles.foreach(recursiveSearch)
  }

  def recursiveSearch(file: File) {
    if(file.isDirectory) {
      analyseAndAdd(file)
    } else {
      if(SongFilter.songTypesFilter(file)) addSong(file)
    }
  }

  def addSong(file: File) {
    println(file.getCanonicalPath)
  }
}