package org.soundective.utils.Builders

import models.Song
import java.io.File
import play.Logger
import com.mpatric.mp3agic.Mp3File
import org.soundective.utils.SongTypes
import org.soundective.utils.SongTypes.SongType

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:37
 */

class Mp3SongBuilder extends SongBuilder {

  val songType: SongType = SongTypes.mp3

  def buildASong(file: File): Song = {

    if(file == null) {
      return null
    }

    val mp3file = new Mp3File(file.getAbsolutePath)

    Logger.info("Starting build song : " + mp3file.getId3v2Tag.getTitle)

    new Song(mp3file.getId3v2Tag.getTitle, 1, file.getAbsolutePath, songType.mime)
  }
}