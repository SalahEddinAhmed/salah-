package com.salaheddin65.randomnumberfirebase

import android.app.ProgressDialog
import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class createAccountActivity : AppCompatActivity() {
    private lateinit var EtEmail:TextInputLayout
    private lateinit var Etpassword:TextInputLayout
    private lateinit var btnLogin: Button
    private lateinit var btnLoginGoogle: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = "login"
        EtEmail = findViewById(R.id.etEmail)
        Etpassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)
        btnLoginGoogle = findViewById(R.id.btnSigninWithGoogle)

        btnLogin.setOnClickListener { loginUser() }

    }

    private fun loginUser() {
        val email = EtEmail.editText!!.text.toString()
        val pass = Etpassword.editText!!.text.toString()

        when {
            //وظيفة textUtils التعامل مع النصوص في الجمل الشرطية في حالة الادخال من المستخدم

            TextUtils.isEmpty(email) -> EtEmail.error = "Email is required"
            TextUtils.isEmpty(pass) -> Etpassword.error = "Password is required"

            else -> {
                val progressDia = ProgressDialog(this)
                progressDia.setTitle("Login...")
                progressDia.setMessage("please wait, this may take a while")
                progressDia.setCanceledOnTouchOutside(false)
                progressDia.show()
                val mAuth = FirebaseAuth.getInstance()
                mAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        progressDia.dismiss()
                        startActivity(Intent(this@createAccountActivity,GameActivity::class.java))
                        finish()
                    }else {

                        val message = task.exception!!.toString()
                        Toast.makeText(this, "Error : $message", Toast.LENGTH_SHORT).show()
                        FirebaseAuth.getInstance().signOut()
                        progressDia.dismiss()

                    }
                }

            }
        }
    }
}