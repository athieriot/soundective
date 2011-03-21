package org.soundective.utils.builders

import java.io.File
import models.Song

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:37
 */

trait SongBuilder {

  def buildASong(file: File): Song
}