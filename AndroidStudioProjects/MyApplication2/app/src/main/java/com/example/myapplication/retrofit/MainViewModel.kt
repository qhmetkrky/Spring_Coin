package com.example.myapplication.retrofit

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.GoldRate
import com.example.myapplication.data.PriceDateRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.math.truncate


class MainViewModel : ViewModel() {

    var postResponse = MutableLiveData<Response<User>>()
    var postResponse_log = MutableLiveData<Response<Login_user>>()
    var _post = MutableLiveData<User>()
    var _postOne = MutableLiveData<Login_user>()
    var _errorMessage = MutableLiveData<String?>(null)
    var _isLoadind = MutableLiveData<Boolean>(false)
    var _gold = MutableLiveData<AltinResponse>()
    val _golResponse = MutableLiveData<Response<AltinResponse>>()
    private val _goldRates = MutableStateFlow<List<GoldRate>>(emptyList())
    val goldRates: StateFlow<List<GoldRate>> = _goldRates
    private val _goldPrice = MutableStateFlow<String?>(null)
    val goldPrice: StateFlow<String?> get() = _goldPrice
    private val _isLoading = MutableStateFlow<Boolean>(true)
    val isLoading: MutableStateFlow<Boolean> get() = _isLoading



    var token: String? = null
    private val _loginResponse = mutableStateOf<Login_response?>(null)
    val loginResponse: State<Login_response?> = _loginResponse
    fun fetchpost(user: User) {
        viewModelScope.launch {
            _isLoadind.value = true

            try {
                val response = RetrofitInstance.api.saveUser(user)
                if (response.isSuccessful) {
                    response.body()?.let { post ->
                        _post.value = post
                        postResponse.value = response
                        _errorMessage.value = null // hata yok
                        delay(2000)

                    }
                } else {
                    // Backend'den gelen hatayı string olarak al
                    val errorBody = response.errorBody()?.string()
                    _errorMessage.value = errorBody ?: "Bilinmeyen bir hata oluştu"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ağ hatası: ${e.message}"
            } finally {
                _isLoadind.value = false
            }


            _isLoadind.value = false

        }
    }


    fun login(login: Login_user, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            _isLoadind.value = true
            try {
                val response = RetrofitInstance.api.login(login)
                if (response.isSuccessful) {
                    response.body()?.let { post ->
                        _loginResponse.value = post
                        token = post.token
                        println("toke $token")
                        onResult(true) // Başarılı → Coin sayfasına geç
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    _errorMessage.value = errorBody ?: "Bilinmeyen hata"
                    onResult(false)
                }
            } catch (e: Exception) {
                _errorMessage.value = "Ağ hatası: ${e.message}"
                onResult(false)
            } finally {
                _isLoadind.value = false
            }
        }
    }

    fun fetchGoldPrice(symbol: String = "XAU", currency: String = "USD", date: String? = "") {
        viewModelScope.launch(Dispatchers.IO) {

            _isLoading.value=true

            try {
                val response = RetrofitInstance.api2.getGoldPrice(symbol,currency,date)
                if (response.isSuccessful) {
                    _goldPrice.value=response.body()?.price_gram_10k.toString()
                } else {
                    _goldPrice.value = "Error: ${response.errorBody()?.string()}"
                }

            } catch (e: Exception) {
                _goldPrice.value = "Error: ${e.message}"
            }
            finally {
                delay(3000)
                _isLoading.value=false
            }


        }
    }



    fun updateAmount(amount: Double, onResult: (Boolean) -> Unit) {
            val currentToken = token
            println("token $currentToken")
            if (currentToken.isNullOrEmpty()) {
                onResult(false)
                return
            }

            viewModelScope.launch {
                _isLoadind.value = true
                try {
                    val response = withContext(Dispatchers.IO) {
                        RetrofitInstance.api.updateAmount(
                            token = "Bearer $currentToken",
                            amount = mapOf("amount" to amount)
                        )
                    }
                    withContext(Dispatchers.Main) {
                        onResult(response.isSuccessful)
                    }
                } catch (e: Exception) {
                    println("api cagrı hata")
                    onResult(false)
                } finally {
                    _isLoadind.value = false
                }
            }
        }



}