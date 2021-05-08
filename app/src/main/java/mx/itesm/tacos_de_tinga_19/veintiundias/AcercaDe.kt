package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_acerca_de.*

class AcercaDe : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_acerca_de, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        escribirTexto()
    }

    private fun escribirTexto() {
        tVTextoGeneral.text = "¿Qué es nuestra aplicación?" +
                "\nNuestra aplicación es una ayuda para que se creen buenos habitos, estudios dicen que se " +
                "requieren 21 días para poder acostumbrarnos y crear un nuevo habito y consideramos" +
                " que con nuestra aplicación esta tarea puede facilitarse." +
                "\n\n¿Por qué el mapache?\n" +
                "El mapache es un animal que relaja y nos gusto mucho la idea de usarlo como" +
                " un elemento más de la aplicación."
    }

}