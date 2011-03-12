package models

import play.db.jpa._
import javax.persistence.Entity

@Entity
class Song (

  var name: String

) extends Model {
    //instance methods
    var isAdmin = false
    override def toString = name
}

object Song extends QueryOn[Song] {

}