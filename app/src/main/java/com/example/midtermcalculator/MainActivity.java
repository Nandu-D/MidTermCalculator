package com.example.midtermcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView resultPrinted;
    private Button one, two, three, four, five, six, seven, eight, nine, zero;
    private Button dot, plus, minus, multiply, divide, cancel, equals;

    String displayScreen = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultPrinted = findViewById(R.id.resultPrinted);
        one = findViewById(R.id.one);
        two = findViewById(R.id.two);
        three = findViewById(R.id.three);
        four = findViewById(R.id.four);
        five = findViewById(R.id.five);
        six = findViewById(R.id.six);
        seven = findViewById(R.id.seven);
        eight = findViewById(R.id.eight);
        nine = findViewById(R.id.nine);
        zero = findViewById(R.id.zero);
        dot = findViewById(R.id.dot);
        plus = findViewById(R.id.plus);
        minus = findViewById(R.id.minus);
        multiply = findViewById(R.id.multiplication);
        divide = findViewById(R.id.division);
        cancel = findViewById(R.id.cancel);
        equals = findViewById(R.id.equals);

        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        dot.setOnClickListener(this);
        cancel.setOnClickListener(this);
        equals.setOnClickListener(this);
        plus.setOnClickListener(this);
        minus.setOnClickListener(this);
        multiply.setOnClickListener(this);
        divide.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.one:
                displayScreen = displayScreen + "1";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.two:
                displayScreen += "2";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.three:
                displayScreen += "3";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.four:
                displayScreen += "4";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.five:
                displayScreen += "5";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.six:
                displayScreen += "6";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.seven:
                displayScreen += "7";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.eight:
                displayScreen += "8";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.nine:
                displayScreen += "9";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.zero:
                displayScreen += "0";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.dot:
                displayScreen += ".";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.plus:
                displayScreen += "+";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.minus:
                displayScreen += "-";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.multiplication:
                displayScreen += "*";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.division:
                displayScreen += "/";
                resultPrinted.setText(displayScreen);
                break;
            case R.id.cancel:
                displayScreen = "";
                resultPrinted.setText("");
                break;
            case R.id.equals:
                //displayScreen = calculateResult();
                displayScreen = calculateOne();
                resultPrinted.setText(displayScreen);
                break;
        }
    }

    private String calculateOne() {
        String result;
        Character symbol= '1';
        String splittedString[] = displayScreen.split("[-*/+]");
        Double first = Double.parseDouble(splittedString[0]);
        Double second = Double.parseDouble(splittedString[1]);
        for (int i=0; i<displayScreen.length(); i++) {
            switch (displayScreen.charAt(i)) {
                case '+':
                    symbol = '+';
                    break;
                case '-':
                    symbol = '-';
                    break;
                case '*':
                    symbol = '*';
                    break;
                case '/':
                    symbol = '/';
                    break;
            }
        }

        Double tempResult = 0.0;
        switch (symbol) {
            case '+':
                tempResult = first + second;
                break;
            case '-':
                tempResult = first - second;
                break;
            case '*':
                tempResult = first * second;
                break;
            case '/':
                tempResult = first / second;
                break;
        }

        result = Double.toString(tempResult);

        return result;
    }


    private String calculateResult() {
        // 5 + 3 * 2 - 8 / 4 + 9
        // + * - / +
        // 5 3 2 8 4 9
        // 0 1 2 3 4

        // 5 + 6 - 2 + 9
        // + - +
        // 5 6 2 9
        String result = "";
        String splittedString[] = displayScreen.split("[+-/*]");
        ArrayList<String> symbols = new ArrayList<>();
        ArrayList<Double> numbers = new ArrayList<>();

        for (int i=0; i<splittedString.length; i++) {
            numbers.add(Double.parseDouble(splittedString[i]));
        }

        for (int i=0; i<displayScreen.length(); i++) {
            if (i != 0 || i != displayScreen.length()-1) {
                if (displayScreen.charAt(i) == '+' || displayScreen.charAt(i) == '-' || displayScreen.charAt(i) == '*' ||
                        displayScreen.charAt(i) == '/') {
                    symbols.add(Character.toString(displayScreen.charAt(i)));
                }
            }
        }

        ArrayList<Integer> removeNumberIndexes = new ArrayList<>();
        for (int i = 0; i<symbols.size(); i++) {
            Double tempResult;
            if (symbols.get(i).equals("*") || symbols.get(i).equals("/")) {
                if (symbols.get(i).equals("*")) {
                    tempResult = numbers.get(i) * numbers.get(i+1);
                } else {
                    tempResult = numbers.get(i) / numbers.get(i+1);
                }
                numbers.set(i, tempResult);
                removeNumberIndexes.add(i+1);
            }
        }

        for (int i=0; i<removeNumberIndexes.size(); i++) {
            numbers.remove(removeNumberIndexes.get(i));
            symbols.remove(removeNumberIndexes.get(i) - 1);
        }


        // 5 + 6 - 2 + 9
        // + - +
        // 5 6 2 9
        for (int i=0; i<symbols.size(); i++) {
            if (symbols.get(i).equals("+")) {
                numbers.set(i+1, numbers.get(i) + numbers.get(i+1));
            } else {
                numbers.set(i+1, numbers.get(i) - numbers.get(i+1));
            }
        }

        result = Double.toString(numbers.get(numbers.size()-1));

        // + - +
        // 5 11 13 22

        return result;
    }
}
