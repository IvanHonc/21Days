package mx.itesm.tacos_de_tinga_19.veintiundias

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentCalendarioBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Autor: Bruno Hae sal Vázquez Hwang

class CalendarioFrag : Fragment() {

    // val dataBase = StorageService()

    private lateinit var database: FirebaseDatabase
    private lateinit var arrDatos: MutableList<String>
    private var _binding: FragmentCalendarioBinding? = null
    private var flag = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        val view = _binding!!.root

        _binding?.ibtnEscribir?.setOnClickListener{
            escribirBD()
        }

        _binding?.cvCalendario?.setOnDateChangeListener{ view, year, month, dayOfMonth ->
            flag = true
            var mes = ""+(month+1)
            var dia = ""+dayOfMonth
            if(month<10){
                mes = "0"+mes
            }
            if(dayOfMonth<10){
                dia = "0"+dia
            }
            val fechaSeleccionada = "$year-$mes-$dia"
            leerBD(fechaSeleccionada)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalendarioBinding.bind(view)
        _binding = binding
        database = FirebaseDatabase.getInstance()
        arrDatos = mutableListOf()
    }

    fun escribirBD(){
        grabarEnBD()
    }

    fun leerBD(fecha: String){
        buscarFecha(fecha)
    }

    private fun grabarEnBD() {
        var texto = _binding?.tieSentimientos?.text.toString()
        val currentDateTime = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE).toString()
        } else {
            TODO("VERSION.SDK_INT < O")
        }
        if(texto==""){
            texto = "Hoy no sientes nada"
        }
        val fecha = mapOf<String, String>(
            "fecha" to  currentDateTime,
            "texto" to texto
        )
        val referencia = database.getReference("/Prototipo/$currentDateTime")
        referencia.setValue(fecha)
        _binding?.tieSentimientos?.setText("")
    }

    private fun buscarFecha(fecha: String) {
        println("Entro a buscarFecha")
        val referencia = database.getReference("/Prototipo/$fecha")
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                println("Entro aqui")
                if(!flag){
                    return
                }
                val builder: AlertDialog.Builder = AlertDialog.Builder(activity!!)
                builder.setTitle(fecha)
                arrDatos.clear()
                for (registro in snapshot.children){
                    arrDatos.add(registro.value.toString())
                }
                if(arrDatos.size==0){
                    builder.setMessage("No escribiste o escrito nada esa fecha")
                } else {
                    builder.setMessage("Esa fecha te sentiste: ${arrDatos.get(1)}")
                }
                builder.setPositiveButton("OK", { dialog, which ->
                    dialog.dismiss()
                })
                val alert = builder.create()
                alert.show()
                flag = false
            }

            override fun onCancelled(error: DatabaseError) {
                println("Ocurrio un error con la base de datos")
            }

        })
    }



}