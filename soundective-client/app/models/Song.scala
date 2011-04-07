package models

import play.db.jpa._
import javax.persistence.Entity
import java.io.File
import java.util.List

@Entity
class Song (

  var title: String,

  var number: Long,

  //TODO: Path should not appear in json response
  var path: String,

  var mimeType: String,

  var songType: String

) extends Model {
    //instance methods
    //var isAdmin = false

    override def toString = title

    def renderFile = new File(path)
}

object Song extends QueryOn[Song] {

  def findByAsScala(s: String, a: Any) = (asScala.asList(findBy(s, a)).asInstanceOf[List[Song]])

  def findByTitle(title: String) = findByAsScala("title", title)
}