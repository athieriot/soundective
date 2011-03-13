package controllers

import play.mvc._
import models.Song
import play.Logger
import results.RenderJson
import org.soundective.utils.serializer.ScalaGsonSerializer

object SongsController extends Controller {

  def binarySong = Song.findAll.head.renderFile

  def list = {
    Logger.info("Your are in the SongsController and there are " + Song.count.toString + " songs in the database")
    Logger.info(Song.findAll.head.name.toString)

    songJson(Song.findAll)
  }

  private def songJson(list: List[Song]) = {
    //FIXME: Until play version of Json work again
    new RenderJson(ScalaGsonSerializer.toJson(list))
  }
}
