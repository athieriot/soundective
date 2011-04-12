import play.jobs._
import play.Logger
import services.SongFinderFactory

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/03/11
 * Time: 22:20
 */

@OnApplicationStart
//TODO: Program OnEvery sometimes?
class Bootstrap extends Job {

  @Override
  override def doJob() {
    Logger.info("Hello ! I'm the bootstrap and i'm ready to populate database")

    if(SongFinderFactory.getSongFinder != null) {
      SongFinderFactory.getSongFinder.start
    }
  }
}