package com.alv.coroutines

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView) as TextView
        textView.text = "Star Coroutines..."

        GlobalScope.launch (Dispatchers.Main){
            delay(10000L)
            textView.text = "I have changed after 10 seconds"
        }

        asyncSum()

    }

    private fun asyncSum() = runBlocking {
        val time = measureTimeMillis {
            val resOne = async(start = CoroutineStart.LAZY) {
                calculateSum(10, 10)
            }

            val resTwo = async(start = CoroutineStart.LAZY) {
                calculateSum(15, 15)
            }

            resOne.start()
            resTwo.start()
            val sum = resOne.await() + resTwo.await()
            Log.i("SUM", sum.toString())
        }
        Log.i("TIME", time.toString())
    }

    suspend fun calculateSum(a: Int , b: Int): Int {
        delay(5000L) // pretend we are doing something useful here
        return a + b
    }

}




