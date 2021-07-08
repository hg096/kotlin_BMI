package com.example.project

import android.os.Build
import android.os.Bundle
import android.view.View
//import android.support.v7.app.AppCompatActivity
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity_1 : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_1)

        title = "구구단 맞추기"

        val edt1 = findViewById<EditText>(R.id.edt1)    // 난수1
        val edt2 = findViewById<EditText>(R.id.edt2)    // 난수2
        val edt3 = findViewById<EditText>(R.id.edt3)    // 결과
        val list1 = findViewById<ListView>(R.id.list1)  // 틀렸을 경우 구구단 출력
        val btnRandom = findViewById<Button>(R.id.btnRandom)    //난수 생성
        val btnOK = findViewById<Button>(R.id.btnOK)    // 정답 확인

        // 난수 생성
        btnRandom.setOnClickListener {
            list1.visibility = View.INVISIBLE
            val rand1 = random(1,9)
            val rand2 = random(1,9)
            edt1.setText(rand1.toString())
            edt2.setText(rand2.toString())
            edt3.setText("") // 정답 칸 초기화
        }

        btnOK.setOnClickListener {

            // 문자변환 String -> Int
            var str1 = edt1.text.toString()
            var str2 = edt2.text.toString()
            var str3 = edt3.text.toString()

            //비어있을 경우(null) 0으로 변경 구구단 범위 외의 값으로 설정
            if (str1.isEmpty()) str1 = "0"
            if (str2.isEmpty()) str2 = "0"
            if (str3.isEmpty()) str3 = "0"

            val a1 = str1.toInt() // 난수 1
            val a2 = str2.toInt() // 난수 2
            val a3 = str3.toInt() // 결과 입력

            val a4 = a1 * a2 // 계산결과 저장
            
            // null 입력 처리
            if (a1 != 0 && a2 != 0 && a3 == 0) {
                Toast.makeText(this@MainActivity_1, "정답을 입력해주세요!",
                        Toast.LENGTH_SHORT).show()
            }

            if (a1 == 0 && a2 == 0 || a3 == 0) {
                Toast.makeText(this@MainActivity_1, "난수생성을 눌러주세요!",
                        Toast.LENGTH_SHORT).show()
            }

            //정답 비교
            else if (a3 == a4)
            {

                Toast.makeText(this@MainActivity_1, "정답입니다!",
                        Toast.LENGTH_SHORT).show()

            } else {
                list1.visibility = View.VISIBLE
                Toast.makeText(this@MainActivity_1, "틀렸습니다!",
                        Toast.LENGTH_SHORT).show()
                val values = arrayOfNulls<String>(9)
                for (i in 0..8) { // i 를 0 부터 8까지 출력부분
                    values[i] = a1.toString() + "X" + (i + 1) + "=" + a1 * (i + 1)
                }
                // ListView에 넣기
                val adapter = ArrayAdapter(this@MainActivity_1,
                        android.R.layout.simple_list_item_1, values)
                list1.adapter = adapter

                edt3.setText("") // 정답 칸 초기화
            }


        }

    }

    fun random(from: Int, to: Int) : Int {
        return Random().nextInt(to - from) + from
    }

}