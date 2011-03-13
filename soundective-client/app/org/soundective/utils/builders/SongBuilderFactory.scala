package org.soundective.utils.builders

import org.soundective.utils.SongTypes

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:36
 */

object SongBuilderFactory {

  def getSongBuilder(songType: SongTypes.Value): SongBuilder = songType match {
    case SongTypes.mp3 => new Mp3SongBuilder
  }
}