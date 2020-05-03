package com.subha.widgets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textView = (TextView) findViewById(R.id.secondActivityTextView);
        button = (Button) findViewById(R.id.secondActivityButton);
        Bundle extras = getIntent().getExtras();

        if(extras != null) {
            String message = extras.get("message").toString();
            textView.setText(message);
        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = getIntent();
                returnIntent.putExtra("returnData", "From second activity!");
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
    }
}
