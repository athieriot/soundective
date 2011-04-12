package play.modules.soundective.core.utils.songFinders

import play.Logger
import java.io.File
import actors.Actor
import play.modules.soundective.core.utils.SongFilter

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 */

class SongFinder(directory: File, action: Function[File, Unit]) extends Actor {

  private var fileCounter: Long = 0
  private var songCounter: Long = 0

  override def act {
    Logger.info("Entering SongFinder Actor")

    fileCounter = 0
    songCounter = 0

    recursiveAction(directory, action)
  }

  private def recursiveAction(file: File, action: Function[File, Unit]) {

    if(file != null && file.exists && action != null) {
      fileCounter = fileCounter + 1

      if(file.isDirectory) {
        file.listFiles.foreach(child => recursiveAction(child, action))
      } else if (file.isFile) {
        if(SongFilter.accept(file)) action(file)

        songCounter = songCounter + 1
      }
    }
  }

  def getDetails: Tuple2[Long, Long] = {
    Tuple2(fileCounter, songCounter)
  }
}