package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonSubtract,buttonEquals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;


    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);
        //btnStop = findViewById(R.id.btnStop);


        assignIdAndSetClickListener(R.id.button_c);
        assignIdAndSetClickListener(R.id.button_open_bracket);
        assignIdAndSetClickListener(R.id.button_close_bracket);
        assignIdAndSetClickListener(R.id.button_divide);
        assignIdAndSetClickListener(R.id.button_multiply);
        assignIdAndSetClickListener(R.id.button_plus);
        assignIdAndSetClickListener(R.id.button_subtract);
        assignIdAndSetClickListener(R.id.button_equals);
        assignIdAndSetClickListener(R.id.button_0);
        assignIdAndSetClickListener(R.id.button_1);
        assignIdAndSetClickListener(R.id.button_2);
        assignIdAndSetClickListener(R.id.button_3);
        assignIdAndSetClickListener(R.id.button_4);
        assignIdAndSetClickListener(R.id.button_5);
        assignIdAndSetClickListener(R.id.button_6);
        assignIdAndSetClickListener(R.id.button_7);
        assignIdAndSetClickListener(R.id.button_8);
        assignIdAndSetClickListener(R.id.button_9);
        assignIdAndSetClickListener(R.id.button_ac);
        assignIdAndSetClickListener(R.id.button_dot);


    }
    void assignIdAndSetClickListener(int id) {
        MaterialButton button = findViewById(id);
        button.setOnClickListener(this);
    }
    //void assignId(MaterialButton btn,int id)
    //{
      //  btn=findViewById(id);
       // btn.setOnClickListener(this);
    //}
    //void assignId(int id) {
       // MaterialButton btn = findViewById(id);
       // btn.setOnClickListener(this);
  //  }


    public void onClick(View view)
    {
        MaterialButton button=(MaterialButton) view;
        String buttonText =button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();
        if(buttonText.equals("AC"))
        {
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("="))
        {
            solutionTv.setText(resultTv.getText());
            return;

        }
        if(buttonText.equals("C"))
        {
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }
        else{
            dataToCalculate = dataToCalculate+buttonText;
        }


        solutionTv.setText(dataToCalculate);
        String finalResult = getResult(dataToCalculate);
        if(!finalResult.equals("ERR"))
        {
            resultTv.setText(finalResult);
        }
    }

    String getResult(String data)
    {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch(Exception e)
        {
            return "ERR";
        }
    }
}