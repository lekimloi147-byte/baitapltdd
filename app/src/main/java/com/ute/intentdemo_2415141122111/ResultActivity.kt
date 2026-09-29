package com.ute.intentdemo_2415141122111

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvGender = findViewById<TextView>(R.id.tvGender)
        val tvPhone = findViewById<TextView>(R.id.tvPhone)
        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvClass = findViewById<TextView>(R.id.tvClass)
        val btnBack = findViewById<Button>(R.id.btnBack)

        // Nhan du lieu tu Intent
        val name = intent.getStringExtra(IntentKeys.EXTRA_NAME) ?: ""
        val phone = intent.getStringExtra(IntentKeys.EXTRA_PHONE) ?: ""
        val email = intent.getStringExtra(IntentKeys.EXTRA_EMAIL) ?: ""
        val gender = intent.getStringExtra(IntentKeys.EXTRA_GENDER) ?: ""
        val className = intent.getStringExtra(IntentKeys.EXTRA_CLASS) ?: ""

        tvName.text = name
        tvGender.text = gender
        tvPhone.text = "SĐT: $phone"
        tvEmail.text = "Email: ${email.ifEmpty { "Chưa cập nhật" }}"
        tvClass.text = "Lớp: $className"

        // Explicit Intent: ResultActivity -> MainActivity (chieu nguoc lai)
        btnBack.setOnClickListener {
            val backIntent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            }
            startActivity(backIntent)
            finish()
        }
    }
}