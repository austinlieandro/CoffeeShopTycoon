package com.austin.coffeshoptycoon

object Global {
    var day = 1
    var balance = 350000
    var playerName = ""

    var location:Array<Locations> = arrayOf(
        Locations(1, "University", 100000),
        Locations(2, "Business District", 350000),
        Locations(3, "Beach", 500000)
    )

    var weather:List<Weather> = listOf(
        Weather("Sunny Day"),
        Weather("Rainy day"),
        Weather("Thunderstorm")
    )

    val simulation:ArrayList<Simulation> = ArrayList(
        listOf()
    )

    val recipes:ArrayList<Recipes> = ArrayList(
        listOf(
            Recipes("No Item Selected", "0","0","0"),
            Recipes("Mocha Coffee", "5", "3", "2"),
            Recipes("Americano", "10", "3", "1")
    )
    )
}