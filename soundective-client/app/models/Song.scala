package models

import play.db.jpa._
import javax.persistence._
import java.io.File

@Entity
class Song (

  var mimeType: String,

  var extension: String,

  var path: String,

  var album: String,

  var artist: String,

  var albumImage: Blob,

  var albumImageMimeType: String,

  var comment: String,

  var composer: String,

  var encoder: String,

  var genre: String,

  var iTunesComment: String,

  var length: Long,

  var originalArtist: String,

  var title: String,

  var track: String,

  var url: String,

  var year: String

) extends Model {
    //instance methods
    //var isAdmin = false

    override def toString = title

    def renderFile = new File(path)
}

object Song extends QueryOn[Song] {

  def findByAsScala(s: String, a: Any) = Song.findBy(s, a).asInstanceOf[List[Song]]

  def findByTitle(title: String) = findByAsScala("title", title)
}