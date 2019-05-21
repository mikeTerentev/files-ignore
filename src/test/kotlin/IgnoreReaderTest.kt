import org.junit.Assert.assertEquals
import org.junit.Test
import ru.itmo.rain.terentev.walker.IgnoreWrapper
import java.nio.file.Path
import java.nio.file.Paths

class IgnoreReaderTest {

    val curDir = System.getProperty("user.dir") + "/src/test/resources/reader-tests/";

    @Test
    fun `Empty File`() {
        val wrapper = IgnoreWrapper(curDir + ".bad", "")
        assertEquals(emptySet<Path>(), wrapper.files);
        assertEquals(emptySet<Path>(), wrapper.dirs);
    }

    @Test
    fun `Only Comment`() {
        val wrapper = IgnoreWrapper(curDir + ".comment", "")
        assertEquals(emptySet<Path>(), wrapper.files);
        assertEquals(emptySet<Path>(), wrapper.dirs);
    }

    @Test
    fun `Directory and file`() {
        val expDir = setOf<Path>(Paths.get(curDir + "gradle/"))
        val expFile = setOf<Path>(Paths.get(curDir + "build.txt"))
        val res = IgnoreWrapper(curDir + ".dir-and-file", curDir)
        assertEquals(expDir, res.dirs)
        assertEquals(expFile, res.files)
    }
}