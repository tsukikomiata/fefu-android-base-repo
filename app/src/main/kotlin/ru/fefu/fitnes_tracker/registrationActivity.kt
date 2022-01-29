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
import androidx.core.content.ContentProviderCompat.requireContext
import kotlinx.android.synthetic.main.registration.*
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.RegistrationBinding

class registrationActivity: AppCompatActivity() {
    lateinit var binding: RegistrationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = RegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.regImageButtonArrow.setOnClickListener {
            val intent = Intent(this, welcomeScreenActivity::class.java)
            startActivity(intent)
        }
        TextVieWpolicySpan()
    }

    override fun onResume() {
        super.onResume()
        val genders = resources.getStringArray(R.array.sex)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, genders)
        binding.regAutoCompleteTextViewSex.setAdapter(arrayAdapter)
    }

    private fun TextVieWpolicySpan() {
        val spannableString = SpannableString("Нажимая на кнопку, вы соглашаетесь с политикой" +
                " \nконфиденциальности и обработки персональных \nданных, а также принимаете " +
                "пользовательское соглашение")
        val clickableSpan_policy = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@registrationActivity, "privacy policy", Toast.LENGTH_SHORT).show()
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }
        val clickableSpan_agreement = object : ClickableSpan() {
            override fun onClick(widget: View) {
                Toast.makeText(this@registrationActivity, "user agreement", Toast.LENGTH_SHORT).show()
            }
            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.BLUE
                ds.isUnderlineText = false
            }
        }

        spannableString.setSpan(clickableSpan_policy, 37, 66, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan_agreement, 120, spannableString.length, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        reg_textView_policy.text = spannableString
        reg_textView_policy.movementMethod = LinkMovementMethod.getInstance()
    }

}