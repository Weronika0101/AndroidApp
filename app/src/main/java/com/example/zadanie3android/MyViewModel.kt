package com.example.zadanie3android

//import android.content.Context
//import android.text.Spannable.Factory
//import android.util.Log
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.CreationExtras
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.launch
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.CreationExtras

class MyViewModel(applicationContext: Context): ViewModel() {
    private var myRepository: MyRepository
    private var dataList: MutableList<DBItem>? = null
//    private var myRepository: MyRepository
//    private var dataList: MutableList<DBItem>
//    private var dataList2: LiveData<List<DBItem>>
//    private var dataList3: Flow<List<DBItem>>
//    private var myDao: MyDao
//    private var db: MyDB
//    private var myDao: MyDao
//    private var db: MyDB
init {
    this.myRepository = MyRepository(applicationContext)
}

    fun getData(): MutableList<DBItem>? {
        dataList = myRepository.getData()
        return dataList
    }

    fun getDataFlow() = myRepository.getData3()
    fun getDataLiveData() = myRepository.getData2()

    fun addItem(item:DBItem): Boolean {
        return myRepository.addItem(item)
    }

    fun deleteItem(item: DBItem) {
        myRepository.deleteItem(item)
    }

    fun updateItem(item: DBItem) {
        myRepository.updateItem(item)
    }

//    init {
//        db = MyDB.getDatabase(context)!!
//        myDao = db.myDao()!!
//        myRepository = MyRepository(context)
//        Log.d("dupa", myRepository.toString())
//        dataList = myRepository.getData()!!
//        Log.d("dupaa", dataList.toString())
//        dataList2 = myRepository.getData2()!!
//        dataList3 = myRepository.getData3()!!
//    }
//
//    //uncomment later
////    fun getDataList2(): LiveData<List<DBItem>>? {
////        return dataList2
////    }
//
//
////    companion object{
////        private var R_INSTANCE: MyRepository? = null
////        fun getinstance(context: Context): MyRepository {
////            if (R_INSTANCE == null){
////                R_INSTANCE = MyRepository(context)
////            }
////            return R_INSTANCE as MyRepository
////        }
////    }
////    init {
////        db = MyDB.getDatabase(context)!!
////        myDao = db.myDao()!!
////
////
////    }
//
//
//    fun getData(): MutableList<DBItem>? {
//        // dataList = myRepository.getData()
//        dataList = myRepository.getData()!!
//        return dataList
//    }
//    fun getData2(): LiveData<List<DBItem>>? {
//       // dataList = myRepository.getData()
//        dataList2 = myRepository.getData2()!!
//        return dataList2
//    }
//
////    fun getData3(): Flow<List<DBItem>>? {
////        // dataList = myRepository.getData()
////        dataList3 = myRepository.getData3()
////        return dataList3
////    }
//
//    fun getData3(): Flow<List<DBItem>>? {
//        dataList3 = myRepository.getData3()!!
//        if (dataList3 == null) {
//            println("getData3() returned null")
//            Log.d("DUPA",dataList3.toString())
//        }
//        return dataList3
//    }

//    fun addItem(item: DBItem?){
//        viewModelScope.launch { myRepository.addItem(item) }
//    }
//    fun deleteItem(item: DBItem?) {
//        viewModelScope.launch { myRepository.deleteItem(item) }
////        if(myDao.delete(item) > 0) return true
////        else return false
//    }
//    fun updateItem(item: DBItem){
//        viewModelScope.launch { myRepository.updateItem(item) }
//    }
//    fun getDataById(id: Long): DBItem?{
////        viewModelScope.launch { myRepository.getDataById(id) }
//        return myRepository.getDataById(id)
//    }

//    companion object{
//        val Factory :ViewModelProvider.Factory = object:ViewModelProvider.Factory {
//            @Suppress("UNCHECKED CAST")
//            override fun <T : ViewModel> create(
//                modelClass: Class<T>,
//                extras: CreationExtras): T {
//                val application = checkNotNull(extras[APPLICATION_KEY])
//                return MyViewModel(application.applicationContext) as T
//            }
//        }
//    }
companion object {
    val Factory: ViewModelProvider.Factory = object :
        ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            return MyViewModel(application.applicationContext) as T
        }
    }
}

}