package models

import play.db.jpa._
import javax.persistence._
import java.io.File

@Entity
class Song(var mimeType: String,
           var extension: String,
           var path: String,
           var title: String) extends Model {

  var album: String = null;

  var artist: String = null;

  var albumImage: Blob = null;

  var albumImageMimeType: String = null;

  var comment: String = null;

  var composer: String = null;

  var encoder: String = null;

  var genre: String = null;

  var length: Long = 0;

  var originalArtist: String = null;

  var track: String = null;

  var url: String = null;

  var year: String = null

  def renderFile: File = new File(path)
}

object Song extends QueryOn[Song] {

  def findByAsScala(s: String, a: Any) = Song.findBy(s, a).asInstanceOf[List[Song]]

  def findByTitle(title: String) = findByAsScala("title", title)
}