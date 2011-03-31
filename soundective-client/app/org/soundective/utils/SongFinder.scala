package org.soundective.utils

import play._
import java.io.File
import actors.Actor

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 */

class SongFinder(directory: File, action: Function[File, Unit]) extends Actor {

  override def act() {
    Logger.info("Entering SongFinder Actor")
    recursiveAction(directory, action)
  }

  private def recursiveAction(file: File, action: Function[File, Unit]) {

    if(file != null && file.exists && action != null) {
      if(file.isDirectory) {
        directory.listFiles(SongFilter).foreach(file => recursiveAction(file, action))
      } else if (file.isFile) {
        action(file)
      }
    }
  }
}