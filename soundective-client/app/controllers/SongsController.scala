package controllers

import play.mvc._
import models.Song
import results.{RenderBinary, NotFound, RenderJson}
import play.modules.soundective.core.utils.serializer.ScalaGsonSerializer
import java.io.File
import play.Play.applicationPath
import play.Logger
import play.db.jpa.Blob
;

object SongsController extends Controller {

  def binarySong(id: Long) = Song.findById(id) match {
    case None => NotFound
    case x => {
      response.contentType = x.head.mimeType
      new RenderBinary(x.head.renderFile, x.head.mimeType)
    }
  }

  def albumImage(UUID: String) = {
    if (UUID == null) NotFound

    var image: File = new File(Blob.getStore(), UUID)
    if(image.isFile) image else NotFound
  }

  def list = {
    Logger.debug("Your are in the SongsController and there are " + Song.count.toString + " songs in the database")

    songJson(Song.findAll)
  }

  private def songJson(list: List[Song]) = {
    //FIXME: Until play version of Json work again
    new RenderJson(ScalaGsonSerializer.toJson(list))
  }
}
