package com.alexapps.conversormoneda.main

import com.alexapps.conversormoneda.R

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.LiveData



class MainViewModel: ViewModel() {

    //tipo de dato que puede ser observado por fuera de la actividad afuera
    private val Resultado: MutableLiveData<String> = MutableLiveData()
    val resultadoDone: LiveData<String> = Resultado

    private val errorData: MutableLiveData<String> = MutableLiveData()
    val errorDataDone: LiveData<String> = errorData

    private val errorConver: MutableLiveData<String> = MutableLiveData()
    val errorConverDone: LiveData<String> = errorConver

    //funciones para trabajar

    fun convertir(moneda: String, cambio: String, cantida: String){

        if (cantida.isEmpty()){
            errorData.value = R.string.sin_valor.toString()
        }
        else {
            convertirMoneda(moneda, cambio, cantida)
        }
    }


    fun convertirMoneda(moneda: String, cambio: String, cantida: String) {
        var resultado: Double = 1.0
        var cantidad = cantida.toFloat()

        // condicional de conversion valida
        if ((moneda == "Euro" && cambio == "Euro") || (moneda == "Dolar" && cambio == "Dolar")
            || (moneda == "Yen" && cambio == "Yen") || (moneda == "PesoCol" && cambio == "PesoCol")
        ) {
            errorConver.value = R.string.error.toString()
        } else {

            if (moneda == "Euro" && cambio == "Dolar") {
                resultado = cantidad * 1.05
            } else if (moneda == "Dolar" && cambio == "Euro") {
                resultado = cantidad * 0.85
            } else if (moneda == "Dolar" && cambio == "Yen") {
                resultado = cantidad * 134.24
            } else if (moneda == "Dolar" && cambio == "PesoCol") {
                resultado = cantidad * 3.879

            } else if (moneda == "Euro" && cambio == "Yen") {
                resultado = cantidad * 141.14
            } else if (moneda == "Euro" && cambio == "PesoCol") {
                resultado = cantidad * 4.077
            } else if (moneda == "Euro" && cambio == "Dolar") {
                resultado = cantidad * 1.05

            } else if (moneda == "Yen" && cambio == "Dolar") {
                resultado = cantidad * 0.0074
            } else if (moneda == "Yen" && cambio == "Euro") {
                resultado = cantidad * 0.0071
            } else if (moneda == "Yen" && cambio == "PesoCol") {
                resultado = cantidad * 28.86

            } else if (moneda == "PesoCol" && cambio == "Euro") {
                resultado = cantidad * 0.00024
            } else if (moneda == "PesoCol" && cambio == "Dolar") {
                resultado = cantidad * 0.00026
            } else if (moneda == "PesoCol" && cambio == "Yen") {
                resultado = cantidad * 0.035
            }
        }
        Resultado.value = resultado.toString()
    }
}



