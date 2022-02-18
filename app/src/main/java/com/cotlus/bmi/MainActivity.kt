package com.cotlus.bmi

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.core.content.ContextCompat
import com.cotlus.bmi.databinding.ActivityMainBinding
private lateinit var binding: ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var currentweight = 55
    var currentage = 22
    var currentprogress = 170
    var intprogress = "170"
    var typeofuser = "0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // Male Card
        binding.male.setOnClickListener(){
            binding.male.setBackgroundResource(R.drawable.male_female_focus)
            binding.female.setBackgroundResource(R.drawable.male_female_normal)
            typeofuser = "Male"
        }

        // Female Card
        binding.female.setOnClickListener(){
            binding.female.setBackgroundResource(R.drawable.male_female_focus)
            binding.male.setBackgroundResource(R.drawable.male_female_normal)
            typeofuser = "Female"
        }

        // SeekBar
        binding.seekbarForHeight.setMax(300)
        binding.seekbarForHeight.setProgress(170)
        binding.seekbarForHeight.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                currentprogress = progress
                binding.currentHeight.text = currentprogress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })

        // Age
        binding.increaseAge.setOnClickListener(){
            currentage = currentage + 1
            binding.currentAge.text = currentage.toString()
        }

        binding.decreaseAge.setOnClickListener(){
            if (currentage > 0){
                currentage = currentage - 1
                binding.currentAge.text = currentage.toString()
            } else {
                currentage = 1
            }
        }

        // Weight
        binding.increaseWeight.setOnClickListener(){
            currentweight = currentweight + 1
            binding.currentWeight.text = currentweight.toString()
        }

        binding.decreaseWeight.setOnClickListener(){
            if (currentweight > 0){
                currentweight = currentweight - 1
                binding.currentWeight.text = currentweight.toString()
            } else {
                currentweight = 1
            }
        }

        // Button
        binding.calculateBMI.setOnClickListener(){
            if (typeofuser == "0"){
                Toast.makeText(applicationContext, "Please select your gender", Toast.LENGTH_SHORT).show()
            } else if (intprogress == "0"){
                Toast.makeText(applicationContext, "Please select your height", Toast.LENGTH_SHORT).show()
            } else if (currentage <= 0) {
                Toast.makeText(applicationContext, "Age is incorrect", Toast.LENGTH_SHORT).show()
            } else if (currentweight <= 0){
                Toast.makeText(applicationContext, "Weight is incorrect", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, bmiactivity::class.java)
                intent.putExtra("gender", typeofuser)
                intent.putExtra("height", currentprogress.toString())
                intent.putExtra("weight", currentweight.toString())
                intent.putExtra("age", currentage.toString())



                startActivity(intent)
                finish()
            }
        }
    }
}