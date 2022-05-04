package com.example.myapplication

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    lateinit var see_list: ImageView
    lateinit var add_list: ImageView
    lateinit var back_btn: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        see_list = findViewById(R.id.see_list)
        add_list = findViewById(R.id.add_list)
        back_btn = findViewById(R.id.back_btn)

        val homefragment = HomeFragment()

        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragContainer, homefragment)
        transaction.commit()


        back_btn.setOnClickListener {
           if(supportFragmentManager.backStackEntryCount==0)
           {
              finish()
           }
            else{
               supportFragmentManager.popBackStack()
            }


        }

        add_list.setOnClickListener(View.OnClickListener {
            val fragmentA = AddDataFragment()

            changeFragment(fragmentA)
        })

        see_list.setOnClickListener(View.OnClickListener {
            val fragmentB = ShowDataFragment()

            changeFragment(fragmentB)
        })
    }

    fun changeFragment(fragment: Fragment) {
        val manager = supportFragmentManager
        val transaction = manager.beginTransaction()
        transaction.replace(R.id.fragContainer, fragment)
        transaction.addToBackStack(fragment.javaClass.simpleName)
        transaction.commit()
    }
}