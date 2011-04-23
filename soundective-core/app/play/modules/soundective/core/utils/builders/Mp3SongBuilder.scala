package play.modules.soundective.core.utils.builders

import models.Song
import play.Logger
import com.mpatric.mp3agic.Mp3File
import play.modules.soundective.core.utils.SongTypes
import play.modules.soundective.core.utils.SongTypes.SongType
import play.db.jpa.Blob
import java.io.{ByteArrayInputStream, File}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:37
 */

class Mp3SongBuilder extends SongBuilder {

  val songType: SongType = SongTypes.mp3

  def buildASong(file: File): Song = {

    if(file == null || !file.exists || file.length == 0) {
      return null
    }

    var absolutePath = file.getAbsolutePath
    var title = file.getName

    Logger.info("Starting build song : " + file.getName)


    try {
      val mp3file = new Mp3File(file.getAbsolutePath)
      val tag = mp3file.getId3v2Tag

      var albumImage = new Blob();
      albumImage.set(new ByteArrayInputStream(tag.getAlbumImage), tag.getAlbumImageMimeType)

      new Song(songType.mime,
               songType.name,
               file.getAbsolutePath,
               tag.getAlbum,
               tag.getArtist,
               albumImage,
               tag.getAlbumImageMimeType,
               tag.getComment,
               tag.getComposer,
               tag.getCopyright,
               tag.getEncoder,
               tag.getGenre,
               tag.getGenreDescription,
               tag.getItunesComment,
               tag.getLength,
               tag.getObseleteFormat,
               tag.getOriginalArtist,
               tag.getPadding,
               tag.getTitle,
               tag.getTrack,
               tag.getUrl,
               tag.getVersion,
               tag.getYear)

    } catch {
      //TODO: FIXME: Do this better. For example, create a SongTemplate to feed in builders
      case e: Exception => return new Song(songType.mime,
                                           songType.name,
                                           file.getAbsolutePath,
                                           null, null, null, null, null, null, null, null, 0,
                                           null, null, 0, false, null, false, file.getName,
                                           null, null, null, null)
    }
  }
}