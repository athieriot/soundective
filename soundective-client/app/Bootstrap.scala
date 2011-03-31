import java.io.File
import models.Song
import org.soundective.utils.Builders.{SongBuilder, SongBuilderFactory}
import org.soundective.utils.{SongTypes, SongFinder}
import play.db.jpa.JPAPlugin
import play.jobs._
import play.Logger
import play.Play.configuration

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/03/11
 * Time: 22:20
 */

@OnApplicationStart
class Bootstrap extends Job {

  val songDirectory = configuration.getProperty("soundective.song.directory")

  @Override
  override def doJob() {
    Logger.info("Hello ! I'm the bootstrap and i'm ready to populate database")

    var songFinder = new SongFinder(new File(songDirectory), addSong)
    songFinder.start

  }

  //TODO: Add a test for that
  def addSong(file: File) {
    JPAPlugin.startTx(false);

    var songBuilder: SongBuilder = SongBuilderFactory.getSongBuilder(SongTypes.mp3)
    var song:Song = songBuilder.buildASong(file)

    if(song != null) {
      if(Song.findByTitle(song.title).isEmpty) song.save()
      else Logger.info("The song : " + song.title + " is already present in database")
    } else {
      Logger.info("A problem happen during building the song : " + file.getName)
    }

    JPAPlugin.closeTx(false);
  }
}