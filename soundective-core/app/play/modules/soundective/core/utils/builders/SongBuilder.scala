package play.modules.soundective.core.utils.builders

import models.Song
import play.db.jpa.Blob
import java.io.{ByteArrayInputStream, File}
import org.jaudiotagger.audio.{AudioFileIO, AudioFile}
import org.jaudiotagger.tag.{FieldKey, Tag}
import play.libs.MimeTypes
import play.Logger
import play.Play.configuration
import java.util.logging.Level

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:37
 */

object SongBuilder {

  java.util.logging.Logger.getLogger("org.jaudiotagger").setLevel(Level.parse(configuration.get("jaudiotagger.log").asInstanceOf[String]));

  def buildASong(file: File): Option[Song] = {

    if(!isFileValid(file)) {
      return None
    }

    var resultSong = new Song(MimeTypes.getMimeType(file.getName), file.getName.substring(file.getName.lastIndexOf(".") + 1), file.getAbsolutePath, file.getName)

    Logger.info("Starting build song : " + file.getName)

    try {

      val audioFile: AudioFile = AudioFileIO.read(file)
      val tag: Tag = audioFile.getTag
      val image = audioFile.getTag().getFirstArtwork()

      //TODO: Have to clean store directory sometimes (Or on delete)
      val albumImageMimeType = image.getMimeType

      val albumImage: Blob = new Blob()
      albumImage.set(new ByteArrayInputStream(image.getBinaryData), albumImageMimeType)

      resultSong.album = tag.getFirst(FieldKey.ALBUM)
      resultSong.artist = tag.getFirst(FieldKey.ARTIST)
      resultSong.albumImage = albumImage
      resultSong.albumImageMimeType = albumImageMimeType
      resultSong.comment = tag.getFirst(FieldKey.COMMENT)
      resultSong.composer = tag.getFirst(FieldKey.COMPOSER)
      resultSong.encoder = tag.getFirst(FieldKey.ENCODER)
      resultSong.genre = tag.getFirst(FieldKey.GENRE)
      resultSong.length = audioFile.getAudioHeader.getTrackLength
      resultSong.originalArtist = tag.getFirst(FieldKey.ORIGINAL_ARTIST)
      resultSong.title = tag.getFirst(FieldKey.TITLE)
      resultSong.track = tag.getFirst(FieldKey.TRACK)
      resultSong.url = tag.getFirst(FieldKey.URL_WIKIPEDIA_ARTIST_SITE)
      resultSong.year = tag.getFirst(FieldKey.YEAR)


    } catch {
      case e: Exception => Logger.debug(e.getMessage)
    }

    return Some(resultSong)
  }

  def isFileValid(file: File): Boolean = {
    return file != null && file.exists && file.length > 0
  }
}