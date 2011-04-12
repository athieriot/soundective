package play.modules.soundective.core.utils.builders

import play.modules.soundective.core.utils.SongTypes
import play.modules.soundective.core.utils.SongTypes.SongType

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