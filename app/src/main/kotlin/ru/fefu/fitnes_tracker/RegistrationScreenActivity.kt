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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_registration_screen.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.fefu.activitytracker.R
import ru.fefu.activitytracker.databinding.ActivityRegistrationScreenBinding
import ru.fefu.fitnes_tracker.main.App
import ru.fefu.fitnes_tracker.main.MainScreenActivity
import ru.fefu.fitnes_tracker.retrofit.Result
import ru.fefu.fitnes_tracker.retrofit.remote.GenderType
import ru.fefu.fitnes_tracker.retrofit.remote.models.TokenUserModel
import ru.fefu.fitnes_tracker.view_models.RegistrationViewModel

class RegistrationScreenActivity: AppCompatActivity() {
    lateinit var binding: ActivityRegistrationScreenBinding
    private lateinit var viewModel: RegistrationViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[RegistrationViewModel::class.java]
        binding = ActivityRegistrationScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnArrow.setOnClickListener {
            val intent = Intent(this, WelcomeScreenActivity::class.java)
            startActivity(intent)
        }

        viewModel.dataFlow
            .onEach {
                if (it is Result.Success<TokenUserModel>) {
                    App.INSTANCE.sharedPreferences.edit().putString("token", it.result.token).apply()
                    val intent = Intent(this@RegistrationScreenActivity, MainScreenActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
                else if (it is Result.Error<TokenUserModel>) {
                    Toast.makeText(this, it.e.toString(), Toast.LENGTH_LONG).show()
                }
            }
            .launchIn(lifecycleScope)

        binding.btnContinue.setOnClickListener {
            val login = binding.tilLogin.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()
            val password2 = binding.tilPassword2.editText?.text.toString()
            val name = binding.tilName.editText?.text.toString()
            val gender = binding.tilGender.editText?.text.toString()
            var genderOrdinal = 0
            for (i in enumValues<GenderType>()) {
                if (i.type == gender) {
                    genderOrdinal = i.ordinal
                }
            }
            if (password == password2) {
                viewModel.register(login, password, name, genderOrdinal)
            }
            else {
                Toast.makeText(this@RegistrationScreenActivity, "Пароли не совпадают", Toast.LENGTH_SHORT).show()
            }
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