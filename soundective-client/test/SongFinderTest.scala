import org.junit.Test
import play.test.UnitTest
import org.soundective.SongFinder

class SongFinderTest extends UnitTest {

  @Test
  def findAndAnalyseTest: Unit = {
    new SongFinder.type().findAndAnalyse
  }
}