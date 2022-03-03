package com.cotlus.bmi

import android.content.Intent
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cotlus.bmi.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var intCurrentWeight = 55
    private var intCurrentAge = 22
    private var intCurrentProgress = 170
    private var strProgress = "170"
    private var strTypeOfuser = "0"
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding){
            // Male Card
            male.setOnClickListener {
                male.setBackgroundResource(R.drawable.male_female_focus)
                female.setBackgroundResource(R.drawable.male_female_normal)
                strTypeOfuser = "Male"
            }

            // Female Card
            female.setOnClickListener {
                female.setBackgroundResource(R.drawable.male_female_focus)
                male.setBackgroundResource(R.drawable.male_female_normal)
                strTypeOfuser = "Female"
            }

            // SeekBar
            seekbarForHeight.max = 300
            seekbarForHeight.progress = 170
            seekbarForHeight.setOnSeekBarChangeListener(object :
                SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                    intCurrentProgress = progress
                    currentHeight.text = intCurrentProgress.toString()
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {
                }

                override fun onStopTrackingTouch(p0: SeekBar?) {
                }
            })

            // Age
            increaseAge.setOnClickListener {
                intCurrentAge += 1
                currentAge.text = intCurrentAge.toString()
            }

            decreaseAge.setOnClickListener {
                if (intCurrentAge > 0) {
                    intCurrentAge -= 1
                    currentAge.text = intCurrentAge.toString()
                } else {
                    intCurrentAge = 1
                }
            }

            // Weight
            increaseWeight.setOnClickListener {
                intCurrentWeight += 1
                currentWeight.text = intCurrentWeight.toString()
            }

            decreaseWeight.setOnClickListener {
                if (intCurrentWeight > 0) {
                    intCurrentWeight -= 1
                    currentWeight.text = intCurrentWeight.toString()
                } else {
                    intCurrentWeight = 1
                }
            }

            // Button
            calculateBMI.setOnClickListener {
                when {
                    strTypeOfuser == "0" -> Toast.makeText(
                        applicationContext,
                        "Please select your gender",
                        Toast.LENGTH_SHORT
                    ).show()
                    strProgress == "0" -> Toast.makeText(
                        applicationContext,
                        "Please select your height",
                        Toast.LENGTH_SHORT
                    ).show()
                    intCurrentAge <= 0 -> Toast.makeText(
                        applicationContext,
                        "Age is incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                    intCurrentWeight <= 0 -> Toast.makeText(
                        applicationContext,
                        "Weight is incorrect",
                        Toast.LENGTH_SHORT
                    ).show()
                    else -> {
                        val intent = Intent(this@MainActivity, BmiActivity::class.java)
                        intent.putExtra("gender", strTypeOfuser)
                        intent.putExtra("height", intCurrentProgress.toString())
                        intent.putExtra("weight", intCurrentWeight.toString())
                        intent.putExtra("age", intCurrentAge.toString())
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}