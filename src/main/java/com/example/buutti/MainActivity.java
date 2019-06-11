package com.example.buutti;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button firstButton;
    Button secondButton;
    Button submitButton;
    EditText firstNumber;
    EditText secondNumber;
    EditText thirdNumber;
    EditText fourthNumber;
    TextView sum;
    int multiplicand = 0;
    int multiplier = 0;
    int activeView = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); /*set display portrait*/
        firstButton = findViewById(R.id.buttonFirstView);
        secondButton = findViewById(R.id.buttonSecondView);
        submitButton = findViewById(R.id.buttonResult);
        firstNumber = findViewById(R.id.firstTextNumber);
        secondNumber = findViewById(R.id.secondTextNumber);
        thirdNumber = findViewById(R.id.editTextNumber1);
        fourthNumber = findViewById(R.id.editTextNumber2);
        sum = (TextView)findViewById(R.id.textViewLayout3);
        activeView =1;


        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewOne();
            }
        });
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showViewTwo();
            }
        });
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showViewThree();

            }
        });

        firstNumber.addTextChangedListener(numberFieldWatcher);
        secondNumber.addTextChangedListener(numberFieldWatcher);
        thirdNumber.addTextChangedListener(numberFieldWatcher);
        fourthNumber.addTextChangedListener(numberFieldWatcher);

    }

    /************
     * Check if text changed in the text field and change the added values to integer
     */
    private TextWatcher numberFieldWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String firstValue = firstNumber.getText().toString().trim();
            String secondValue = secondNumber.getText().toString().trim();
            String thirdValue = thirdNumber.getText().toString().trim();
            String fourthValue = fourthNumber.getText().toString().trim();
            submitButton.setEnabled(false);

            if(activeView ==1) {
                if (isStringInt(firstValue)) {
                    if (isStringInt(secondValue)) {
                        submitButton.setEnabled(true);
                        multiplicand = Integer.parseInt(firstValue);
                        multiplier = Integer.parseInt(secondValue);
                    }
                }
            }
            if (activeView == 2) {
                if (isStringInt(thirdValue)) {
                    if (isStringInt(fourthValue)) {
                        submitButton.setEnabled(true);
                        multiplicand = Integer.parseInt(thirdValue);
                        multiplier = Integer.parseInt(fourthValue);
                    }
                }
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    /************
     * Boolean isStingInt
     * @param s
     * @return show toast to user if tried to insert letters
     */
    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            if(s.length() > 0) {
                Toast.makeText(getApplicationContext(), "Please use numbers.",
                        Toast.LENGTH_SHORT
                ).show();
            }
            return false;
        }
    }

    /****
     * function showViewOne set view one visible and set submitButton and secondButton true
     */
    private void showViewOne(){
        findViewById(R.id.secondLayout).setVisibility(View.GONE);
        findViewById(R.id.thirdLayout).setVisibility(View.GONE);
        findViewById(R.id.firstLayout).setVisibility(View.VISIBLE);
        firstButton.setEnabled(false);
        secondButton.setEnabled(true);
        activeView = 1;
        try
        {
            Integer.parseInt(firstNumber.getText().toString().trim());
            Integer.parseInt(secondNumber.getText().toString().trim());
            submitButton.setEnabled(true);
        } catch (NumberFormatException ex)
        {
            submitButton.setEnabled(false);
        }
    }

    /****
     * function showViewTwo set view two visible and set firstButton and submitButton true
     */
    private void showViewTwo(){
        findViewById(R.id.secondLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.firstLayout).setVisibility(View.GONE);
        findViewById(R.id.thirdLayout).setVisibility(View.GONE);
        firstButton.setEnabled(true);
        secondButton.setEnabled(false);
        activeView = 2;
        try
        {
            Integer.parseInt(thirdNumber.getText().toString().trim());
            Integer.parseInt(fourthNumber.getText().toString().trim());
            submitButton.setEnabled(true);
        } catch (NumberFormatException ex)
        {
            submitButton.setEnabled(false);
        }
    }

    /****
     * function showViewThree set view three visible and set firstButton and secondButton true
     * calculates the given numbers
     */
    private void showViewThree(){
        findViewById(R.id.thirdLayout).setVisibility(View.VISIBLE);

        findViewById(R.id.firstLayout).setVisibility(View.GONE);
        findViewById(R.id.secondLayout).setVisibility(View.GONE);
        firstButton.setEnabled(true);
        secondButton.setEnabled(true);
        submitButton.setEnabled(false);
        activeView =3;
        if ((multiplicand >= 0) && (multiplier >=0)){
            int summary = multiplicand* multiplier;
            sum.setText("Result is: " + summary);
        }
    }

    /*******
     * onStart display firstLayout
     */

    @Override
    public void onStart(){
        super.onStart();
        findViewById(R.id.firstLayout).setVisibility(View.VISIBLE);
        findViewById(R.id.secondLayout).setVisibility(View.GONE);
        secondButton.setEnabled(true);
    }

    /*******
     * onResume display the last activated view
     */
    @Override
    public void onResume(){
        super.onResume();
        if (activeView ==1){
            showViewOne();
        }else if(activeView == 2){
            showViewTwo();
        }else{
            showViewThree();
        }
    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onStop(){
        super.onStop();
    }
}
