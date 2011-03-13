package models

import play.db.jpa._
import javax.persistence.Entity
import java.io.File
import com.google.gson.annotations.Expose

@Entity
class Song (

  var name: String,

  var number: Long,

  var path: String

) extends Model {
    //instance methods
    //var isAdmin = false

    override def toString = name

    def renderFile = new File(path)
}

object Song extends QueryOn[Song] {
}