package com.christiencdev.quiznum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
    //define necessary component variables
    private Button buttonConfirm;
    private TextView textViewLast, textViewRight, textViewHint;
    private EditText editTextGuess;

    //transfers number of digits chosen from main activity
    boolean twoDigits, threeDigits, fourDigits;

    //randomizes numbers
    Random r = new Random();
    int random;
    int remainingRight = 10;

    //will store user guesses
    ArrayList<Integer> guessesList = new ArrayList<>();
    int userAttempts = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //link component variables to ids
        buttonConfirm = findViewById(R.id.buttonConfirm);
        textViewHint = findViewById(R.id.textViewHint);
        textViewLast = findViewById(R.id.textViewLast);
        textViewRight = findViewById(R.id.textViewRight);
        editTextGuess = findViewById(R.id.editTextGuess);

        //will get intent chosen from main activity
        twoDigits = getIntent().getBooleanExtra("two", false);
        threeDigits = getIntent().getBooleanExtra("three", false);
        fourDigits = getIntent().getBooleanExtra("four", false);

        if (twoDigits)
        {
            //enforces a 2 digit number from 10 - 99
            random = r.nextInt(90)+10;
        }
        if (threeDigits)
        {
            //enforces a 3 digit number from 100 - 990
            random = r.nextInt(900)+10;
        }
        if (fourDigits)
        {
            //enforces a 4 digit number from 1000 - 9900
            random = r.nextInt(9000)+10;
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String guess = editTextGuess.getText().toString();
                //if guess empty
                if (guess.equals(""))
                {
                    Toast.makeText(GameActivity.this, "Go ahead, make a guess!", Toast.LENGTH_LONG).show();
                }
                //if guess entered
                else
                {
                    textViewLast.setVisibility(View.VISIBLE);
                    textViewRight.setVisibility(View.VISIBLE);
                    textViewHint.setVisibility(View.VISIBLE);

                    userAttempts++;
                    remainingRight--;

                    int userGuess = Integer.parseInt(guess);
                    guessesList.add(userGuess);
                    textViewLast.setText("Previous Guess: " + guess);
                    textViewRight.setText("Attempts Remaining: " + remainingRight);

                    if (random == userGuess)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("GAME OVER");
                        builder.setCancelable(false);
                        builder.setMessage("Congrats! The answer was: " + random + "\n\nYou made " + userAttempts + " attempts \n\nYour guesses were: " + guessesList);
                        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();
                    }
                    if (userGuess < random)
                    {
                        textViewHint.setText("Hint: Guess Higher");
                    }
                    if (userGuess > random)
                    {
                        textViewHint.setText("Hint: Guess Lower");
                    }
                    if (remainingRight == 0)
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                        builder.setTitle("GAME OVER");
                        builder.setCancelable(false);
                        builder.setMessage("Oops! The correct guess was: " + random + "\n\nYou made " + userAttempts + " attempts \n\nYour guesses were: " + guessesList );
                        builder.setPositiveButton("Play Again", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent(GameActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                moveTaskToBack(true);
                                android.os.Process.killProcess(android.os.Process.myPid());
                                System.exit(1);
                            }
                        });

                        builder.create().show();
                    }

                    editTextGuess.setText("");
                }
            }
        });
    }
}