package mx.itesm.tacos_de_tinga_19.veintiundias

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.utils.ColorTemplate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_perfil.*
import java.lang.NullPointerException
import java.util.ArrayList

class PerfilFrag : Fragment() {
    private lateinit var Auth: FirebaseAuth
    private lateinit var firebase: FirebaseDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBarChart()
        setPieChart()
    }



    fun setBarChart(){
        var count1 = 0
        var count2 = 0
        var count3 = 0
        var count4 = 0
        var count5 = 0
        var countDias = 0
        val referencia = firebase.getReference("/Usuarios/${Auth.currentUser.uid}/Fecha/")
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (registro in snapshot.children) {
                    val emocion = registro.getValue(Fecha::class.java)!!.emocion
                    println(emocion)
                    countDias ++
                    when (emocion) {
                        "Tristeza" -> count1++
                        "Cansancio" -> count2++
                        "Alegría" -> count3++
                        "Inspiración" -> count4++
                        "Enfado" -> count5++
                        else -> count1++
                    }
                    val xvalue = ArrayList<String>()
                    xvalue.add("Tristeza")
                    xvalue.add("Cansancio")
                    xvalue.add("Alegría")
                    xvalue.add("Inspiración")
                    xvalue.add("Enfado")
                    val yax = ArrayList<Float>()
                    yax.add(count1+0.0f)
                    yax.add(count2+0.0f)
                    yax.add(count3+0.0f)
                    yax.add(count4+0.0f)
                    yax.add(count5+0.0f)
                    val barentries = ArrayList<BarEntry>()
                    for ((i, item) in yax.withIndex()){
                        barentries.add(BarEntry(yax[i], i))
                    }
                    val bardataset = BarDataSet(barentries, "texts1")
                    bardataset.color = resources.getColor(R.color.black)
                    val datacon = ArrayList<BarDataSet>()
                    datacon.add(bardataset)
                    try {
                        val data =BarData(xvalue, datacon as List<IBarDataSet>?)
                        barChart.data= data
                        barChart.setBackgroundColor(resources.getColor(R.color.white))
                        bardataset.setColors(ColorTemplate.COLORFUL_COLORS)
                        barChart.animateXY(2000, 2000)
                    }catch (e: NullPointerException){
                        println("error: barchart null")
                    }
                }
                try {
                    tvDaysUser.text = countDias.toString() + " días"
                }catch (e: NullPointerException){
                    println("error: barchart null")
                }
            }
            override fun onCancelled(error: DatabaseError) {
                println("Error al descargar los datos")
            }
        })
    }

    fun setPieChart() {
        var count1 = 0
        var count2 = 0
        var count3 = 0
        var count4 = 0
        var count5 = 0
        val referencia = firebase.getReference("/Usuarios/${Auth.currentUser.uid}/Fecha/")
        referencia.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (registro in snapshot.children) {
                    val emocion = registro.getValue(Fecha::class.java)!!.emocion
                    println(emocion)
                    when (emocion) {
                        "Tristeza" -> count1++
                        "Cansancio" -> count2++
                        "Alegría" -> count3++
                        "Inspiración" -> count4++
                        "Enfado" -> count5++
                        else -> count1++
                    }
                    val xvalue = ArrayList<String>()
                    xvalue.add("Tristeza")
                    xvalue.add("Cansancio")
                    xvalue.add("Alegría")
                    xvalue.add("Inspiración")
                    xvalue.add("Enfado")
                    val yax = ArrayList<Float>()
                    yax.add(count1 + 0.0f)
                    yax.add(count2 + 0.0f)
                    yax.add(count3 + 0.0f)
                    yax.add(count4 + 0.0f)
                    yax.add(count5 + 0.0f)
                    val piechartentry = ArrayList<Entry>()
                    for ((i, item) in yax.withIndex()) {
                        piechartentry.add(Entry(item, i))
                    }
                    val colors = ArrayList<Int>()
                    colors.add(Color.GRAY)
                    colors.add(Color.LTGRAY)
                    colors.add(Color.DKGRAY)
                    colors.add(Color.YELLOW)
                    colors.add(Color.MAGENTA)
                    val piedataset = PieDataSet(piechartentry, "days")
                    piedataset.colors = colors
                    piedataset.setColors(ColorTemplate.COLORFUL_COLORS)
                    piedataset.sliceSpace = 3f
                    val data = PieData(xvalue, piedataset)
                    try {

                        piechart.data = data
                        piechart.holeRadius = 5f
                        piechart.setBackgroundColor(resources.getColor(R.color.white))
                        piechart.setDescription("days")
                        piechart.animateY(2000)
                    }catch(e: NullPointerException){
                        println("error: barchart null")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                println("Error al descargar los datos")
            }
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        Auth = FirebaseAuth.getInstance()
        firebase = FirebaseDatabase.getInstance()
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }
}