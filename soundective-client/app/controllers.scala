package controllers

import play._
import play.mvc._

import models.Song
import templates.Template
import org.soundective.SongFinder

object Application extends Controller {
    
    def index = Template

    def add(name: String) = {
      val song = new Song(name)
      Logger.info(Song.count.toString)
      SongFinder.findAndAnalyse
    }
}
