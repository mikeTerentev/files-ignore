package ru.itmo.rain.terentev.walker

import processIgnore
import java.nio.file.Files
import java.nio.file.Paths


fun main() {
    val startDir = System.getProperty("user.dir") + "/"
    val ignoreFilePath = "$startDir.ignore"
    val excluded = processIgnore(ignoreFilePath, startDir)
    val walker = VisibleFilesFinder(excluded, startDir.length)
    Files.walkFileTree(Paths.get(startDir), walker)
    walker.found.forEach { e -> println(e) }
}
