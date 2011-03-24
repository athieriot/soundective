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
    songFilter(file, SongTypes.all)
  }

  def songFilter(file: File, filters: Seq[AnyRef]): Boolean = {
    if(file == null || !file.exists || filters == null) {
      return false
    }

    var pass = false;

    filters.foreach(filter => {
      if(file.getName.contains(filter.toString))
        pass = true
    })

    return pass
  }
}