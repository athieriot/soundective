package play.modules.soundective.core.utils

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 12/03/11
 * Time: 13:30
 */

object SongTypes {
  sealed trait SongType {
    val name: String

    val extension: String

    val mime: String

    override def toString() = extension
  }

  case object Mp3 extends SongType {
    val name = "mp3"
    val extension = ".mp3"
    val mime = "audio/mpeg"
  }

  def all: Seq[SongType] = List(Mp3)
  def withName(name: String) = all.find(_.name == name) match {
    case None => null
    case x => x.head
  }

  val mp3 = Mp3
}