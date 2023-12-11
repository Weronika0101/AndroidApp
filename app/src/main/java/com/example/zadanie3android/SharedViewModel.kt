package com.example.zadanie3android
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val invitationText = MutableLiveData<String>()
    val infoText = MutableLiveData<String>()
    private val _data = MutableLiveData<MutableList<DataItem>>()
    val data: LiveData<MutableList<DataItem>> get() = _data
    val selectedImageResId = MutableLiveData<Int>()

    fun setSelectedImage(resId: Int) {
        selectedImageResId.value = resId
        Log.d("SharedViewModel", "Selected image set to: $resId")
    }

    fun setData(newData: MutableList<DataItem>) {
        _data.value = newData
    }
}
