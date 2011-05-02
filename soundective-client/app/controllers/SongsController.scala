package controllers

import play.mvc._
import models.Song
import results.{RenderBinary, NotFound, RenderJson}
import play.modules.soundective.core.utils.serializers.ScalaGsonSerializer
import java.io.File
import play.Play.applicationPath
import play.Logger
import play.db.jpa.Blob

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:41
 */

object SongsController extends Controller {

  def binarySong(id: Long) = Song.findById(id) match {
    case None => NotFound
    case x => {
      response.contentType = x.head.mimeType
      new RenderBinary(x.head.renderFile, x.head.mimeType)
    }
  }

  def albumImage(id: Long) = Song.findById(id) match {
    case None => NotFound
    case x => {
      if(x.head.albumImage.exists) {
        var image: File = x.head.albumImage.getFile
        if(image.isFile) image else NotFound
      } else {
        NotFound
      }
    }
  }

  def list = {
    Logger.debug("Your are in the SongsController and there are " + Song.count.toString + " songs in the database")

    new RenderJson(new ScalaGsonSerializer().exclude("path, albumImage").toJson(Song.findAll))
  }
}
