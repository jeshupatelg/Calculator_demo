package com.example.myapplication;

//import android.annotation.SuppressLint;
import android.app.Activity;
//import android.app.ListActivity;
//import android.app.ProgressDialog;
//import android.content.Context;
import android.os.Bundle;
//import android.view.LayoutInflater;
import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ListView;
import android.widget.TextView;
//import android.widget.Toast;

//import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

//import java.util.ArrayList;


public class MainActivity extends Activity {

    TextView res,input;
    Button b0,b1,b2,b3,b4,b5,b6,b7,b8,b9,bDot,ans;
    StringBuffer num;
    boolean decimal,sum,sub,mul,div,pow,mod,per,sec,third,st;
    double num1,num2;
    //int digit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = (TextView) findViewById(R.id.res);
        input = (TextView) findViewById(R.id.input);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        b0 = (Button) findViewById(R.id.b0);
        b3 = (Button) findViewById(R.id.b3);
        b4 = (Button) findViewById(R.id.b4);
        b5 = (Button) findViewById(R.id.b5);
        b6 = (Button) findViewById(R.id.b6);
        b7 = (Button) findViewById(R.id.b7);
        b8 = (Button) findViewById(R.id.b8);
        b9 = (Button) findViewById(R.id.b9);
        bDot = (Button) findViewById(R.id.bDot);
        ans = (Button) findViewById(R.id.ans);
        num = new StringBuffer("");
        res.setText(R.string._0);
        input.setText(R.string._0);
    }

    public void digitClick(View view){
        if(num.length()==10){return;}
        int button = view.getId();
        if((sum || sub || mul || div || pow || mod || per) && !sec){//second no. start
            sec = true;
            input.setText("");
        }
        if(third && st){input.setText("");}
        st = false;
        switch (button){
            case R.id.b0:
                num.append(0);
                break;
            case R.id.b1:
                num.append(1);
                break;
            case R.id.b2:
                num.append(2);
                break;
            case R.id.b3:
                num.append(3);
                break;
            case R.id.b4:
                num.append(4);
                break;
            case R.id.b5:
                num.append(5);
                break;
            case R.id.b6:
                num.append(6);
                break;
            case R.id.b7:
                num.append(7);
                break;
            case R.id.b8:
                num.append(8);
                break;
            case R.id.b9:
                num.append(9);
                break;
            case R.id.bDot:
                num.append('.');
                decimal = true;
                break;
        }
        input.setText(num.toString());
    }
    public void ansClick(View view){
        getResult();
    }
    public void getResult(){
        double result;
        if(sum){
            result = num1+num2;
            sum = false;
        }else if(sub){
            result = num1 - num2;
            sub = false;
        }else if(mul){
            result = num1 * num2;
            mul = false;
        }else if(div){
            result = num1 / num2;
            div = false;
        }else if(mod){
            result = num1 % num2;
            mod = false;
        }else if(pow){
            result = Math.pow(num1,num2);
            pow = false;
        }else if(per){
            result = num1 * num2 / 100;
            per = false;
        }else{result = 0.0;}
        if(result > 9999999999.0){res.setText(R.string.error); return;}
        if(!decimal){
            res.setText(Long.toString((long)result));
        }else{
            res.setText(String.format("%f",result));
        }
        if(third){num1 = result;}
    }
    public void operation(View view){
        st=true;
        if(sec){
            third = true;
            num2 = Double.parseDouble(num.toString());//TODO check for empty string
        }else{num1 = Double.parseDouble(num.toString());}
        if(third){
            getResult();
        }//TODO for res after third and more operation
        num = new StringBuffer("");
        int button = view.getId();
        switch (button){
            case R.id.sum:
                sum = true;
                break;
            case R.id.sub:
                sub = true;
                break;
            case R.id.mul:
                mul = true;
                break;
            case R.id.div:
                div = true;
                break;
            case R.id.mod:
                mod = true;
                break;
            case R.id.pow:
                pow = true;
                break;
            case R.id.percent:
                per = true;
                break;
        }
    }
    public void clearAll(View view){
        input.setText(R.string._0);
        res.setText(R.string._0);
        num1=0.0;
        num2=0.0;
        num= new StringBuffer("");
        //TODO clear all vars
    }
}
