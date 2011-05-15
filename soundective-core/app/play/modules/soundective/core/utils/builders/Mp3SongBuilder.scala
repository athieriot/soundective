package play.modules.soundective.core.utils.builders

import models.Song
import play.Logger
import com.mpatric.mp3agic.Mp3File
import play.modules.soundective.core.utils.SongTypes
import play.modules.soundective.core.utils.SongTypes.SongType
import play.db.jpa.Blob
import java.io.{ByteArrayInputStream, File}
import org.jaudiotagger.audio.{AudioFileIO, AudioFile}
import org.jaudiotagger.tag.{FieldKey, Tag}

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:37
 */

class Mp3SongBuilder extends SongBuilder {

  val songType: SongType = SongTypes.mp3

  def buildASong(file: File): Song = {

    //TODO: What about monads !!
    if(file == null || !file.exists || file.length == 0) {
      return null
    }

    val absolutePath = file.getAbsolutePath
    val title = file.getName

    Logger.info("Starting build song : " + file.getName)


    try {
      val audioFile: AudioFile = AudioFileIO.read(file)
      val tag: Tag = audioFile.getTag

      //TODO: Have to clean store directory sometimes (Or on delete)
      val albumImage = new Blob
      val albumImageMimeType = audioFile.getTag().getFirstArtwork().getMimeType
      albumImage.set(new ByteArrayInputStream(audioFile.getTag().getFirstArtwork().getBinaryData), albumImageMimeType)

      //TODO: Yerk.
      new Song(songType.mime,
               songType.name,
               file.getAbsolutePath,
               tag.getFirst(FieldKey.ALBUM),
               tag.getFirst(FieldKey.ARTIST),
               albumImage,
               albumImageMimeType,
               tag.getFirst(FieldKey.COMMENT),
               tag.getFirst(FieldKey.COMPOSER),
               tag.getFirst(FieldKey.ENCODER),
               tag.getFirst(FieldKey.GENRE),
               null,
               audioFile.getAudioHeader.getTrackLength,
               tag.getFirst(FieldKey.ORIGINAL_ARTIST),
               tag.getFirst(FieldKey.TITLE),
               tag.getFirst(FieldKey.TRACK),
               tag.getFirst(FieldKey.URL_WIKIPEDIA_ARTIST_SITE),
               tag.getFirst(FieldKey.YEAR))

    } catch {
      //TODO: FIXME: Do this better.
      case e: Exception => return new Song(songType.mime,
                                           songType.name,
                                           file.getAbsolutePath,
                                           null, null, null, null, null, null, null,
                                           null, null, 0, null, file.getName,
                                           null, null, null)
    }
  }
}