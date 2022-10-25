package com.austin.coffeshoptycoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_preparation.*
import kotlin.random.Random.Default.nextInt

class PreparationActivity : AppCompatActivity() {
    companion object{
        val PLAYERNAME = "PLAYERNAME"
        val SELLING = "SELLING"
        val PRICEALL = "PRICEALL"
        val BALANCE = "BALANCE"
        val PRICESELL = "PRICESELL"
        val TOTAL = "TOTAL"
        val WEATHER = "WEATHER"
        val LOCATION = "LOCATION"
        val LOCATIONPRICE = "LOCATIONPRICE"
        val DAY = "DAY"
    }

    var coffeePrice = 500
    var milkPrice = 1000
    var waterPrice = 200

    var countCoffe = 0
    var countMilk = 0
    var countWater = 0

    var totalPriceCoffe = coffeePrice * countCoffe
    var totalPriceMilk = milkPrice * countMilk
    var totalPriceWater = waterPrice * countWater

    var totalPriceAll = totalPriceCoffe + totalPriceMilk + totalPriceWater

    var priceRent = 0

    lateinit var textViewCoffee: TextView
    lateinit var totalCoffee: EditText
    lateinit var inputCoffee: EditText
    lateinit var inputMilk: EditText
    lateinit var inputWater: EditText
    lateinit var inputPriceSell: EditText

    lateinit var inputTemplateRecipes: EditText

    var totalAllCoffee = 0
    var totalCoffee1 = 0

    var priceSell = 0

    var total = priceRent + totalCoffee1

    var weather : Weather = Global.weather[0]

    var playerName = ""

    var indexSpinner = ""

