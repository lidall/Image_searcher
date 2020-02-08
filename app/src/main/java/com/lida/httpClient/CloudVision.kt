package com.lida.httpClient

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.api.client.extensions.android.http.AndroidHttp
import com.google.api.client.json.JsonFactory
import com.google.api.client.json.gson.GsonFactory
import com.google.api.services.vision.v1.Vision
import com.google.api.services.vision.v1.VisionRequestInitializer
import com.google.api.services.vision.v1.model.*
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileInputStream

fun startRecong(bitmap: Bitmap) : String{

    val featureList: MutableList<Feature> =
        ArrayList()

    val feature = Feature()
    feature.setType("LABEL_DETECTION")
    feature.setMaxResults(10)

    featureList.add(feature)
    val annotateImageRequests: MutableList<AnnotateImageRequest> =
        ArrayList()
    val annotateImageReq = AnnotateImageRequest()
    annotateImageReq.setFeatures(featureList)
    annotateImageReq.setImage(getImageEncodeImage(bitmap))
    annotateImageRequests.add(annotateImageReq)


    val httpTransport = AndroidHttp.newCompatibleTransport()
    val jsonFactory: JsonFactory = GsonFactory.getDefaultInstance()
    val requestInitializer =
        VisionRequestInitializer("YOUR_API_KEY")

    val builder = Vision.Builder(httpTransport, jsonFactory, null)
    builder.setVisionRequestInitializer(requestInitializer)

    val vision = builder.build()
    val batchAnnotateImagesRequest = BatchAnnotateImagesRequest()
    batchAnnotateImagesRequest.setRequests(annotateImageRequests)

    val annotateRequest =
        vision.images().annotate(batchAnnotateImagesRequest)
    annotateRequest.disableGZipContent = true
    val response: BatchAnnotateImagesResponse = annotateRequest.execute()
    return convertResponseToString(response).toString()

}



private fun convertResponseToString(response: BatchAnnotateImagesResponse): String? {
    val imageResponses = response.responses[0]
    val entityAnnotations: List<EntityAnnotation>
    var message: String? = ""

    entityAnnotations = imageResponses.labelAnnotations
    message = formatAnnotation(entityAnnotations)

    return message
}

private fun formatAnnotation(entityAnnotation: List<EntityAnnotation>?): String? {
    var message = ""
    if (entityAnnotation != null) {
        for (entity in entityAnnotation) {
            message = message + "    " + entity.description + " " + entity.score
            message += "\n"
        }
    } else {
        message = "Nothing Found"
    }
    return message
}

private fun getImageEncodeImage(bitmap: Bitmap): Image? {
    val base64EncodedImage = Image()
    val byteArrayOutputStream = ByteArrayOutputStream()
    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, byteArrayOutputStream)
    val imageBytes: ByteArray = byteArrayOutputStream.toByteArray()
    base64EncodedImage.encodeContent(imageBytes)
    return base64EncodedImage
}

