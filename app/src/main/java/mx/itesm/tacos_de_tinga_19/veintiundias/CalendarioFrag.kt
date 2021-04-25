package mx.itesm.tacos_de_tinga_19.veintiundias

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentCalendarioBinding
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*

// Autor: Bruno Hae sal Vázquez Hwang

class CalendarioFrag : Fragment() {


    private var _binding: FragmentCalendarioBinding? = null
    private lateinit var compactCalendar: CompactCalendarView
    private val dateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentCalendarioBinding.inflate(inflater, container, false)
        val view = _binding!!.root

        _binding?.ibtnEscribir?.setOnClickListener{
            // Escribe a BD lo que sientes
        }
        iniciarCalendario()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCalendarioBinding.bind(view)
        _binding = binding
    }

    fun iniciarCalendario(){
        compactCalendar = _binding!!.compactcalendarView
        compactCalendar.setUseThreeLetterAbbreviation(true)
        compactCalendar.setFirstDayOfWeek(Calendar.MONDAY)
        _binding?.tVMA?.text = dateFormat.format(Calendar.getInstance().time)
        var creation_time = 1619370004000L
        var date = Date(creation_time)
        val cal = Calendar.getInstance()
        cal.time = date
        var ano = cal.get(Calendar.YEAR)
        var mes = cal.get(Calendar.MONTH)
        var dia = cal.get(Calendar.DAY_OF_MONTH)
        println("$ano/${mes+1}/$dia")
        val ev1 = Event(Color.RED, creation_time)
        compactCalendar.addEvent(ev1)
        compactCalendar.setListener(object: CompactCalendarView.CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date?) {
                val sdf = SimpleDateFormat("dd-MM-yyyy")
                val l = sdf.parse(sdf.format(dateClicked))
                val epoch = dateClicked!!.time
                val ev = Event(Color.RED, epoch)
                compactCalendar.addEvent(ev)
                println("Esta es el stamp $l")
                println("El epoch $epoch")
                println("${sdf.format(dateClicked)}")
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date?) {
                _binding?.tVMA?.text = dateFormat.format(firstDayOfNewMonth)
            }

        })
    }





}