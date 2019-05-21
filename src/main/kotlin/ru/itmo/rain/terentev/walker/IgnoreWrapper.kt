package ru.itmo.rain.terentev.walker

import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.util.HashSet

class IgnoreWrapper(ignoreFilePath: String, prefix: String) {
    val dirs = HashSet<Path>()
    val files = HashSet<Path>()

    init {
        val ignorePath = Paths.get(ignoreFilePath)
        if (Files.isReadable(ignorePath) && Files.isRegularFile(ignorePath)) {
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
                        val item= Paths.get(prefix + line)
                        when {
                            line.endsWith("/") -> dirs.add(item)
                            else -> files.add(item)
                        }
                    }
                }
            } catch (ignored: IOException) {
            }
        }
    }

    fun isBad(line: String): Boolean {
        return (line.startsWith("#")
                || line.isEmpty()
                || line.split(" ").size > 1)
    }
}