package ru.itmo.rain.terentev.walker

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.FileVisitResult.CONTINUE
import java.nio.file.FileVisitResult.SKIP_SUBTREE


class VisibleFilesFinder(
    private val excludedPaths: Set<Path>,
    private val prefixSize: Int
) : SimpleFileVisitor<Path>() {

    val found = HashSet<String>()

    override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
        return if (excludedPaths.contains(dir)) SKIP_SUBTREE else CONTINUE
    }

    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        if (!excludedPaths.contains(file)) {
            found.add(file.toString().substring(prefixSize))
        }
        return CONTINUE
    }
}
