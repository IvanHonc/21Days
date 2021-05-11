package mx.itesm.tacos_de_tinga_19.veintiundias

import java.time.DateTimeException
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.util.*
import kotlin.collections.ArrayList

data class Habito(val name: String, val days: Int, val startDate: Date){
    constructor() : this("", 0, Calendar.getInstance().time)
    //test functionality
//    companion object{
//        fun createTestHabitoList(num: Int) : ArrayList<Habito> {
//            val habitos = ArrayList<Habito>()
//            for(i in 1..num){
//                habitos.add(Habito("Habito_"+i, i*2))
//            }
//            return habitos;
//        }
//    }

}