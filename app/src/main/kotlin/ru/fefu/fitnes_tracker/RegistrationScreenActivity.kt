package ru.fefu.fitnes_tracker

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_registration_screen.*
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityRegistrationScreenBinding

class RegistrationScreenActivity: AppCompatActivity() {
    lateinit var binding: ActivityRegistrationScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnArrow.setOnClickListener {
            val intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }
        TextVieWpolicySpan()
    }

    override fun onResume() {
        super.onResume()
        val genders = resources.getStringArray(R.array.genders)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, genders)
        binding.autocompleteGender.setAdapter(arrayAdapter)
    }

    private fun TextVieWpolicySpan() {
        val spannableString = SpannableString("Нажимая на кнопку, вы соглашаетесь с политикой" +
                " \nконфиденциальности и обработки персональных \nданных, а также принимаете " +
                "пользовательское соглашение")
        val clickablespanPolicy = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@RegistrationScreenActivity, "privacy policy", Toast.LENGTH_SHORT).show()
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
        val clickablespanAgreement = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@RegistrationScreenActivity, "user agreement", Toast.LENGTH_SHORT).show()
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(clickablespanPolicy, 37, 66, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickablespanAgreement, 120, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        tvPolicy.text = spannableString
        tvPolicy.movementMethod = LinkMovementMethod.getInstance()
    }

}