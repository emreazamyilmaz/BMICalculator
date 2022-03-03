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

        if (intbmi < 18.4) {
            binding.categoryBMI.text = "Severe Thinness"
            resultImage.setImageResource(R.drawable.bad_result)
        } else if (intbmi < 24.9 && intbmi > 18.5) {
            binding.categoryBMI.text = "Moderate Thinness"
            resultImage.setImageResource(R.drawable.fine_result)
        } else {
            binding.categoryBMI.text = "Overweight"
            resultImage.setImageResource(R.drawable.bad_result)
        }

        binding.genderDisplay.text = intent.getStringExtra("gender")
        binding.bmiDisplay.text = stringbmi

        binding.reCalculateBMI.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}