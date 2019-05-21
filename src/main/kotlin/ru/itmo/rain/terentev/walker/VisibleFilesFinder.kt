package ru.itmo.rain.terentev.walker

import java.nio.file.FileVisitResult
import java.nio.file.Path
import java.nio.file.SimpleFileVisitor
import java.nio.file.attribute.BasicFileAttributes
import java.nio.file.FileVisitResult.CONTINUE
import java.nio.file.FileVisitResult.SKIP_SUBTREE
import java.util.*


class VisibleFilesFinder(
    private val excluded: IgnoreWrapper,
    private val prefixSize: Int
) : SimpleFileVisitor<Path>() {

    val found = TreeSet<String>()

    override fun preVisitDirectory(dir: Path, attrs: BasicFileAttributes): FileVisitResult {
        return if (excluded.dirs.contains(dir)) SKIP_SUBTREE else CONTINUE
    }

    override fun visitFile(file: Path, attrs: BasicFileAttributes): FileVisitResult {
        if (!excluded.files.contains(file)) {
            found.add(file.toString().substring(prefixSize))
        }
        return CONTINUE
    }
}
