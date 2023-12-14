package com.example.zadanie3android

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

//import koltinx.corutines.flow.Flow


@Dao
interface MyDao {
    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData(): MutableList<DBItem> //bedzie livedata potem albo flow

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData2(): LiveData<List<DBItem>> //bedzie livedata potem albo flow

    @Query("SELECT * FROM item_table ORDER BY id ASC")
    fun getAllData3(): Flow<List<DBItem>> //bedzie livedata potem albo flow

    @Query("DELETE FROM item_table")
    fun deleteAll()
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(item: DBItem) : Long
    @Delete
    fun delete(item: DBItem) : Int
    @Update
    fun update(item: DBItem): Int
    @Query("SELECT * FROM item_table WHERE id = :itemId")
    fun getItemById(itemId: Long): DBItem?

}