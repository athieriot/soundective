package controllers

import play.mvc.Controller
import services.SongFinderService

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 05/04/11
 * Time: 21:20
 */
object SongFinderController extends Controller {

  def finderState = {
    if(SongFinderService.getSongFinderDetails != null) {
      Json("{state:" + SongFinderService.getSongFinderDetails._1.toString +
           ", files: " + SongFinderService.getSongFinderDetails._2 +
           ", songs: " + SongFinderService.getSongFinderDetails._3 + "}")
    } else {
      NoContent
    }
  }
}