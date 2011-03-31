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

    if(file == null || file.length == 0) {
      return null
    }

    try {
      val mp3file = new Mp3File(file.getAbsolutePath)

      Logger.info("Starting build song : " + mp3file.getId3v2Tag.getTitle)

      new Song(mp3file.getId3v2Tag.getTitle, 0, file.getAbsolutePath, songType.mime, songType.name)

    } catch {
      //TODO: FIXME: Do this better. For example, creating the song with file.name = name
      case e: Exception => return new Song(file.getName, 0, file.getAbsolutePath, songType.mime, songType.name)
    }
  }
}