package com.austin.coffeshoptycoon

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

class MainActivity : AppCompatActivity() {
    companion object{
        val USERNAME = "USERNAME"
    }

    lateinit var buttonLogin: Button
    lateinit var editTextUsername: EditText
    lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var sharedFile = "com.austin.coffeshoptycoon"

        var shared:SharedPreferences = getSharedPreferences(sharedFile, Context.MODE_PRIVATE)

        var editor:SharedPreferences.Editor = shared.edit()

        val myset : Set<String> = setOf("Austin Lieandro", "lieandro")
        val myset1 : Set<String> = setOf("Lieandro Austin", "parkodetuak1")
        val myset2 : Set<String> = setOf("Lieandro Saja", "a")
        editor.putStringSet("austinlie123", myset)
        editor.putStringSet("greensaiver", myset1)
        editor.putStringSet("a", myset2)
        editor.putBoolean("First Time", true)
        editor.apply()

        buttonLogin = findViewById(R.id.btnLogin)
        editTextUsername = findViewById(R.id.txtUsername) as EditText
        editTextPassword = findViewById(R.id.txtPassword) as EditText

        buttonLogin.setOnClickListener {
            val username1 = editTextUsername.text.toString()
            val password1 = editTextPassword.text.toString()

            val password = shared.getStringSet(username1, null)
            if(password == null){
                Toast.makeText(this, "Your username is wrong", Toast.LENGTH_SHORT).show()
            }
            else{
                if (password.elementAt(1) == password1.toString()){
                    toPreperationActivity(password.elementAt(0))
                    Global.playerName = password.elementAt(0)
                }
                else{
                    Toast.makeText(this, "Your password is wrong", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun toPreperationActivity(playerName:String){
        var intent =Intent(this, PreparationActivity::class.java)
        intent.putExtra("USERNAME", playerName)

        startActivity(intent)

        finish()
    }

}