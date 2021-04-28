package mx.itesm.tacos_de_tinga_19.veintiundias

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentCalendarioBinding
import java.text.SimpleDateFormat
import java.util.*


// Autor: Bruno Hae sal Vázquez Hwang

class CalendarioFrag : Fragment() {

    private var _binding: FragmentCalendarioBinding? = null
    private lateinit var compactCalendar: CompactCalendarView
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
    private lateinit var Auth: FirebaseAuth
    private lateinit var firebase: FirebaseDatabase
    private val formato = SimpleDateFormat("dd-M-yyyy")
    private lateinit var arrFechas: MutableList<Fecha>
    private lateinit var strNota: String
    private var flagEventos = true
    private var flagNota = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        Auth = FirebaseAuth.getInstance()
        firebase = FirebaseDatabase.getInstance()
        arrFechas = mutableListOf()

        _binding!!.ibtnEscribir.setOnClickListener{
            // Escribe a BD lo que sientes
            flagNota = false
            grabarNota()
            _binding?.tieSentimientos?.setText("")
            val inputManager: InputMethodManager = view
                    .context
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

            val binder = view.windowToken
            inputManager.hideSoftInputFromWindow(binder,
                    InputMethodManager.HIDE_NOT_ALWAYS)
        }
        _binding!!.ibtnTriste.setOnClickListener{
            // Escirbe a la BD
            grabarEmocion(1)
            val text = "Hoy te sientes triste"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(view.context, text, duration)
            toast.show()

        }
        _binding!!.ibtnAsco.setOnClickListener {
            // Escirbe en la BD
            grabarEmocion(2)
            val text = "Hoy te sientes con asco"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(view.context, text, duration)
            toast.show()
        }
        _binding!!.ibtnAlegre.setOnClickListener {
            // Escirbe en la BD
            grabarEmocion(3)
            val text = "Hoy te sientes alegre"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(view.context, text, duration)
            toast.show()
        }
        _binding!!.ibtnSorpresa.setOnClickListener {
            // Escirbe en la BD
            grabarEmocion(4)
            val text = "Hoy te sientes con sorpresa"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(view.context, text, duration)
            toast.show()
        }
        _binding!!.ibtnEnfado.setOnClickListener {
            // Escirbe en la BD
            grabarEmocion(5)
            val text = "Hoy te sientes con enfado"
            val duration = Toast.LENGTH_SHORT
            val toast = Toast.makeText(view.context, text, duration)
            toast.show()
        }
        iniciarCalendario()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalendarioBinding.bind(view)
        _binding = binding
    }

    fun grabarEmocion(emocion: Int){
        val currentDate = formato.format(Date())
        val currentDateEpoch = formato.parse(formato.format(Date())).time
        val referencia = firebase.getReference("/Usuarios/${Auth.currentUser.uid}/Fecha/$currentDate")
        val fecha: Fecha
        when (emocion){
            1 -> fecha = Fecha("Tristeza", currentDateEpoch)
            2 -> fecha = Fecha("Asco", currentDateEpoch)
            3 -> fecha = Fecha("Alegría", currentDateEpoch)
            4 -> fecha = Fecha("Sorpresa", currentDateEpoch)
            5 -> fecha = Fecha("Enfado", currentDateEpoch)
            else -> fecha = Fecha("Alegría", currentDateEpoch)
        }

        referencia.setValue(fecha)
    }

    fun grabarNota(){
        val currentDate = formato.format(Date())
        val nota = Nota(_binding!!.tieSentimientos.text.toString())
        val referencia = firebase.getReference("/Usuarios/${Auth.currentUser.uid}/Nota/$currentDate")
        referencia.setValue(nota)
    }

    fun leerEventos(callback: FirebaseCallbackList){
        val referencia = firebase.getReference("/Usuarios/${Auth.currentUser.uid}/Fecha/")
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                println("El valor de flag $flagEventos")
                for (registro in snapshot.children) {
                    val resultado = registro.getValue(Fecha::class.java)
                    if (resultado != null) {
                        arrFechas.add(resultado)
                    }
                }
                if (flagEventos) {
                    callback.onCallBackListFecha(arrFechas)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al descargar los datos")
            }
        })
    }

    fun añadirEventos() {
        for(registros in arrFechas){
            var emocion = registros.emocion
            var epoch = registros.currentDateEpoch
            val ev: Event
            when (emocion) {
                "Tristeza" -> ev = Event(Color.parseColor("#2196F3"), epoch)
                "Asco" -> ev = Event(Color.parseColor("#ff99cc00"), epoch)
                "Alegría" -> ev = Event(Color.parseColor("#ffffbb33"), epoch)
                "Sorpresa" -> ev = Event(Color.parseColor("#ffff8800"), epoch)
                "Enfado" -> ev = Event(Color.parseColor("#ffcc0000"), epoch)
                else -> ev = Event(Color.parseColor("#ffffbb33"), epoch)
            }
            compactCalendar.addEvent(ev)
        }
    }
    fun iniciarCalendario(){
        compactCalendar = _binding!!.compactcalendarView
        compactCalendar.setUseThreeLetterAbbreviation(true)
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY)
        _binding?.tVMA?.text = dateFormat.format(Calendar.getInstance().time)

        // Agregar Eventos
        leerEventos(object : FirebaseCallbackList {
            override fun onCallBackListFecha(lista: MutableList<Fecha>) {
                arrFechas = lista
                añadirEventos()
                flagEventos = false
            }
        })

        // Listener
        compactCalendar.setListener(object : CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date?) {
                flagNota = true
                leerNota(object: FirebaseCallbackString{
                    override fun onCallBackStringNota(str: String) {
                        strNota = str
                        flagNota
                        if(flagNota) {
                            desplegarNota()
                        }
                    }
                }, dateClicked, flagNota)
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                _binding?.tVMA?.text = dateFormat.format(firstDayOfNewMonth)
            }

        })
    }

    fun desplegarNota(){
        val text = "Escirbiste: " + strNota
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(view?.context, text, duration)
        toast.show()
    }

    fun leerNota(callBack: FirebaseCallbackString, dateClicked: Date?, flag: Boolean){
        var arrNota: MutableList<String>
        arrNota = mutableListOf()
        val date = formato.format(dateClicked)
        val referencia = firebase.getReference("/Usuarios/${Auth.currentUser.uid}/Nota/$date")
        referencia.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                arrNota.clear()
                for(registro in snapshot.children){
                    arrNota.add(registro.value.toString())
                }
                if (arrNota.size == 0) {
                    val text = "No escirbiste/escirto nada en dicha fecha"
                    val duration = Toast.LENGTH_SHORT
                    val toast = Toast.makeText(view?.context, text, duration)
                    toast.show()
                } else {
                    callBack.onCallBackStringNota(arrNota.get(0))
                }

            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al descargar los datos")
            }

        })
    }

    interface FirebaseCallbackList{
        fun onCallBackListFecha(lista: MutableList<Fecha>)
    }

    interface FirebaseCallbackString{
        fun onCallBackStringNota(str: String)
    }


}