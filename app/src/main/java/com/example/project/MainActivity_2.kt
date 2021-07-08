package com.example.project

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.os.Build
import android.os.Bundle
//import android.support.annotation.RequiresApi
//import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity


class MainActivity_2 : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var cm : String
    lateinit var kg : String
    var result : Double? = null

    lateinit var rabtnre : String

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ClickableViewAccessibility", "SetTextI18n", "ResourceType")
    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)
        title = "신체질량 지수 (BMI)"

        val cm1 = findViewById<EditText>(R.id.edt1)
        val kg1 = findViewById<EditText>(R.id.edt2)

        val btn = findViewById<Button>(R.id.btn1)
        val tv1 = findViewById<TextView>(R.id.tv1)
        val tv2 = findViewById<TextView>(R.id.tv2)

        val ragroup = findViewById<RadioGroup>(R.id.ragroup)
        val rabtn1 = findViewById<RadioButton>(R.id.rabtn1)
        val rabtn2 = findViewById<RadioButton>(R.id.rabtn2)

        val ckbox1 = findViewById<CheckBox>(R.id.ckbox01)
        val ckbox2 = findViewById<CheckBox>(R.id.ckbox02)
        val ckbox3 = findViewById<CheckBox>(R.id.ckbox03)

        val hrScrollView = findViewById<HorizontalScrollView>(R.id.hrScrollView)
        val hrLinear = findViewById<LinearLayout>(R.id.hrLinear)


        //스피너
        val blood = arrayOf("A", "B", "O", "AB")
        // 이미지의 순서에 맞게 이름을 순서대로 넣기
        val spinner = findViewById<Spinner>(R.id.spi1)
        val adapter: ArrayAdapter<String>
        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, blood)
        spinner.adapter = adapter
        // 액티비티는 뷰를 가지고 있고, 그 뷰가 리스너를 가진다.
        spinner.onItemSelectedListener = this
        //이미지 비활성화
        hrScrollView.visibility = View.INVISIBLE

        //버튼 이벤트
        btn.setOnClickListener {
            // 성별 체크
            when (ragroup.checkedRadioButtonId) {
                R.id.rabtn1 -> {
                    rabtnre = rabtn1.text.toString()
                }
                R.id.rabtn2 -> {
                    rabtnre = rabtn2.text.toString()
                }
            }

            // 이미지 초기화
            hrLinear.removeAllViews()
            // 체크박스체크이벤트
            if (ckbox1.isChecked) {
                val imageView = ImageView(this)
                    imageView.setImageResource(R.drawable.drinking)
                    hrLinear.addView(imageView)
            }
            if (ckbox2.isChecked) {
                val imageView = ImageView(this)
                    imageView.setImageResource(R.drawable.ciga)
                    hrLinear.addView(imageView)
            }
            if (!ckbox3.isChecked) {
                val imageView = ImageView(this)
                    imageView.setImageResource(R.drawable.running)
                    hrLinear.addView(imageView)
            }

            val cmCK = cm1.text.toString()
            val kgCK = kg1.text.toString()
            val spinner1 = spinner.selectedItem.toString()
            if (cmCK == "" || kgCK == ""){
                showDialog(1)

            }else {
                cm = cm1.text.toString()
                kg = kg1.text.toString()
                val cm2 =  cm.toDouble() / 100
                val kg2 =  kg.toDouble()
                result = kg2 / (cm2 * cm2)
                        tv2.text = "신체 지수 질량은 : " + String.format("%.2f", result) + "입니다"
                tv1.text = " " + spinner1 + " 형 " + rabtnre + "입니다"
                hrScrollView.visibility = View.VISIBLE
            }

        }

    }

    @SuppressLint("InflateParams")
    override fun onCreateDialog(id: Int): Dialog {
        val inf = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inf.inflate(R.layout.dialog, null)
        val view: View = inf.inflate(R.layout.dialog, null)
        val builder = AlertDialog.Builder(this@MainActivity_2)
        builder.setView(view)
        return builder.create()
    }

    // 스피너
    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}