package com.example.zadanie3android

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class MyRepository(context: Context) {
    private var dataList: MutableList<DBItem>? = null //tu tez bedzie livedata lub flow
    private var dataList2: LiveData<List<DBItem>>? = null //tu tez bedzie livedata lub flow
    private var dataList3: Flow<List<DBItem>>? = null //tu tez bedzie livedata lub flow
    private var myDao: MyDao
    private var db: MyDB


    companion object{
        private var R_INSTANCE: MyRepository? = null
        fun getinstance(context: Context): MyRepository {
            if (R_INSTANCE == null){
                R_INSTANCE = MyRepository(context)
            }
            return R_INSTANCE as MyRepository
        }
    }
    init {
        db = MyDB.getDatabase(context)!!
        myDao = db.myDao()!!


    }

    fun getData(): MutableList<DBItem>? {
        dataList = myDao.getAllData()
        return dataList
    }

    fun getData2(): LiveData<List<DBItem>>? {
        dataList2 = myDao.getAllData2()
        return dataList2
    }

    fun getData3(): Flow<List<DBItem>>? {
        dataList3 = myDao.getAllData3()
        return dataList3
    }

    fun addItem(item: DBItem?) : Boolean {
        if(item?.let { myDao.insert(it) }!! >= 0) return true
        else return false
    }
    fun deleteItem(item: DBItem?) : Boolean {
        if(item?.let { myDao.delete(it) }!! > 0) return true
        else return false
    }
    fun updateItem(item: DBItem): Boolean {
        val result = myDao.update(item)
        return result > 0
    }
    fun getDataById(id: Long): DBItem?{
        return myDao.getItemById(id)
    }
}