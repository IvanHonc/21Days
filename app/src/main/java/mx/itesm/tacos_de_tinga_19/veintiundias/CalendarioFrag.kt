package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

// Autor: Bruno Hae sal Vázquez Hwang

class CalendarioFrag : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configurarRV();
    }

    private fun configurarRV() {
        val layout = LinearLayoutManager(parentFragment?.context)
        //R.rvVideos.layoutManager = layout
        val arrVideos = crearArrVideos()
        val adaptador = Adaptador(arrVideos)
    }

    private fun crearArrVideos(): Array<Video> {
        return arrayOf(
            Video("Informacion de un video", "https://www.youtube.com/watch?v=iaXReFOr9YY&ab_channel=%EB%8D%95%ED%9B%84%EB%AE%A4%EC%A7%81MusicNerds") ,
            Video("Informacion de un video 2 ", "https://www.youtube.com/watch?v=iaXReFOr9YY&ab_channel=%EB%8D%95%ED%9B%84%EB%AE%A4%EC%A7%81MusicNerds"),
            Video("Informacion de un video 3", "https://www.youtube.com/watch?v=iaXReFOr9YY&ab_channel=%EB%8D%95%ED%9B%84%EB%AE%A4%EC%A7%81MusicNerds"),
            Video("Informacion de un video 4", "https://www.youtube.com/watch?v=iaXReFOr9YY&ab_channel=%EB%8D%95%ED%9B%84%EB%AE%A4%EC%A7%81MusicNerds")



        )
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendario, container, false)
    }
    

}