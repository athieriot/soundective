package controllers

import play.mvc.Controller
import services.SongFinderFactory

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 05/04/11
 * Time: 21:20
 */
object SongFinderController extends Controller {

  def finderState = {
    if(SongFinderFactory.getSongFinderDetails != null) {
      Json("{files: " + SongFinderFactory.getSongFinderDetails._1 + ", songs: " + SongFinderFactory.getSongFinderDetails._2 + "}")
    } else {
      NoContent
    }
  }
}