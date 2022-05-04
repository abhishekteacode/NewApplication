package com.example.myapplication

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    var SPLASH_DURATION = 2000
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val handler = Handler()
        handler.postDelayed({


            if (checkForInternet(this)) {
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()

            } else {

                val dialogBuilder = AlertDialog.Builder(this)
                dialogBuilder.setMessage("No Internet! Please connect to the internet")
                dialogBuilder.setPositiveButton("OK", { dialog, whichButton ->
                    dialog.dismiss()
                })
                dialogBuilder.setNegativeButton("Cancel", { dialog, whichButton ->
                    dialog.dismiss()
                })

                dialogBuilder.show()
            }

        }, SPLASH_DURATION.toLong())
    }

    private fun checkForInternet(context: Context): Boolean {

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            val network = connectivityManager.activeNetwork ?: return false


            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            // if the android version is below M
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION")
            return networkInfo.isConnected
        }
    }
}