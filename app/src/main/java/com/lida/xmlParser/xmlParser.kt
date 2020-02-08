package com.lida.xmlParser

fun getCTX(originalCTX: String, firstSplit: String, secondSplit: String): String {
    var resultCTX = originalCTX.substring(
        originalCTX.lastIndexOf(firstSplit),
        originalCTX.lastIndexOf(secondSplit)
    )
    resultCTX = resultCTX.substring(firstSplit.length, resultCTX.length)
    return resultCTX
}