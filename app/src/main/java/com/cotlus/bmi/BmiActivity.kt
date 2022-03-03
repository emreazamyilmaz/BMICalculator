package com.cotlus.bmi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cotlus.bmi.databinding.ActivityBmiactivityBinding
import java.math.RoundingMode
import java.text.DecimalFormat


class BmiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBmiactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val resultImage = binding.resultImage
        var floatHeight = 0.0f
        var floatWeight = 0.0f

        intent.getStringExtra("height")?.let {
            floatHeight = it.toFloat()
        }

        intent.getStringExtra("weight")?.let {
            floatWeight = it.toFloat()
        }

        floatHeight /= 100

        val intbmi = floatWeight / (floatHeight * floatHeight)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val roundbmi = df.format(intbmi)
        val stringbmi = roundbmi.toString()

        with(binding) {
            if (intbmi < 18.4) {
                categoryBMI.text = "Severe Thinness"
                resultImage.setImageResource(R.drawable.bad_result)
            } else if (intbmi < 24.9 && intbmi > 18.5) {
                categoryBMI.text = "Moderate Thinness"
                resultImage.setImageResource(R.drawable.fine_result)
            } else {
                categoryBMI.text = "Overweight"
                resultImage.setImageResource(R.drawable.bad_result)
            }
            genderDisplay.text = intent.getStringExtra("gender")
            bmiDisplay.text = stringbmi

            reCalculateBMI.setOnClickListener {
                val intent = Intent(this@BmiActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}