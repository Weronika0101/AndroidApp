package com.example.zadanie3android

import kotlin.random.Random
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "item_table")
class DBItem {
    @PrimaryKey(autoGenerate = true)
    var id = 0
    var text_main : String = ""
    @ColumnInfo(name = "sport")
    var text_sport : String =""
    @ColumnInfo(name = "beginner")
    var item_beginner : Boolean = Random.nextBoolean()
    @ColumnInfo(name = "rating")
    var item_trudnosc = Random.nextInt(0, 5)
    @ColumnInfo(name = "opis")
    var item_opis: String = "Default opis"
    @ColumnInfo(name = "typ")
    var item_type : Int = Random.nextInt(0, 4)
    constructor()
    constructor(text_sport:String, item_beginner:Boolean,item_trudnosc: Int, item_opis: String) : this() {
        this.text_sport=text_sport
        this.item_beginner=item_beginner
        this.item_trudnosc=item_trudnosc
        this.item_opis=item_opis
        this.item_type=Random.nextInt(0, 4)
        this.text_main = "Element "


    }
//    @PrimaryKey(autoGenerate = true)
//    var id = 0
//    var text_main: String? = null
//    var text_2: String? = null
//    @ColumnInfo(name = "rating")
//    var item_value = 0
//    @ColumnInfo(name = "age")
//    var item_value2 = 0
//    @ColumnInfo(name = "sex")
//    var item_type = false
//    constructor()
//    constructor(num: Int) : this(){
//        text_main = "Item name " + num
//        text_2 = "Default text" + num
//        item_value = Random.nextInt(0, 5)
//        item_value2 = Random.nextInt(0, 100)
//        item_type = Random.nextBoolean()
//    }
}

