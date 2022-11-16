/*
 * Copyright © 2020, Simplexion, Hungary and contributors. Use of this source code is governed by the Apache 2.0 license.
 */
package zakadabar.gradle

import com.aayushatharva.brotli4j.Brotli4jLoader
import com.aayushatharva.brotli4j.encoder.BrotliOutputStream
import com.aayushatharva.brotli4j.encoder.Encoder
import java.nio.file.Files
import java.nio.file.Path
import java.util.zip.Deflater
import java.util.zip.GZIPOutputStream

fun gzip(src : Path, dst: Path) {
    object : GZIPOutputStream(Files.newOutputStream(dst), 64*1024) {
        init {
            def.setLevel(Deflater.BEST_COMPRESSION)
        }
    }.use {
        Files.copy(src, it)
    }
}

fun brotli(src : Path, dst: Path) {
    Brotli4jLoader.ensureAvailability()
    val fileName = src.fileName.toString()
    val params = Encoder.Parameters()
        .setQuality(11)
        .setWindow(24)
        .setMode(when {
            fileName.endsWith(".js") -> Encoder.Mode.TEXT
            fileName.endsWith(".js.map") -> Encoder.Mode.TEXT
            fileName.endsWith(".html") -> Encoder.Mode.TEXT
            fileName.endsWith(".txt") -> Encoder.Mode.TEXT
            else -> Encoder.Mode.GENERIC
        })
    BrotliOutputStream(Files.newOutputStream(dst), params).use {
        Files.copy(src, it)
    }
}
