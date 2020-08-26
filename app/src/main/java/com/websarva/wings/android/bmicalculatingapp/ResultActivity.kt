package com.websarva.wings.android.bmicalculatingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        var generation = 0

        Log.w("LifeCycle", "Sub onCreate() called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val spinnerItem = intent.getIntExtra("spinnerItem", 0)
        val etLenghtItem = intent.getDoubleExtra("etLenghtItem", 0.0)
        val etWeightItem = intent.getDoubleExtra("etWeightItem", 0.0)
        val rbSex = intent.getIntExtra("rbSexItem", 0)


        val tvResult = findViewById<TextView>(R.id.tvResult)
        val BMI = etWeightItem / etLenghtItem / etLenghtItem * 10000

        //tvUserBMI
        val tvUserBMI = findViewById<TextView>(R.id.tvUserBMI)
        val BMIInt = Math.round(BMI)
        tvUserBMI.text = BMIInt.toString()

        //tvGeneration
        val tvGeneration = findViewById<TextView>(R.id.tv_generation)
        val generatonres = spinnerItem / 10
        generation = generatonres * 10

        val geneStr = generation.toString()
        if (rbSex == 1) {
            tvGeneration.setText(geneStr + "代" + "男性")
        } else {
            tvGeneration.setText(geneStr + "女性")
        }

        //体型表示
        if (rbSex == 1 && spinnerItem < 50) {
            if (BMI < 18.5) {
                tvResult.text = "やせ型"
            } else if (18.5 <= BMI && BMI <= 24.9) {
                tvResult.text = "ふつう型"
            } else if (BMI > 24.9) {
                tvResult.text = "ぽっちゃり型"
            }
        } else if (rbSex == 1 && spinnerItem >= 50) {
            if (BMI < 20.0) {
                tvResult.text = "やせ型"
            } else if (20.0 <= BMI && BMI <= 24.9) {
                tvResult.text = "ふつう型"
            } else if (BMI > 24.9) {
                tvResult.text = "ぽっちゃり型"
            }
        } else if (rbSex == 2 && spinnerItem < 50) {
            if (BMI < 18.5) {
                tvResult.text = "やせ型"
            } else if (18.5 <= BMI && BMI <= 23.9) {
                tvResult.text = "ふつう型"
            } else if (BMI > 23.9) {
                tvResult.text = "ぽっちゃり型"
            }
        } else if (rbSex == 2 && spinnerItem >= 50) {
            if (BMI < 19.5) {
                tvResult.text = "やせ型"
            } else if (19.5 <= BMI && BMI <= 23.9) {
                tvResult.text = "ふつう型"
            } else if (BMI > 23.9) {
                tvResult.text = "ぽっちゃり型"
            }
        }

        //各世代別平均体重
        if (rbSex == 1 && spinnerItem < 50 ){
            tvAveBMI.text = "18.5~24.9"
        }else if (rbSex == 1 && spinnerItem >= 50) {
            tvAveBMI.text = "20.0~24.9"
        }else if (rbSex == 2 && spinnerItem < 50)  {
            tvAveBMI.text = "18.5~23.9"
        }else{
            tvAveBMI.text = "19.5~23.9"
        }
    }
    fun onBackButton(view: View) {
        finish()
    }
}