package com.example.xmlcalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View v1 = findViewById(R.id.textView2);
        textView = (TextView) v1;

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        Button button5 = (Button) findViewById(R.id.button5);
        button5.setOnClickListener(this);
        Button button6 = (Button) findViewById(R.id.button6);
        button6.setOnClickListener(this);
        Button button7 = (Button) findViewById(R.id.button7);
        button7.setOnClickListener(this);
        Button button8 = (Button) findViewById(R.id.button8);
        button8.setOnClickListener(this);
        Button button9 = (Button) findViewById(R.id.button9);
        button9.setOnClickListener(this);
        Button button0 = (Button) findViewById(R.id.button19);
        button0.setOnClickListener(this);

        Button buttonPoint = (Button) findViewById(R.id.button31);
        buttonPoint.setOnClickListener(this);
        Button buttonAC = (Button) findViewById(R.id.button28);
        buttonAC.setOnClickListener(this);
        Button buttonDEL = (Button) findViewById(R.id.button29);
        buttonDEL.setOnClickListener(this);
        Button buttonSuma = (Button) findViewById(R.id.button26);
        buttonSuma.setOnClickListener(this);
        Button buttonResta = (Button) findViewById(R.id.button27);
        buttonResta.setOnClickListener(this);
        Button buttonMulti = (Button) findViewById(R.id.button25);
        buttonMulti.setOnClickListener(this);
        Button buttonDivi = (Button) findViewById(R.id.button23);
        buttonDivi.setOnClickListener(this);
        Button buttonResult = (Button) findViewById(R.id.button20);
        buttonResult.setOnClickListener(this);
        // Restaurar el contenido si hay un estado guardado
        if (savedInstanceState != null) {
            String text = savedInstanceState.getString("textViewContent");
            textView.setText(text);
        }
    }
    @Override
    public void onClick(View v) {

//Esta funcion se encarga de añadir un valor segun el boton que se precione
        if (v.getId() == R.id.button1) textView.append("1");
        else if (v.getId() == R.id.button2) textView.append("2");
        else if (v.getId() == R.id.button3) textView.append("3");
        else if (v.getId() == R.id.button4) textView.append("4");
        else if (v.getId() == R.id.button5) textView.append("5");
        else if (v.getId() == R.id.button6) textView.append("6");
        else if (v.getId() == R.id.button7) textView.append("7");
        else if (v.getId() == R.id.button8) textView.append("8");
        else if (v.getId() == R.id.button9) textView.append("9");
        else if (v.getId() == R.id.button19) textView.append("0");
        else if (v.getId() == R.id.button31) textView.append(".");
        else if (v.getId() == R.id.button28) textView.setText("");
        else if (v.getId() == R.id.button29) textView.setText("");
        else if (v.getId() == R.id.button26) textView.append("-");
        else if (v.getId() == R.id.button27) textView.append("+");
        else if (v.getId() == R.id.button25) textView.append("x");
        else if (v.getId() == R.id.button23) textView.append("/");
        else if (v.getId() == R.id.button) textView.append("²");
        else if (v.getId() == R.id.button10) textView.append("√");
        else if (v.getId() == R.id.button11) textView.append("(");
        else if (v.getId() == R.id.button12) textView.append(")");
        else if (v.getId() == R.id.button13) textView.append("%");
        else if (v.getId() == R.id.button20) textView.append("=");

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("textViewContent", textView.getText().toString());
    }
}