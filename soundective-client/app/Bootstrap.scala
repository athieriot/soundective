import org.soundective.utils.SongFinder
import play.jobs._
import play.Logger

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/03/11
 * Time: 22:20
 */

@OnApplicationStart
class Bootstrap extends Job {

    @Override
    override def doJob() {
      Logger.info("Hello ! I'm the bootstrap and i'm ready to populate database")
      SongFinder.findAndAddWithSongTypeFilter
    }
}