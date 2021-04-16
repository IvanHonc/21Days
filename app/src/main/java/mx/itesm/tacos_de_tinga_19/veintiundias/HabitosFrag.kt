package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_habitos.*
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentHabitosBinding

class HabitosFrag : Fragment(), ClickListener {
    private lateinit var arrHabitos: Array<Habito>
    private var _binding: FragmentHabitosBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun configureRecyclerView() {
        val admLayout = LinearLayoutManager(parentFragment?.context)
        _binding?.rvHabitos?.layoutManager = admLayout

        arrHabitos = createArrHabitos()
        val adapter = AdaptadorHabito(arrHabitos)

        _binding?.rvHabitos?.adapter = adapter

        adapter.listener = this
    }

    private fun createArrHabitos(): Array<Habito> {
        return arrayOf(
            Habito("Llevas 45 días sin consumir alcohol!", "45 días"),
            Habito("Llevas 25 días haciendo ejercicio!", "25 días"),
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHabitosBinding.bind(view)
        _binding = binding
        configureRecyclerView()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_habitos, container, false)
        _binding = FragmentHabitosBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        return view
    }

    override fun clicked(posicion: Int) {
        TODO("Not yet implemented")
    }
}