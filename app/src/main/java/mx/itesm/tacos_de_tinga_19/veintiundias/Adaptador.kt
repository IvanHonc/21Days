package mx.itesm.tacos_de_tinga_19.veintiundias

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.renglonvideo.view.*

//autor: viviana osorio nieto
// administra la informacion del recycler view

class Adaptador(private val arrDatos: Array<Video>):
    RecyclerView.Adapter<Adaptador.VistaRenglon>() {

    var listener: ClickListener?  = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VistaRenglon {
       val vista = LayoutInflater.from(parent.context)
           .inflate(R.layout.renglonvideo, parent, false)
        return VistaRenglon(vista)
    }

    override fun onBindViewHolder(holder: VistaRenglon, position: Int) {
        val video = arrDatos[position]
        holder.set(video)
        holder.vistaRenglonVideo.setOnClickListener{
            listener?.clicked(position)
        }
    }

    override fun getItemCount(): Int {
        return arrDatos.size
    }

    class VistaRenglon(val vistaRenglonVideo: View):
        RecyclerView.ViewHolder(vistaRenglonVideo) {
        fun set(video: Video){
            //add tvInfo
            val uri: Uri = Uri.parse("https://www.youtube.com/watch?v=fImMNxHj2aU&ab_channel=codeandchill")

           vistaRenglonVideo.tvInfo.text = video.info
           vistaRenglonVideo.videoView2.setImageResource(video.idImagen)
        }
    }

}