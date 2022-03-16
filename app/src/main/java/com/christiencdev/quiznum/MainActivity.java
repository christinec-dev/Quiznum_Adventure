package com.christiencdev.quiznum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    //define necessary component variables
    private Button buttonStart;
    private RadioButton radio2, radio3, radio4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link component variables to ids
        buttonStart = findViewById(R.id.buttonStart);
        radio2 = findViewById(R.id.radio2);
        radio3 = findViewById(R.id.radio3);
        radio4 = findViewById(R.id.radio4);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);

                //if no option is selected, force user
                if (!radio2.isChecked() && !radio3.isChecked() && !radio4.isChecked())
                {
                    Snackbar.make(view, "Hmm, you need to choose a game mode.", Snackbar.LENGTH_LONG).show();
                }
                //else start game intent
                else
                {
                    if (radio2.isChecked())
                    {
                        //key for game menu
                        intent.putExtra("two", true);
                    }
                    if (radio3.isChecked())
                    {
                        intent.putExtra("three", true);
                    }
                    if (radio4.isChecked())
                    {
                        intent.putExtra("four", true);
                    }

                    startActivity(intent);
                }
            }
        });
    }
}