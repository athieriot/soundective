package org.soundective.utils.builders

import models.Song
import java.io.File
import play.Logger
import com.mpatric.mp3agic.Mp3File

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:37
 */

class Mp3SongBuilder extends SongBuilder {

  def buildASong(file: File): Song = {

    val mp3file = new Mp3File(file.getAbsolutePath)

    Logger.info("Starting build song : " + mp3file.getId3v2Tag.getTitle)

    new Song(mp3file.getId3v2Tag.getTitle, 1, file.getAbsolutePath)
  }
}