package ru.itmo.rain.terentev.walker


import java.nio.file.Files
import java.nio.file.Paths


fun main() {
    val startDir = System.getProperty("user.dir") + "/"
    val ignoreFilePath = "$startDir.ignore"
    val ignoreWrapper = IgnoreWrapper(ignoreFilePath, startDir)
    val walker = VisibleFilesFinder(ignoreWrapper, startDir.length)
    Files.walkFileTree(Paths.get(startDir), walker)
    walker.found.forEach { e -> println(e) }
}
