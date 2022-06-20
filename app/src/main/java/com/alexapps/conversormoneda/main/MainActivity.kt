package com.alexapps.conversormoneda.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexapps.conversormoneda.databinding.ActivityMainBinding
import android.widget.Toast
import com.alexapps.conversormoneda.R

import androidx.lifecycle.ViewModelProvider


class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding // definimos una variable privada de solo la actividad principal

    private lateinit var viewModel: MainViewModel //creo Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //paso 2
        mainBinding = ActivityMainBinding.inflate(layoutInflater) //iniciamos la variable y me da acceso s los datos en la actividad
        val view = mainBinding.root //la variable view tomara lo que hay en vista de actividad
        setContentView(view)

        //paso 3, ViewModel

        //Parte del view mode
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        // vinculo a la actividad Viewmode observadores de os live dat

        viewModel.errorDataDone.observe(this){ errorData ->
            mainBinding.ReceptorEt.setText(errorData)// envio el resultado de la conversion
            Toast.makeText(this,getString(R.string.sin_valor), Toast.LENGTH_LONG).show()
            mainBinding.ReceptorEt.text = null
        }

        viewModel.errorConverDone.observe(this){ errorConver ->
            mainBinding.ReceptorEt.setText(errorConver)// envio el resultado de la conversion
            Toast.makeText(this,getString(R.string.error), Toast.LENGTH_LONG).show()
            mainBinding.ReceptorEt.text = null
        }

        viewModel.resultadoDone.observe(this){ Resultado ->
            mainBinding.ReceptorLayout.error = null
            mainBinding.ReceptorEt.setText(Resultado)// envio el resultado de la conversion
        }

        // cuando se toca el boton convertir
        mainBinding.buttonConvertir.setOnClickListener {
            var moneda = mainBinding.spinnerConversor.selectedItem.toString()
            var cambio = mainBinding.spinnerReceptor.selectedItem.toString()
            var cantida = mainBinding.ingresoEt.text.toString()
            viewModel.convertir(moneda, cambio, cantida)
        }
    }
}