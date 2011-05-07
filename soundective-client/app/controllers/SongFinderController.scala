package controllers

import play.mvc.Controller
import services.SongFinderService
import com.google.gson.JsonParser

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 05/04/11
 * Time: 21:20
 */
object SongFinderController extends Controller {

  def finderState = {
    if(SongFinderService.getSongFinderDetails != null) {
      response.contentType = "application/json; charset=utf-8"
      new JsonParser().parse("{'status':'" + SongFinderService.getSongFinderDetails._1.toString +
           "', 'files': '" + SongFinderService.getSongFinderDetails._2 +
           "', 'songs': '" + SongFinderService.getSongFinderDetails._3 + "'}")
    } else {
      NoContent
    }
  }
}