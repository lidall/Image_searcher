package com.lida.imageSearch

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.StrictMode
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

import com.lida.httpClient.startRecong
import com.lida.httpClient.sendGet

class MainActivity : AppCompatActivity() {
    private var btn: Button? = null
    private val GALLERY = 1
    private val CAMERA = 2
    private val URL = "https://www.googleapis.com/customsearch/v1?key=YOUR_KEY&cx=YOUR_CX&q="
    companion object {
        var urlList = mutableListOf<String>()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        searchButton.setOnClickListener {

            llProgressBar.visibility = View.VISIBLE
            val searchObj = editTextSearch.text.toString()
            urlList = sendGet(URL + searchObj)
            val intent = Intent(this@MainActivity, DisplayResult::class.java)

            llProgressBar.visibility = View.GONE
            startActivity(intent);
        }

        btn = findViewById<View>(R.id.btn) as Button
        btn!!.setOnClickListener { showPictureDialog() }

    }

    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems
        ) { _, which ->
            when (which) {
                0 -> choosePhotoFromGallary()
                1 -> takePhotoFromCamera()
            }
        }
        pictureDialog.show()
    }


    private fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI)

        startActivityForResult(galleryIntent, GALLERY)
    }

    private fun takePhotoFromCamera() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    public override fun onActivityResult(requestCode:Int, resultCode:Int, data: Intent?) {

        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY)
        {
            if (data != null)
            {
                val contentURI = data!!.data
                val bitmapGallery = MediaStore.Images.Media.getBitmap(this.contentResolver, contentURI)
                val searchObj = startRecong(bitmapGallery)!!.split("0")[0].replace(" ", "")
                urlList = sendGet(URL + searchObj)
                val intent = Intent(this@MainActivity, DisplayResult::class.java)
                startActivity(intent);
            }
        }
        else if (requestCode == CAMERA)
        {
            val bitmapPhoto = data!!.extras!!.get("data") as Bitmap
            val searchObj = startRecong(bitmapPhoto)!!.split("0")[0].replace(" ", "")
            urlList = sendGet(URL + searchObj)
            val intent = Intent(this@MainActivity, DisplayResult::class.java)
            startActivity(intent);
        }
    }
}
