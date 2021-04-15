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
import kotlinx.android.synthetic.main.fragment_perfil.*
import java.util.ArrayList

class PerfilFrag : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setBarChart()
        setPieChart()
    }
    fun setBarChart(){
        val xvalue = ArrayList<String>()
        xvalue.add("text1")
        xvalue.add("text2")
        xvalue.add("text3")
        xvalue.add("text4")
        val yax = ArrayList<Float>()
        yax.add(4.5f)
        yax.add(6.5f)
        yax.add(8.5f)
        yax.add(9.5f)
        val barentries = ArrayList<BarEntry>()
        for ((i, item) in yax.withIndex()){
            barentries.add(BarEntry(yax[i], i))
        }
        val bardataset = BarDataSet(barentries, "texts1")
        bardataset.color = resources.getColor(R.color.black)
        val datacon = ArrayList<BarDataSet>()
        datacon.add(bardataset)
        val data =BarData(xvalue, datacon as List<IBarDataSet>?)
        barChart.data= data
        barChart.setBackgroundColor(resources.getColor(R.color.white))
        bardataset.setColors(ColorTemplate.PASTEL_COLORS)
        barChart.animateXY(2000, 2000)

    }
    fun setPieChart() {
        val xvalue = ArrayList<String>()
        xvalue.add("text")
        xvalue.add("text")
        xvalue.add("text")
        xvalue.add("text")
        xvalue.add("text")

        val yvalues = ArrayList<Float>()
        yvalues.add(15.5f)
        yvalues.add(25.5f)
        yvalues.add(45.5f)
        yvalues.add(20.5f)
        yvalues.add(39.5f)

        val piechartentry = ArrayList<Entry>()
        for ((i, item) in yvalues.withIndex()){
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
        piedataset.sliceSpace = 3f
        val data = PieData(xvalue, piedataset)
        piechart.data = data
        piechart.holeRadius = 5f
        piechart.setBackgroundColor(resources.getColor(R.color.white))
        piechart.setDescription("days")
        piechart.animateY(2000)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_perfil, container, false)
    }
}