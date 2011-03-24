package models

import play.db.jpa._
import javax.persistence.Entity
import java.io.File
import org.soundective.utils.SongTypes.SongType

@Entity
class Song (

  var name: String,

  var number: Long,

  //TODO: Path should not appear in json response
  var path: String,

  var mimeType: String

) extends Model {
    //instance methods
    //var isAdmin = false

    override def toString = name

    def renderFile = new File(path)
}

object Song extends QueryOn[Song] {
}