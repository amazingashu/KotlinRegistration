package com.acrony.kotlinregistration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var Name:String
    lateinit var Email:String
    lateinit var Phone:String
    lateinit var Password:String

    lateinit var eFullName: EditText
    lateinit var eEmail:EditText
    lateinit var ePhone:EditText
    lateinit var ePassword:EditText

    lateinit var btnCreateAccount: Button
    lateinit var btnLogin:Button
    lateinit var pbProcess: ProgressBar
     lateinit var mAPIService:RegisterServiceForm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        eFullName = findViewById(R.id.etFullName) as EditText
        eEmail = findViewById(R.id.etEmail) as EditText
        ePhone = findViewById(R.id.etPhone) as EditText
        ePassword = findViewById(R.id.etPassword) as EditText
        btnCreateAccount = findViewById(R.id.btnSignUp) as Button
        pbProcess = findViewById(R.id.progressBar) as ProgressBar

        pbProcess.setVisibility(View.GONE)

        btnCreateAccount.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view:View) {
                Name = eFullName.getText().toString()
                Email = eEmail.getText().toString()
                Phone = ePhone.getText().toString()
                Password = ePassword.getText().toString()

                if (Name.isEmpty() && Email.isEmpty() && Phone.isEmpty() && Password.isEmpty())
                {
                    Toast.makeText(this@MainActivity, "Please enter all details",
                        Toast.LENGTH_SHORT).show()
                }

                else
                {
                    pbProcess.setVisibility(View.VISIBLE)
                    mAPIService = RegistrationAdapter.registerServiceForm!!
                    val call = mAPIService.getStringScalar(Name, Email, Phone, Password)
                    call.enqueue(object: Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            Toast.makeText(this@MainActivity, response.body(),
                                Toast.LENGTH_SHORT).show()

                            pbProcess.setVisibility(View.GONE)
                        }
                        override fun onFailure(call:Call<String>, t:Throwable) {
                            Toast.makeText(this@MainActivity, "Error" + t.message.toString(), Toast.LENGTH_SHORT).show()
                            pbProcess.setVisibility(View.GONE)
                        }
                    })
                }
            }// end else
        })
    }
}
