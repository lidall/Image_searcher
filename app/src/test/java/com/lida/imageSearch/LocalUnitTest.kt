package com.lida.imageSearch

import android.graphics.BitmapFactory
import com.lida.httpClient.sendGet
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.api.mockito.PowerMockito.`when`
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import java.io.File
import java.io.FileInputStream

@RunWith(PowerMockRunner::class)
@PrepareForTest(BitmapFactory::class, LocalUnitTest::class)
class LocalUnitTest {

    @Test
    fun seeifXMLwork() {
        var urlList: MutableList<String> = sendGet("https://www.googleapis.com/customsearch/v1?key=YOUR_KEY&cx=YOUR_CX&q=building")
        for (item in urlList) {
            println(item)
        }
    }

    @Test
    fun seeifGoogleAPIwork() {

        val filepath = "/Users/Lida/Desktop/cat.jpeg"
        val imagefile = File(filepath)
        var fis: FileInputStream? = null
        try {
            fis = FileInputStream(imagefile)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        PowerMockito.mockStatic(BitmapFactory::class.java)
        `when`(BitmapFactory.decodeStream(fis))
        
    }

}
