package fr.couzin;

import org.jaudiotagger.audio.AudioFile
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.audio.mp3.MP3File
import org.jaudiotagger.audio.mp3.MP3AudioHeader
import org.jaudiotagger.tag.FieldKey
import java.io.File

/**
 * Hello world!
 *
 */
object App extends Application {

	def call = {
		var f = AudioFileIO.read(new File("/home/aurelien/Musique/no.mp3"))
		var	tag = f.getTag()
		tag.getFirst(FieldKey.ARTIST)
	}
}
