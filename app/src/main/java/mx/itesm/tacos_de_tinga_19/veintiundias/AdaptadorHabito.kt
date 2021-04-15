package mx.itesm.tacos_de_tinga_19.veintiundias

import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.renglon_habito.view.*

class AdaptadorHabito(var arrData: Array<Habito>) :
    RecyclerView.Adapter<AdaptadorHabito.ViewLine>()
{
    var listener: ClickListener? = null

    class ViewLine(val viewLineCard: View) :
        RecyclerView.ViewHolder(viewLineCard)
    {
        fun setCard(habito: Habito) {
            viewLineCard.tvHabitoTitle.text = habito.name
            viewLineCard.tvHabitoTiempo.text = habito.time
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewLine {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.renglon_habito, parent, false)

        return ViewLine(view)
    }

    override fun onBindViewHolder(holder: ViewLine, position: Int) {
        val card = arrData[position]
        holder.setCard(card)

        holder.viewLineCard.setOnClickListener {
            listener?.clicked(position)
            println("Hizo click sobre $position")
        }
    }

    override fun getItemCount(): Int {
        return arrData.size
    }

}