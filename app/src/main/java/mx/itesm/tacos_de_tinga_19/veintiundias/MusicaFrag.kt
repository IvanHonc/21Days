package mx.itesm.tacos_de_tinga_19.veintiundias
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentMusicaBinding

//Autor: Viviana Osorio Nieto
class MusicaFrag: Fragment(), ClickListener {

    private var _binding: FragmentMusicaBinding? = null
    //private val binding get() = _binding!!

    // Arreglo de videos
    private lateinit var arrVideos: Array<Video>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private fun configurarRV() {

        val layout = LinearLayoutManager(parentFragment?.context)

        _binding?.rvVideos?.layoutManager = layout
        arrVideos = crearArrVideos()
        val adaptador = Adaptador(arrVideos)
        _binding?.rvVideos?.adapter = adaptador
        adaptador.listener = this
    }

    private fun crearArrVideos(): Array<Video> {
        return arrayOf(
                Video("Informacion del video",R.drawable.lofi1),
                Video("Mas informacion del video",R.drawable.lofi2),
                Video("Cosas escritas aqui",R.drawable.lofi1),
                Video("Otra informacion del video",R.drawable.lofi2))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentMusicaBinding.bind(view)
        _binding = binding
        configurarRV()
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMusicaBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        return view
    }

    override fun clicked(position: Int) {
        val video = arrVideos[position]
        val url = Uri.parse("https://www.youtube.com/watch?v=fImMNxHj2aU&ab_channel=codeandchill")
        val intBuscar = Intent(Intent.ACTION_VIEW, url) // busca applicaciones que pudean abrir url
        startActivity(intBuscar)
    }

}