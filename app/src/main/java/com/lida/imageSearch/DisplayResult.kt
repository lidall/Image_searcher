package com.lida.imageSearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

class DisplayResult : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_result)
        setImages()

    }

    private fun setImages(){
        val imageView: ImageView = findViewById(R.id.itemImage1) as ImageView

        Glide.with(applicationContext)
            .load(MainActivity.urlList[0])
            .into(imageView)

        val imageView2: ImageView = findViewById(R.id.itemImage2) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[1])
            .into(imageView2)

        val imageView3: ImageView = findViewById(R.id.itemImage3) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[2])
            .into(imageView3)

        val imageView4: ImageView = findViewById(R.id.itemImage4) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[3])
            .into(imageView4)

        val imageView5: ImageView = findViewById(R.id.itemImage5) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[4])
            .into(imageView5)

        val imageView6: ImageView = findViewById(R.id.itemImage6) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[5])
            .into(imageView6)

        val imageView7: ImageView = findViewById(R.id.itemImage7) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[6])
            .into(imageView7)

        val imageView8: ImageView = findViewById(R.id.itemImage8) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[7])
            .into(imageView8)

        val imageView9: ImageView = findViewById(R.id.itemImage9) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[8])
            .into(imageView9)

        val imageView10: ImageView = findViewById(R.id.itemImage10) as ImageView
        Glide.with(applicationContext)
            .load(MainActivity.urlList[9])
            .into(imageView10)
    }

}
