package com.example.zadanie3android

import android.content.Context
import android.text.Spannable.Factory
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import kotlinx.coroutines.launch

class MyViewModel(context: Context): ViewModel() {
    private lateinit var myRepository: MyRepository
    private var dataList: MutableList<DBItem>? = null
   // private var dataList: LiveData<List<DBItem>>? = null
//    private var myDao: MyDao
//    private var db: MyDB

    init {
        myRepository = MyRepository(context)
        dataList = myRepository.getData()
    }

    //uncomment later
//    fun getDataList2():LiveData<List<DBItem>>{
//        return dataList
//    }


//    companion object{
//        private var R_INSTANCE: MyRepository? = null
//        fun getinstance(context: Context): MyRepository {
//            if (R_INSTANCE == null){
//                R_INSTANCE = MyRepository(context)
//            }
//            return R_INSTANCE as MyRepository
//        }
//    }
//    init {
//        db = MyDB.getDatabase(context)!!
//        myDao = db.myDao()!!
//
//
//    }

    fun getData(): MutableList<DBItem>? {
        dataList = myRepository.getData()
        return dataList
    }
    fun addItem(item: DBItem?){
        viewModelScope.launch { myRepository.addItem(item) }
    }
    fun deleteItem(item: DBItem?) {
        viewModelScope.launch { myRepository.deleteItem(item) }
//        if(myDao.delete(item) > 0) return true
//        else return false
    }
    fun updateItem(item: DBItem){
        viewModelScope.launch { myRepository.updateItem(item) }
    }
    fun getDataById(id: Long): DBItem?{
//        viewModelScope.launch { myRepository.getDataById(id) }
        return myRepository.getDataById(id)
    }
    companion object{
        val Factory :ViewModelProvider.Factory = object:ViewModelProvider.Factory {
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras): T {
                val application = checkNotNull(extras[APPLICATION_KEY])
                return MyViewModel(application.applicationContext) as T
            }
        }
    }

}