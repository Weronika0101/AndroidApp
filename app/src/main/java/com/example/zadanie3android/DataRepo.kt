package com.example.zadanie3android


class DataRepo {
    val LIST_SIZE = 20
    //    val item_text_list = Array(LIST_SIZE) { i -> "Item name " +i}
    private lateinit var dataList: MutableList<DataItem>


    init {
        dataList = MutableList(LIST_SIZE) { i -> DataItem(i)}
    }
    companion object{
        private var INSTANCE: DataRepo? = null
        fun getinstance(): DataRepo {
            if (INSTANCE == null){
                INSTANCE = DataRepo()
            }
            return INSTANCE as DataRepo
        }
    }

    fun getData() : MutableList<DataItem> {
        return dataList
    }

    fun addItem(dataItem: DataItem) {
        dataList.add(dataItem)
    }

    fun deleteItem(position: Int) : Boolean{
        if (position in 0 until dataList.size) {
            dataList.removeAt(position)
            return true
        }
        return false
    }

    fun updateItem(position: Int, newDataItem: DataItem) {
        if (position in 0 until dataList.size) {
            dataList[position] = newDataItem
        }
    }
}