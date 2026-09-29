package com.ute.intentdemo_2415141122111
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var etEmail: EditText
    private lateinit var rgGender: RadioGroup
    private lateinit var spinnerClass: Spinner
    private lateinit var btnSubmit: Button
    private lateinit var btnOpenWeb: Button
    private lateinit var btnCall: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        rgGender = findViewById(R.id.rgGender)
        spinnerClass = findViewById(R.id.spinnerClass)
        btnSubmit = findViewById(R.id.btnSubmit)
        btnOpenWeb = findViewById(R.id.btnOpenWeb)
        btnCall = findViewById(R.id.btnCall)

        val classAdapter = ArrayAdapter.createFromResource(
            this, R.array.class_array, android.R.layout.simple_spinner_item
        )
        classAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerClass.adapter = classAdapter

        // Explicit Intent: MainActivity -> ResultActivity
        btnSubmit.setOnClickListener {
            val name = etName.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ họ tên và số điện thoại", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val gender = if (rgGender.checkedRadioButtonId == R.id.rbMale) "Nam" else "Nữ"
            val className = spinnerClass.selectedItem.toString()

            val intent = Intent(this, ResultActivity::class.java).apply {
                putExtra(IntentKeys.EXTRA_NAME, name)
                putExtra(IntentKeys.EXTRA_PHONE, phone)
                putExtra(IntentKeys.EXTRA_EMAIL, email)
                putExtra(IntentKeys.EXTRA_GENDER, gender)
                putExtra(IntentKeys.EXTRA_CLASS, className)
            }
            startActivity(intent)
        }

        // Implicit Intent: mo website
        btnOpenWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://ute.udn.vn"))
            startActivity(intent)
        }

        // Implicit Intent: goi dien (dung so vua nhap, neu chua nhap thi dung so mac dinh)
        btnCall.setOnClickListener {
            val phone = etPhone.text.toString().trim().ifEmpty { "0901234567" }
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))
            startActivity(intent)
        }
    }
}