package com.example.matt.calculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //numbers
        One.setOnClickListener{appendOnExpression("1",true)}
        Two.setOnClickListener{appendOnExpression("2",true)}
        Three.setOnClickListener{appendOnExpression("3",true)}
        Four.setOnClickListener{appendOnExpression("4",true)}
        Five.setOnClickListener{appendOnExpression("5",true)}
        Six.setOnClickListener{appendOnExpression("6",true)}
        Seven.setOnClickListener{appendOnExpression("7",true)}
        Eight.setOnClickListener{appendOnExpression("8",true)}
        Nine.setOnClickListener{appendOnExpression("9",true)}
        Zero.setOnClickListener{appendOnExpression("0",true)}
        Dot.setOnClickListener{appendOnExpression(".",true)}

        //operators
        Plus.setOnClickListener{appendOnExpression("+",false)}
        Minus.setOnClickListener{appendOnExpression("-",false)}
        Multiply.setOnClickListener{appendOnExpression("*",false)}
        Divide.setOnClickListener{appendOnExpression("/",false)}
        OpenP.setOnClickListener{appendOnExpression("(",false)}
        CloseP.setOnClickListener{appendOnExpression(")",false)}

        Clear.setOnClickListener{
            textExpression.text = ""
            textResult.text = ""
        }

        Back.setOnClickListener{
            val string = textExpression.text.toString()
            if(string.isNotEmpty()){
                textExpression.text = string.substring(0,string.length-1)
            }
            textResult.text = ""
        }

        Equals.setOnClickListener{
            try {

                val expression = ExpressionBuilder(textExpression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble())
                    textResult.text = longResult.toString()
                else
                    textResult.text = result.toString()

            }catch(e:Exception){
                Log.d("Exception", " message : " + e.message )
            }
        }
    }

    fun appendOnExpression( text : String, canClear : Boolean){

        if(textResult.text.isNotEmpty()){
            textExpression.text = ""
        }

        if(canClear){
            textResult.text = ""
            textExpression.append(text)
        }else{
            textExpression.append(textResult.text)
            textExpression.append(text)
            textResult.text = ""
        }
    }
}
