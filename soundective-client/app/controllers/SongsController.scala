package controllers

import play.mvc._
import models.Song
import results.{RenderBinary, NotFound, RenderJson}
import play.modules.soundective.core.utils.serializer.ScalaGsonSerializer
import java.io.File
import play.Play.applicationPath;

object SongsController extends Controller {

  def binarySong(id: Long) = Song.findById(id) match {
    case None => NotFound
    case x => {
      response.contentType = x.head.mimeType
      new RenderBinary(x.head.renderFile, x.head.mimeType)
    }
  }

  def albumImage(UUID: String) = {
    var image: File = new File(applicationPath + "/data/attachments/" + UUID)
    if(image.isFile) image else NotFound
  }

  def list = {
    //TODO: We don't have to log this each time in info...
    //Logger.info("Your are in the SongsController and there are " + Song.count.toString + " songs in the database")

    songJson(Song.findAll)
  }

  private def songJson(list: List[Song]) = {
    //FIXME: Until play version of Json work again
    new RenderJson(ScalaGsonSerializer.toJson(list))
  }
}
