package org.soundective.utils

import java.io.File

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 */

object SongFilter {

  def songTypesFilter(file: File): Boolean = {
    if(file == null || !file.exists) {
      return false
    }

    var pass = false;

    SongTypes.values.foreach(songType => {
      if(file.getName.contains(songType.toString))
        pass = true
    })

    return pass
  }
}