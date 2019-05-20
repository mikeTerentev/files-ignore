
import org.junit.Test

import org.junit.Assert.*
import ru.itmo.rain.terentev.walker.VisibleFilesFinder
import java.nio.file.Files
import java.nio.file.Paths

class SmartWalkerTest {
    val curDir = System.getProperty("user.dir") + "/src/test/resources/walker-tests/";
    @Test
    fun `Main Test`() {
        val excluded = processIgnore(curDir + ".ignore", curDir)
        val walker = VisibleFilesFinder(excluded, curDir.length)
        Files.walkFileTree(Paths.get(curDir), walker)

        val exp = HashSet<String>();
        exp.add("config/global.txt")
        exp.add("src/main/Main.java")
        exp.add(".ignore")
        exp.add("build.gradle")
        assertEquals(exp,walker.found)
    }
}
