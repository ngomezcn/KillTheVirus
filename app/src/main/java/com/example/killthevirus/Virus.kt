package com.example.killthevirus

import android.graphics.Point
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.coroutines.*
import kotlin.random.Random

data class Position(var x: Float, var y: Float)
{

}

class Virus (var imageView: ImageView, val size: Point, private val virus_size : Int)  {
    lateinit var job : Job

    init {
        imageView.layoutParams = LinearLayout.LayoutParams(virus_size, virus_size)
        imageView.setImageResource(R.drawable.virus_black)

        val rx: Float = 250.0f + Random.nextFloat() * ((size.x-250) - 250.0f)
        val ry: Float = 300.0f + Random.nextFloat() * ((size.y-500) - 300.0f)

        imageView.x = rx
        imageView.y = ry //- (size.y / 2)

        imageView.setOnClickListener {
            killVirus()
        }
    }

    private fun killVirus()
    {
        job.cancel()
        imageView.setImageResource(R.drawable.virus_dead)
    }

    fun spread() {
        job = CoroutineScope(Dispatchers.Default).launch {
            var ym = 5f
            var xm = 5f

            if(Random.nextBoolean())
            {
                ym *= -1f
            }
            if(Random.nextBoolean())
            {
                xm *= -1f
            }

            withContext(Dispatchers.Main)
            {
                while (true) {
                    imageView.y += ym
                    if (imageView.y + virus_size >= size.y) {
                        ym *= -1
                    }
                    if (imageView.y <= 0.0f) {
                        ym *= -1
                    }

                    imageView.x += xm
                    if (imageView.x + virus_size >= size.x) {
                        xm *= -1
                    }
                    if (imageView.x <= 0.0f) {
                        xm *= -1
                    }

                    val rand = (0..50).random().toLong()
                    delay(rand)
                }
            }
        }
    }
}