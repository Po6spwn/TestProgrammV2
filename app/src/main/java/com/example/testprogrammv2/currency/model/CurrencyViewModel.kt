package com.example.testprogrammv2.currency.model

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprogrammv2.currency.`object`.xml.RecordV
import com.example.testprogrammv2.currency.`object`.xml.ValCurs
import com.example.testprogrammv2.currency.network.ConnectionResult
import com.example.testprogrammv2.currency.network.RetrofitBuilder
import kotlinx.coroutines.launch
import kotlin.collections.ArrayList

class CurrencyViewModel : ViewModel() {

//    private val _liveDataCurrency = MutableLiveData<ArrayList<RecordV>>()
//    var liveDataCurrency: LiveData<ArrayList<RecordV>> = _liveDataCurrency

    private val _liveDataCurrency = MutableLiveData<ConnectionResult>()
    var liveDataCurrency: LiveData<ConnectionResult> = _liveDataCurrency

    private var dateBegin: String? = null
    private var dateEnd: String? = null

    fun getValue() {
        viewModelScope.launch {
            try {
                val response = RetrofitBuilder.apiService.getValueForMonth(dateBegin!!, dateEnd!!, "R01235")

                if (response.isSuccessful) {
                    _liveDataCurrency.value = ConnectionResult(success = createArray(response.body()!!))
                }
            } catch (t: Throwable) {
                Log.d("ErrorTAG", "Error: ${t.message}")
                _liveDataCurrency.value = ConnectionResult(error = t.message.toString())
            }
        }
    }

    private fun createArray(valCurs: ValCurs) : ArrayList<RecordV>
    {
        val recordList = ArrayList<RecordV>()
        for (i in valCurs.Record.indices) {
            val obj = valCurs.Record[i]
            obj.Value = obj.Value.replace(",", ".")
            obj.Date = obj.Date.removeRange(5,10)
            recordList.add(RecordV(obj.Date, obj.Value))
        }
        return  recordList
    }

    fun setDate(date_begin: String, date_end: String)
    {
        this.dateBegin = date_begin
        this.dateEnd = date_end
    }

}
