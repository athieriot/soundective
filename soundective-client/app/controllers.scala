package controllers

import play._
import play.mvc._

import models.Song

object Application extends Controller {
    
    def index = Template

    def add(name: String) = {
      val song = new Song(name)
      Logger.info(Song.count.toString)
      song.save
    }
}
