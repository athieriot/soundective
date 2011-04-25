package controllers

import models.Song
import play.test._
import org.junit._
import play.db.jpa.Blob
import java.io.File

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 13/03/11
 * Time: 17:41
 */

class SongsControllerTest extends FunctionalTest with Browser with Matchers {

  private val title = "Something"
  private var songToTest: Song = null;
  private var uuidTest: String = null;

  @Before
  def setUp {
    Fixtures.deleteAllModels
    Fixtures.loadModels("songs/fixtures/songs.yml")

    songToTest = Song.findByTitle(title).head
    uuidTest = "2fe5fff0-1325-4160-bb9c-80e4670f6de6"

    new File(Blob.getStore(), uuidTest).createNewFile()
  }

  @Test
  def songsTest {
    //One songs in test : Something

    val response = GET("/songs")
    response shouldBeOk()
    response contentTypeShouldBe("application/json")
    response charsetShouldBe("utf-8")
    response contentShouldBe("[{\"mimeType\":\"" + songToTest.mimeType + "\"," +
                            "\"songType\":\"" + songToTest.songType + "\"," +
                            "\"path\":\"" + songToTest.path + "\"," +
                            "\"album\":\"" + songToTest.album + "\"," +
                            "\"artist\":\"" + songToTest.artist + "\"," +
                            "\"albumImage\":{}," +
                            "\"albumImageMimeType\":\"" + songToTest.albumImageMimeType + "\"," +
                            "\"composer\":\"" + songToTest.composer + "\"," +
                            "\"genre\":" + songToTest.genre + "," +
                            "\"genreDescription\":\"" + songToTest.genreDescription + "\"," +
                            "\"length\":" + songToTest.length + "," +
                            "\"obseleteFormat\":" + songToTest.obseleteFormat + "," +
                            "\"padding\":" + songToTest.padding + "," +
                            "\"title\":\"" + songToTest.title + "\"," +
                            "\"track\":\"" + songToTest.track + "\"," +
                            "\"version\":\"" + songToTest.version + "\"," +
                            "\"id\":" + songToTest.id + "}]")
  }

  @Test
  def songAlbumImageTest {
    val response = GET("/songs/album-image/" + uuidTest)
    response shouldBeOk
  }

  @Test
  def songAlbumImageNotFoundTest {
    val response = GET("/songs/album-image/notexistingfile")
    response shouldNotBeFound
  }

  @Test
  def songTest {
    val response = GET("/song/" + songToTest.id + "." + songToTest.mimeType)
    response shouldBeOk()
    response contentTypeShouldBe(songToTest.mimeType)
  }
}