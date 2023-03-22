package com.example.killthevirus

import android.graphics.Point
import android.os.Bundle
import android.view.Display
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.killthevirus.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    val viruses = mutableListOf<Virus>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.spreadBtn.setOnClickListener {
            binding.spreadBtn.visibility = View.GONE
            spreadTheDisease()
        }
    }

    private fun spreadTheDisease() {
        val display: Display = windowManager.defaultDisplay
        val screenSize = Point()
        display.getSize(screenSize)

        val virusSize = 300
        val nVirus = Random.nextInt(5, 10)


        for(i in 0 until nVirus){
            val imageView = ImageView(this)
            binding.rootContainer.addView(imageView)

            val v = Virus(imageView, screenSize, virusSize)
            v.spread()
        }
    }
}