package com.websarva.wings.android.bmicalculatingapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    private var etLenght_notWrite = true
    private var etWeight_notWrite = true
    private var rbSexItem:Int = 0
    private var spinnerItem:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etLenght = findViewById<EditText>(R.id.etLenght)
        val etWeight = findViewById<EditText>(R.id.etWeight)
        val rbSex = findViewById<RadioGroup>(R.id.rbSex)
        val spinner = findViewById<Spinner>(R.id.spiAge)
        val btnResult = findViewById<Button>(R.id.btnResult)

        //Spinner設定
        var item = arrayOf(
                0, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19,
                20, 21, 22, 23, 24, 25, 26, 27, 28, 29,
                30, 31, 32, 33, 34, 35, 36, 37, 38, 39,
                40, 41, 42, 43, 44, 45, 46, 47, 48, 49,
                50, 51, 52, 53, 55, 55, 56, 57, 58, 59
        )
        val adapter =
                ArrayAdapter(applicationContext, android.R.layout.simple_spinner_dropdown_item, item)
        spinner.adapter = adapter


        //etLenght用リスナー設定
        val listenerEtLenght = EtLenghtTextChangeListener()
        etLenght.addTextChangedListener(listenerEtLenght)

        //etLenght用リスナー設定
        val listenerEtWeight = EtWeightTextChangeListener()
        etWeight.addTextChangedListener(listenerEtWeight)

        //spinner用リスナー設定
        val listenerSpi = SpinnerSelectedListener()
        spinner.setOnItemSelectedListener(listenerSpi)

        //btnResult用リスナー設定
        val listenerBtn = ButtonClickListener()
        btnResult.setOnClickListener(listenerBtn)

        //rbSex用リスナー設定
        //val listenerRb = RbSelectedListener()
        //rbSex.setOnCheckedChangeListener(listenerRb)
    }

    //エディットテキスト（身長）
    private inner class EtLenghtTextChangeListener:TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val str = s.toString()
            if (str.length > 0){
                etLenght_notWrite = false
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }

    //エディットテキスト（体重)
    private inner class EtWeightTextChangeListener:TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun afterTextChanged(s: Editable?) {
            val str = s.toString()
            if (str.length > 0){
                etWeight_notWrite = false
            }
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        }
    }


    //スピナー
    private inner class SpinnerSelectedListener: AdapterView.OnItemSelectedListener{
        override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            spinnerItem = parent.getItemAtPosition(position) as Int

            //スピナー選択時にEditTextが記載済みであればbtnResultをアクティブ化
            val btnResult = findViewById<Button>(R.id.btnResult)
            if(etLenght_notWrite == false && etWeight_notWrite == false){
                btnResult.isEnabled = true
            }

        }
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }
    }

    //ボタン
    private inner class ButtonClickListener: View.OnClickListener{
        override fun onClick(v: View) {

            val etLenght = findViewById<EditText>(R.id.etLenght)
            var etLenghtItem = java.lang.Double.valueOf(etLenght.text.toString())

            val etWeight = findViewById<EditText>(R.id.etWeight)
            var etWeightItem = java.lang.Double.valueOf(etWeight.text.toString())

            val rbMale = findViewById<RadioButton>(R.id.rbMale)
            if(rbMale.isChecked() == true)
                rbSexItem = 1
            else
                rbSexItem = 2



            val intent = Intent(applicationContext, ResultActivity::class.java)
            intent.putExtra("etLenghtItem", etLenghtItem)
            intent.putExtra("etWeightItem", etWeightItem)
            intent.putExtra("rbSexItem", rbSexItem)
            intent.putExtra("spinnerItem", spinnerItem)

            startActivity(intent)

        }
    }


}