package com.alexapps.conversormoneda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alexapps.conversormoneda.databinding.ActivityMainBinding
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding // definimos una variable privada de solo la actividad principal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBinding = ActivityMainBinding.inflate(layoutInflater) //iniciamos la variable y me da acceso s los datos en la actividad
        val view = mainBinding.root //la variable view tomara lo que hay en vista de actividad
        setContentView(view)


        // cuando se toca el boton convertir
        mainBinding.buttonConvertir.setOnClickListener {
            var moneda = mainBinding.spinnerConversor.selectedItem.toString()
            var cambio = mainBinding.spinnerReceptor.selectedItem.toString()
            var cantida = mainBinding.ingresoEt.text.toString()

            if (cantida.isEmpty()){
                Toast.makeText(this, getString(R.string.sin_valor),Toast.LENGTH_LONG).show()
            }
            else {
                convertirMoneda(moneda, cambio, cantida)
            }
        }
    }

    private fun convertirMoneda(moneda: String, cambio: String, cantida: String) {
        var resultado: Double= 1.0
        var cantidad= cantida.toFloat()

        // condicional de conversion valida
        if ((moneda == "Euro" && cambio == "Euro") || (moneda == "Dolar" && cambio == "Dolar")
            ||(moneda == "Yen" && cambio == "Yen")||(moneda == "PesoCol" && cambio == "PesoCol")){
            mainBinding.ReceptorLayout.error= getString(R.string.error)
            mainBinding.ReceptorEt.text = null
        }
        else {
            mainBinding.ReceptorLayout.error= null
            if (moneda == "Euro" && cambio == "Dolar") { resultado = cantidad * 1.05
            } else if (moneda == "Dolar" && cambio == "Euro") { resultado = cantidad * 0.85
            } else if (moneda == "Dolar" && cambio == "Yen") { resultado = cantidad * 134.24
            } else if (moneda == "Dolar" && cambio == "PesoCol") { resultado = cantidad * 3.879

            } else if (moneda == "Euro" && cambio == "Yen") { resultado = cantidad * 141.14
            } else if (moneda == "Euro" && cambio == "PesoCol") { resultado = cantidad * 4.077
            } else if (moneda == "Euro" && cambio == "Dolar") { resultado = cantidad * 1.05

            } else if (moneda == "Yen" && cambio == "Dolar") { resultado = cantidad * 0.0074
            } else if (moneda == "Yen" && cambio == "Euro") { resultado = cantidad * 0.0071
            } else if (moneda == "Yen" && cambio == "PesoCol") { resultado = cantidad * 28.86

            } else if (moneda == "PesoCol" && cambio == "Euro") { resultado = cantidad * 0.00024
            } else if (moneda == "PesoCol" && cambio == "Dolar") { resultado = cantidad * 0.00026
            } else if (moneda == "PesoCol" && cambio == "Yen") { resultado = cantidad * 0.035
            }
        }
        mainBinding.ReceptorEt.setText(resultado.toString())// envio el resultado de la conversion
    }
}