package controllers

import play.mvc._
import models.Song
import play.Logger
import results.{RenderBinary, NotFound, RenderJson}
import play.modules.soundective.core.utils.serializer.ScalaGsonSerializer

object SongsController extends Controller {

  def binarySong(id: Long) = Song.findById(id) match {
    case None => NotFound
    case x => {
      response.contentType = x.head.mimeType
      new RenderBinary(x.head.renderFile, x.head.mimeType)
    }
  }

  def list = {
    Logger.info("Your are in the SongsController and there are " + Song.count.toString + " songs in the database")

    songJson(Song.findAll)
  }

  private def songJson(list: List[Song]) = {
    //FIXME: Until play version of Json work again
    new RenderJson(ScalaGsonSerializer.toJson(list))
  }
}
