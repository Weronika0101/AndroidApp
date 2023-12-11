package com.example.zadanie3android

import kotlin.random.Random

class DataItem {
    var text_main : String = "Default text"
    var text_sport : String = "Default text"
    var item_beginner : Boolean = Random.nextBoolean()
    var item_trudnosc = Random.nextInt(0, 5)
    var item_opis: String = "Default opis"
    var item_type : Int = Random.nextInt(0, 4)
    constructor()
    constructor(num: Int) : this() {
        val num2 = num+1
        text_main = "Element $num2"
        item_opis = "Eeeee"

    }
}
