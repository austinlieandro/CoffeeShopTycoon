package com.austin.coffeshoptycoon

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {
    companion object{
        val BALANCEUPDATE = "BALANCEUPDATE"
        val DAYUPDATE = "DAYUPDATE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        var sellCup = intent.getIntExtra(SimulationActivity.SELLCUP,0).toString().toInt()
        var pricePerCup = intent.getIntExtra(SimulationActivity.PRICEPERCUP,0).toString().toInt()
        var expenses = intent.getIntExtra(SimulationActivity.EXPENSES,0).toString().toInt()

        var revenues = sellCup * pricePerCup

        var profit = revenues - expenses

        Global.balance = Global.balance+revenues

        txtDayResult.text = "DAY ${Global.day} RESULT"

        txtCupofCoffee.text = "${sellCup.toString()} cup of coffee"
        txtPriceCupofCoffee.text = "@IDR ${pricePerCup.toString()}"
        lblPriceCupCoffee.text = "IDR ${revenues.toString()}"

        lblLocRentCost.text = "IDR ${expenses.toString()}"

        lblProfit.text ="IDR ${profit.toString()}"

        btnStartNewDay.setOnClickListener(){
            if(Global.balance < 101700){
                val builder = AlertDialog.Builder(this)
                builder.setMessage("You have gone bankrupt")
                builder.setPositiveButton("PLAY AGAIN", DialogInterface.OnClickListener { dialogInterface, i ->
                    Global.balance = 350000
                    Global.day = 1
                    var intent = Intent(this,PreparationActivity::class.java)

                    startActivity(intent)
                })
                builder.setNegativeButton("EXIT", DialogInterface.OnClickListener { dialogInterface, i ->
                    finish()
                })
                builder.create().show()
            }
            else{
                var intent = Intent(this,PreparationActivity::class.java)

                Global.day++

                startActivity(intent)

                finish()
            }
        }
    }
}