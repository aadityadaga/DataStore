package com.example.datastoreproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import com.example.datastoreproject.utils.UserPreferences
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var mUserPreferences: UserPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mUserPreferences = UserPreferences(this@MainActivity)

        button_View.setOnClickListener {

            if (edittext_Name.text.isNullOrEmpty()) {
                edittext_Name.error = "enter value"
            } else {



                GlobalScope.launch {
                    mUserPreferences.saveRandomText(edittext_Name.text.toString())
                    delay(1000)
                }
                mUserPreferences.randomText.asLiveData().observe(this@MainActivity) {
                    textView_data.text = it!!
                }


            }
        }
    }
}