package org.soundective

import java.io.File
import play._

/**
 * Created by IntelliJ IDEA.
 * User: aurelien
 * Date: 3/6/11
 * Time: 9:54 AM
 * To change this template use File | Settings | File Templates.
 */

object SongFinder {

  def findAndAnalyse = {
    analyseAndAdd(new File("/Users/aurelien/Documents/"))
  }

  def analyseAndAdd(directory: File) {
    //Logger.info("Recursive search for songs in : " + directory.getAbsolutePath)
    directory.listFiles.foreach(recursiveSearch)
  }

  def recursiveSearch(file: File) {
    if(file.isDirectory) {
      //Logger.info(file.listFiles.length  + " files in directory " + file.getAbsolutePath)
      analyseAndAdd(file)
    } else {
      if(typeFilter(file)) addSong(file)
    }
  }

  def typeFilter(file: File): Boolean = {
    return file.getName.contains("m4a")
  }

  def addSong(file: File) {
    println(file.getCanonicalPath)
  }
}