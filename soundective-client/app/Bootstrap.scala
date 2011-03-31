import java.io.File
import models.Song
import org.soundective.utils.Builders.{SongBuilder, SongBuilderFactory}
import org.soundective.utils.{SongTypes, SongFinder}
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
    SongFinder.defaultFindAndActionWithSongTypeFilter(addSong)
  }

  //TODO: Add a test for that
  def addSong(file: File) {
    var songBuilder: SongBuilder = SongBuilderFactory.getSongBuilder(SongTypes.mp3)
    var song:Song = songBuilder.buildASong(file)

    if(song != null) {
      if(Song.findByTitle(song.title).isEmpty) song.save()
      else Logger.info("The song : " + song.title + " is already present in database")
    } else {
      Logger.info("A problem happen during building the song : " + file.getName)
    }
  }
}