import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.HashSet

fun processIgnore(ignoreFilePath: String, prefix: String): Set<Path> {
    val curPath = Paths.get(ignoreFilePath)
    if (!Files.isReadable(curPath) || !Files.isRegularFile(curPath)) {
        return emptySet()
    }
    val res = HashSet<Path>()
    try {
        BufferedReader(
            InputStreamReader(
                FileInputStream(ignoreFilePath),
                StandardCharsets.UTF_8
            )
        ).use { reader ->
            while (reader.ready()) {
                val line = reader.readLine().trim()
                if (isBad(line)) {
                    continue
                }
                res.add(Paths.get(prefix + line))
            }
        }
    } catch (ignored: IOException) {
    }
    return res
}

fun isBad(line: String): Boolean {
    return (line.startsWith("#")
            || line.isEmpty()
            || line.split(" ").size > 1)
}