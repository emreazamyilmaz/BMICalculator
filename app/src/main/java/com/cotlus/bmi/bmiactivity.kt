package com.cotlus.bmi

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.Toolbar
import androidx.annotation.Nullable
import androidx.core.content.ContextCompat
import com.cotlus.bmi.databinding.ActivityBmiactivityBinding
import java.math.RoundingMode
import java.text.DecimalFormat

private lateinit var binding: ActivityBmiactivityBinding

class bmiactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmiactivity)
        binding = ActivityBmiactivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val resultImage = binding.resultImage
        val height = intent.getStringExtra("height")
        val weight = intent.getStringExtra("weight")

        var intheight = height!!.toFloat()
        val intweight = weight!!.toFloat()
        intheight = intheight / 100

        val intbmi = intweight/(intheight*intheight)
        val df = DecimalFormat("#.##")
        df.roundingMode = RoundingMode.CEILING
        val roundbmi = df.format(intbmi)
        val stringbmi = roundbmi.toString()


        if (intbmi < 18.4){
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

        binding.reCalculateBMI.setOnClickListener(){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}