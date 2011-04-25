import org.junit._
import play.modules.soundective.core.utils.songFinders.SongFinder
import play.test._
import org.mockito.Mockito._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 24/04/11
 * Time: 23:28
 */

class BootstrapTest extends UnitTest {

  private var bootstrapTestInstance = new Bootstrap

  private var songFinderTest: SongFinder = mock(classOf[SongFinder])

  @Test
  def doPopulateNullTest {
    bootstrapTestInstance.doPopulate(null)
    verify(songFinderTest, never()).start
  }

  @Test
  def doPopulateTest {
    when(songFinderTest.start).thenReturn(null)
    bootstrapTestInstance.doPopulate(songFinderTest)
    verify(songFinderTest).start
  }
}