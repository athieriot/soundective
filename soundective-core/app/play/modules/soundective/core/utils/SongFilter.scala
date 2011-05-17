package play.modules.soundective.core.utils

import java.io.{FileFilter, File}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 */

object SongFilter extends FileFilter {

  def accept(file: File): Boolean = {
    songFilter(file, SupportedSongTypes.all)
  }

  def songFilter(file: File, filters: Seq[String]): Boolean = {
    if(file == null || !file.exists || filters == null) {
      return false
    }

    if(file.isDirectory) return true

    var pass = false;

    filters.foreach(filter => {
      if(file.getName.toLowerCase.contains(filter.toLowerCase))
        pass = true
    })

    return pass
  }
}