    var templateRrcipes = ""
    var inputCoffee1 = ""
    var inputMilk1 = ""
    var inputWater1 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preparation)

        tampilinData()

        txtDay.text = "DAY ${Global.day}"

        val arrWeather : List<Weather> = Global.weather.toList()
        val randomArrWeather = arrWeather.shuffled()
        val indexWeather = nextInt(until = 3)
        weather = randomArrWeather.get(indexWeather)
        txtWeather.text = weather.name

        totalCoffee = findViewById(R.id.txtInputSell) as EditText

        textViewCoffee = findViewById(R.id.txtTotal) as TextView

        inputCoffee = findViewById(R.id.txtInputCoffee) as EditText

        inputMilk = findViewById(R.id.txtInputMilk) as EditText

        inputWater = findViewById(R.id.txtInputWater) as EditText

        inputPriceSell = findViewById(R.id.txtInputPrice) as EditText

        inputTemplateRecipes = findViewById(R.id.txtSaveRecipes) as EditText

        txtWelcome.text = "Welcome, ${Global.playerName}"

        txtBalance.text = "Balance : IDR ${Global.balance.toString()}"

        val adapter = ArrayAdapter(this, R.layout.activity_spinner_layout, Global.location)
        adapter.setDropDownViewResource(R.layout.activity_spinner_item_layout)

        spinLocation.adapter = adapter

        spinLocation.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                txtLocationRent.text = Global.location[p2].namaTempat.toString()
                txtTotalPriceRent.text = "IDR " + Global.location[p2].price.toString()
                indexSpinner = Global.location[p2].namaTempat.toString()
                priceRent = Global.location[p2].price.toInt()
                tampilinData()
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        addToSpinnerSaveRecipes()

        btnSaveRecipes.setOnClickListener {
            if(inputTemplateRecipes.text.toString() == ""){
                Toast.makeText(this, "Please input your name of template recipes", Toast.LENGTH_SHORT).show()
            }else{
                addToSpinnerSaveRecipes()
                saveDataTemplate()
                txtSaveRecipes.setText("")
            }
        }

        spinTemplateRecipes.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                if (Global.recipes[p2] == Global.recipes[0]){

                }
                else{
                    txtInputCoffee.setText(Global.recipes[p2].coffee)
                    txtInputMilk.setText(Global.recipes[p2].milk)
                    txtInputWater.setText(Global.recipes[p2].water)
                    tampilinData()
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }

        txtInputCoffee.addTextChangedListener(){
            if(inputCoffee.text.toString() != ""){
                countCoffe = inputCoffee.text.toString().toInt()
                totalPriceCoffe = coffeePrice * countCoffe
                tampilinData()
            }else{
                countCoffe = 0
                totalPriceCoffe = coffeePrice * countCoffe
                tampilinData()
            }
        }

        txtInputMilk.addTextChangedListener(){
            if(inputMilk.text.toString() != ""){
                countMilk = inputMilk.text.toString().toInt()
                totalPriceMilk = milkPrice * countMilk
                tampilinData()
            }else{
                countMilk = 0
                totalPriceMilk = milkPrice * countMilk
                tampilinData()
            }
        }

        txtInputWater.addTextChangedListener(){
            if (inputWater.text.toString() != ""){
                countWater = inputWater.text.toString().toInt()
                totalPriceWater = waterPrice * countWater
                tampilinData()
            }else{
                countWater = 0
                totalPriceWater = waterPrice * countWater
                tampilinData()
            }
        }

        txtInputSell.addTextChangedListener(){
            if(totalCoffee.text.toString() != ""){
                totalAllCoffee = totalCoffee.text.toString().toInt()
                tampilinData()
            }
            else{
                totalAllCoffee = 0
                tampilinData()
            }
        }

        txtInputPrice.addTextChangedListener(){
            if(inputPriceSell.text.toString() != ""){
                priceSell = inputPriceSell.text.toString().toInt()
            }
            else{
                priceSell = 1
            }
        }

        btnUpCoffee.setOnClickListener {
            countCoffe += 1
            txtInputCoffee.setText(countCoffe.toString())
            tampilinData()
        }
        btnDownCoffee.setOnClickListener {
            if (countCoffe <= 0){
                Toast.makeText(this, "Sorry Can't be less than 0", Toast.LENGTH_SHORT).show()
            }
            else {
                countCoffe -= 1
                txtInputCoffee.setText(countCoffe.toString())
                tampilinData()
            }
        }

        btnUpMilk.setOnClickListener {
            countMilk += 1
            txtInputMilk.setText(countMilk.toString())
            tampilinData()
        }
        btnDownMilk.setOnClickListener {
            if (countMilk <= 0){
                Toast.makeText(this, "Sorry Can't be less than 0", Toast.LENGTH_SHORT).show()
            }
            else {
                countMilk -= 1
                txtInputMilk.setText(countMilk.toString())
                tampilinData()
            }
        }

        btnUpWater.setOnClickListener {
            countWater += 1
            txtInputWater.setText(countWater.toString())
            tampilinData()
        }
        btnDownWater.setOnClickListener {
            if (countWater <= 0){
                Toast.makeText(this, "Sorry Can't be less than 0", Toast.LENGTH_SHORT).show()
            }
            else {
                countWater -= 1
                txtInputWater.setText(countWater.toString())
                tampilinData()
            }
        }

        btnStartDay.setOnClickListener {
            if (countCoffe > 0){
                if (countMilk > 0){
                    if (countWater > 0){
                        if (totalAllCoffee > 0){
                            if (priceSell > 0){
                                if (total <= Global.balance){
                                    Global.balance = Global.balance - total
                                    tampilinData()
                                    toSimulationActivity()
                                }
                                else{
                                    Toast.makeText(this, "your balance is insufficient", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else{
                                Toast.makeText(this, "The selling price of a cup of coffee is at least IDR 1", Toast.LENGTH_SHORT).show()
                            }
                        }
                        else{
                            Toast.makeText(this, "The minimum number of cups of coffee to be sold is 1", Toast.LENGTH_SHORT).show()
                        }
                    }
                    else{
                        Toast.makeText(this, "The number of components of water is at least 1", Toast.LENGTH_SHORT).show()
                    }
                }
                else{
                    Toast.makeText(this, "The number of components of milk is at least 1", Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(this, "The number of components of coffee is at least 1", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun tampilinData(){
        if (Global.balance < total){
            btnStartDay.isEnabled = false
        }else{
            btnStartDay.isEnabled = true
        }
        totalPriceAll = totalPriceCoffe + totalPriceMilk + totalPriceWater
        totalPriceWater = waterPrice * countWater
        totalPriceMilk = milkPrice * countMilk
        totalPriceCoffe = coffeePrice * countCoffe

        txtCost.text = "Cost per cup of coffee is IDR $totalPriceAll how many cup do you want to sell ?"
        txtTotalCoffee.text = "$totalAllCoffee cup of coffee"
        txtSelling.text = "@IDR $totalPriceAll"
        txtTotalPrice.text = "IDR " + hitungTotal(totalAllCoffee, totalPriceAll)
        txtTotal.text = "IDR " + theRealTotal(hitungTotal(totalAllCoffee, totalPriceAll), priceRent)
        txtBalance.text = "Balance : IDR ${Global.balance.toString()}"
    }

    fun tampilDataTemplate(){
        inputCoffee.setText(countCoffe.toString())
        inputMilk.setText(countMilk.toString())
        inputWater.setText(countWater.toString())
        tampilinData()
    }

    fun addToSpinnerSaveRecipes(){
        val adapter1 = ArrayAdapter(this, R.layout.activity_spinner_layout, Global.recipes)
        adapter1.setDropDownViewResource(R.layout.activity_spinner_item_layout)
        spinTemplateRecipes.adapter = adapter1
    }

    fun saveDataTemplate(){
        tampilDataTemplate()
        if (countCoffe > 0){
            if (countMilk > 0) {
                if (countWater > 0){
                    templateRrcipes = inputTemplateRecipes.text.toString()
                    inputCoffee1 = inputCoffee.text.toString()
                    inputMilk1 = inputMilk.text.toString()
                    inputWater1 = inputWater.text.toString()
                    Global.recipes.add(Recipes(templateRrcipes, inputCoffee1, inputMilk1, inputWater1))
                    Toast.makeText(this, "Input Success", Toast.LENGTH_SHORT).show()
                    addToSpinnerSaveRecipes()
                    tampilinData()
                }else{
                    Toast.makeText(this, "The number of components of water is at least 1", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "The number of components of milk is at least 1", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "The number of components of coffee is at least 1", Toast.LENGTH_SHORT).show()
        }
    }

    fun hitungTotal(totalAllCoffee:Int, totalPriceAll:Int):Int{
        totalCoffee1 = totalAllCoffee * totalPriceAll
        return totalCoffee1
    }

    fun theRealTotal(hitungTotal:Int, priceRent:Int):Int{
        total = hitungTotal + priceRent
        return total
    }

    fun toSimulationActivity(){
        var intent = Intent(this, SimulationActivity::class.java)
        intent.putExtra("PLAYERNAME", playerName.toString())
        intent.putExtra("SELLING", totalAllCoffee.toString())
        intent.putExtra("PRICEALL", totalPriceAll.toString())
        intent.putExtra("PRICESELL", priceSell.toString())
        intent.putExtra("TOTAL", total.toString())
        intent.putExtra("WEATHER", weather.name.toString())
        intent.putExtra("LOCATION", indexSpinner.toString())
        intent.putExtra("LOCATIONPRICE", priceRent.toString())

        startActivity(intent)

        finish()
    }
}