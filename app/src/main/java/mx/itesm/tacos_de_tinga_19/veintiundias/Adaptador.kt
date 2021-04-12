package mx.itesm.tacos_de_tinga_19.veintiundias

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

//autor: viviana osorio nieto
// administra la informacion del recycler view

class Adaptador (private val arrDatos : Array<Video>):
    RecyclerView.Adapter<Adaptador.VistaRenglon>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaRenglon {
       val vista = LayoutInflater.from(parent.context)
           .inflate(R.layout.renglonvideo, parent, false)
        return VistaRenglon(vista)
    }

    override fun onBindViewHolder(holder: VistaRenglon, position: Int) {
        val video = arrDatos[position]
        holder.set(video)
    }

    override fun getItemCount(): Int {
        return arrDatos.size
    }

    class VistaRenglon(val vistaRenglonVideo: View):
        RecyclerView.ViewHolder(vistaRenglonVideo) {
        fun set(video: Video){
            //add tvInfo
           // vistaRenglonVideo.tvInfo.text = video.info
        }
    }


}