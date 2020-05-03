package com.subha.widgets;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    //Radio Group and Radio Button
    private RadioGroup radioGroup;
    private RadioButton radioButton;

    //Seek bar
    private SeekBar seekBar;
    private TextView resultTextView;

    //Toggle button
    private ToggleButton toggleButton;
    private TextView pickABooTextView;

    //Check box
    private CheckBox momCheckBox;
    private CheckBox dadCheckBox;
    private CheckBox grandPaCheckBox;
    private Button selectButton;
    private TextView favPersonResultTextView;

    //Alert Dialog
    private AlertDialog.Builder alertDialog;
    private Button alertDialogButton;

    //Navigate
    private Button navigateButton;


    //savedInstanceState - holds all the properties available in onCreate method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //radioGroupInitiate();
        //seekBarInitiate();
        //toggleButtonInitiate();
        //checkBoxInitiate();
        //alertDialogInitiate();
        navigateActivity();
    }

    private void navigateActivity() {
        setContentView(R.layout.activity_navigate);
        navigateButton = (Button) findViewById(R.id.navigateButton);
        navigateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent is the action.
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("message", "Hello from first activity!");
                //startActivity(intent);
                //expecting a result
                startActivityForResult(intent, 0);
            }
        });
    }

    //Gets called when the second activity passes data to this activity
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            if(resultCode == RESULT_OK) {
                String result = data.getStringExtra("returnData");

                Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void alertDialogInitiate() {
        setContentView(R.layout.alert_dialog);

        alertDialogButton = (Button) findViewById(R.id.alertDialogButton);
        alertDialog = new AlertDialog.Builder(this);

        alertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.setTitle(R.string.alertTitle);
                alertDialog.setMessage(R.string.alertMessage);
                alertDialog.setIcon(android.R.drawable.star_big_on);
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton(R.string.YES, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                });
                alertDialog.setNegativeButton(R.string.NO, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                AlertDialog alertDialogBuilt = alertDialog.create();
                alertDialogBuilt.show();
            }
        });
    }

    private void checkBoxInitiate() {
        setContentView(R.layout.check_box);
        momCheckBox = (CheckBox) findViewById(R.id.momCheckBox);
        dadCheckBox = (CheckBox) findViewById(R.id.dadCheckBox);
        grandPaCheckBox = (CheckBox) findViewById(R.id.grandPaCheckBox);

        selectButton = (Button) findViewById(R.id.selectFavPersonButton);
        favPersonResultTextView = (TextView) findViewById(R.id.favPersonResultTextView);

        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder sb = new StringBuilder();
                sb.append(momCheckBox.getText().toString() + " status is: " + momCheckBox.isChecked() + "\n");
                sb.append(dadCheckBox.getText().toString() + " status is: " + dadCheckBox.isChecked() + "\n");
                sb.append(grandPaCheckBox.getText().toString() + " status is: " + grandPaCheckBox.isChecked() + "\n");
                favPersonResultTextView.setText(sb.toString());
            }
        });

    }

    private void toggleButtonInitiate() {
        setContentView(R.layout.toggle_button);
        toggleButton = (ToggleButton) findViewById(R.id.toggleButton);
        pickABooTextView = (TextView) findViewById(R.id.pickABooTextView);

        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    pickABooTextView.setVisibility(View.VISIBLE);
                } else {
                    pickABooTextView.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    private void seekBarInitiate() {
        setContentView(R.layout.seek_bar);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        resultTextView = (TextView) findViewById(R.id.resultTextView);
        resultTextView.setText("Pain Level: 0/10");
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d("SB", "onProgressChanged");
                resultTextView.setText("Pain Level: " + seekBar.getProgress() + "/" + seekBar.getMax());
                if(seekBar.getProgress() <= 7) {
                    resultTextView.setTextColor(Color.GRAY);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d("SB", "onStartTrackingTouch");
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if(seekBar.getProgress() > 7) {
                    resultTextView.setTextColor(Color.RED);
                }
                Log.d("SB", "onStopTrackingTouch");
            }
        });

    }

    private void radioGroupInitiate() {
        setContentView(R.layout.activity_main);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioButton = (RadioButton) findViewById(R.id.radioButtonNoId);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton = (RadioButton) findViewById(checkedId);

                switch (radioButton.getId()) {
                    case R.id.radioButtonYesId:
                        Log.d("RD", "YES");
                        break;
                    case R.id.radioButtonNoId:
                        Log.d("RD", "NO");
                        break;
                    case R.id.radioButtonMayBeId:
                        Log.d("RD", "MAY BE");
                }
            }
        });
    }
}
