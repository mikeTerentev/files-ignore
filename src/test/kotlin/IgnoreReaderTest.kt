import org.junit.Assert.assertEquals
import org.junit.Test
import java.nio.file.Path
import java.nio.file.Paths

class IgnoreReaderTest {

    val curDir = System.getProperty("user.dir") + "/src/test/resources/reader-tests/";

    @Test
    fun `Empty File`() {
        assertEquals(emptySet<Path>(), processIgnore(curDir + ".bad", ""));
    }

    @Test
    fun `Only Comment`() {
        assertEquals(emptySet<Path>(), processIgnore(curDir + ".comment", ""));
    }

    @Test
    fun `Directory and file`() {
        val exp = HashSet<Path>();
        exp.add(Paths.get( curDir +"gradle/"))
        exp.add(Paths.get(curDir +"build.txt"))
        val res = processIgnore(curDir + ".dir-and-file", curDir )
        assertEquals(exp, res)
    }
}