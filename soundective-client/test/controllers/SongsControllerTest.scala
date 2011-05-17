package controllers

import models.Song
import play.test._
import org.junit._
import play.db.jpa.Blob
import java.io.{File, ByteArrayInputStream}
import play.Play.configuration
import org.jaudiotagger.audio.AudioFileIO
import org.jaudiotagger.tag.FieldKey

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:41
 */

class SongsControllerTest extends FunctionalTest with Browser with Matchers {

  private val title = "Something"
  private var fileToTest: File = new File(configuration.getProperty("soundective.song.directory") + "/02 - Something.mp3")
  private var blobToTest: Blob = new Blob
  private var songToTest: Song = null
  private var uuidTest: String = null

  @Before
  def setUp {
    Fixtures.deleteAllModels
    Fixtures.loadModels("songs/fixtures/songs.yml")

    blobToTest.set(new ByteArrayInputStream(AudioFileIO.read(fileToTest).getTag.getFirstField(FieldKey.COVER_ART).getRawContent), "image/jpeg")

    songToTest = Song.findByTitle(title).head
  }

  @Test
  def songsTest {
    //One song in tests : Something

    val response = GET("/songs")
    response shouldBeOk()
    response contentTypeShouldBe("application/json")
    response charsetShouldBe("utf-8")
    response contentShouldBe("[{\"mimeType\":\"" + songToTest.mimeType + "\"," +
                            "\"extension\":\"" + songToTest.extension + "\"," +
                            "\"album\":\"" + songToTest.album + "\"," +
                            "\"artist\":\"" + songToTest.artist + "\"," +
                            "\"albumImageMimeType\":\"" + songToTest.albumImageMimeType + "\"," +
                            "\"composer\":\"" + songToTest.composer + "\"," +
                            "\"genre\":\"" + songToTest.genre + "\"," +
                            "\"length\":" + songToTest.length + "," +
                            "\"title\":\"" + songToTest.title + "\"," +
                            "\"track\":\"" + songToTest.track + "\"," +
                            "\"id\":" + songToTest.id + "}]")
  }

  @Test
  def songAlbumImageTest {
    songToTest.albumImage = blobToTest
    songToTest.save

    val response = GET("/song/" + songToTest.id +"/album-image")
    response shouldBeOk
  }

  @Test
  def songAlbumImageNotFoundTest {
    songToTest.albumImage = null
    songToTest.save

    val response = GET("/song/" + songToTest.id +"/album-image")
    response shouldNotBeFound
  }

  @Test
  def songTest {
    val response = GET("/song/" + songToTest.id + "." + songToTest.mimeType)
    response shouldBeOk()
    response contentTypeShouldBe(songToTest.mimeType)
  }

  @Test
  def songNotFoundTest {
    val response = GET("/song/9999")
    response shouldNotBeFound
  }
}