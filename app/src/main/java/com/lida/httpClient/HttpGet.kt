package com.lida.httpClient

import com.lida.xmlParser.getCTX
import java.net.HttpURLConnection
import java.net.URL

fun sendGet(reqUrl:String) : MutableList<String>{
    val url = URL(reqUrl)

    with(url.openConnection() as HttpURLConnection) {

        var outList: MutableList<String> = ArrayList()
        println("\nSent 'GET' request to URL : $url; Response Code : $responseCode")

        inputStream.bufferedReader().use {
            it.lines().forEach { line ->
                if ("\"og:image\": \"" in line){
                    outList.add(getCTX(line, "\"og:image\": \"", "\""))
                }
            }
        }

        return outList


    }
}