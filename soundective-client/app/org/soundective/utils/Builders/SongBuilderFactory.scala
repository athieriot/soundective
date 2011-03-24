package org.soundective.utils.Builders

import org.soundective.utils.SongTypes
import org.soundective.utils.SongTypes.SongType

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:36
 */

object SongBuilderFactory {

  def getSongBuilder(songType: SongType): SongBuilder = songType match {
    case SongTypes.mp3 => new Mp3SongBuilder
    case x => null
  }
}