import play.jobs._
import play.Logger
import play.modules.soundective.core.utils.songFinders.SongFinder
import services.SongFinderService

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/03/11
 * Time: 22:20
 */

@Every("2h")
@OnApplicationStart
class Bootstrap extends Job {

  var songFinderService = SongFinderService.getSongFinder

  @Override
  override def doJob() {
    Logger.info("Hello ! I'm the bootstrap and i'm ready to populate database")

    doPopulate(songFinderService)
  }

  def doPopulate(songFinderService: SongFinder) {
    if(songFinderService != null) {
      songFinderService.start
    }
  }
}