package com.example.clockgamedjakaridja

/*ClockGameDjakaridja.kt
* CIS 211 900 ONL 15A SP21
* submitted to professor Laurence Liss
* last edited 03/05/2021
* This program is designed to keep track of a check game in order to determine the winner based on the finish time
*/
import android.os.Bundle
import android.os.CountDownTimer
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {


    lateinit var Timer1: CountDownTimer
    lateinit var Timer2: CountDownTimer
    var Timer1Running = false
    var Timer2Running = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button1: Button = findViewById(R.id.button1)             // code set to link the button in the design table to the MainActivity
        val button2: Button = findViewById(R.id.button2)
        val resetButton: Button = findViewById(R.id.resetButton)

        button1.setOnClickListener {                      // this bock of code will execute when the button1 is enable. The timer of the player 1 starts
            button1.isClickable = false
            button1.isEnabled = false
            button2.isClickable = true
            button2.isEnabled = true

            Timer1 = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    button1.text = ((millisUntilFinished / 1000) % 60).toString()
                    Timer1Running = true
                }
                override fun onFinish() {
                    Timer1Running = false
                    button1.text = " Player 1 won the game"

                }
            }.start()
            onPause()                                     // onPause() will stop the timer when the button2 is enable
        }


        button2.setOnClickListener {               // this bock of code will execute when the button2 is enable. The timer of the player 2 starts
            button1.isClickable = true
            button1.isEnabled = true
            button2.isClickable = false
            button2.isEnabled = false

            Timer2 = object : CountDownTimer(30000, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    button2.text = ((millisUntilFinished / 1000) % 60).toString()
                    Timer2Running = true

                }

                override fun onFinish() {
                    Timer2Running = false
                    button2.text = " Player 2 won the game"

                }
            }.start()
            onPause()                                // onPause() will stop the timer when the button1 is enable
        }

        resetButton.setOnClickListener {      // this block od code is set to reset the timer to 30s
            Timer1.cancel()
            Timer2.cancel()
            Timer1Running = false
            Timer2Running = false
            button1.isEnabled = true
            button2.isEnabled = true
            button1.isClickable = true
            button2.isClickable = true
            button1.text = "Timer Reset: Click to start timer"
            button2.text = "Timer Reset: Click to start timer"
        }


    }


    override fun onPause() {                         // onPause method set to switch the countdown. this method will be called to pause the timer
        super.onPause()
        if (Timer1Running){
            Timer1Running = false
            Timer1.cancel()

        }
        else if(Timer2Running){
            Timer2Running = false
            Timer2.cancel()

        }
    }


}    // end of the program