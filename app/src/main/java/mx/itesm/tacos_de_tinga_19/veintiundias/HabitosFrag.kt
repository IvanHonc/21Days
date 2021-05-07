package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_habitos.*
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentHabitosBinding


class HabitosFrag : Fragment() {


    private lateinit var arrHabitos: ArrayList<Habito>
    private var _binding: FragmentHabitosBinding? = null
    private lateinit var _controller: habitsController
    private lateinit var Auth: FirebaseAuth
    private lateinit var adapter : AdaptadorHabito

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private fun configureRecyclerView() {
        val admLayout = LinearLayoutManager(parentFragment?.context)
        _binding?.rvHabitos?.layoutManager = admLayout

        arrHabitos = arrayListOf()
        adapter = AdaptadorHabito(arrHabitos, Auth, _controller)


        _binding?.rvHabitos?.adapter = adapter

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHabitosBinding.bind(view)
        _binding = binding
        configureRecyclerView()
        _controller.setUpGetHabits(
            Auth.currentUser.uid,
            object : Callback {
                override fun onCallback(value: List<Habito>) {
                    addNewHabits(value);
                }

            }
        )
        addHabits.setOnClickListener {
            println("test click")
//            _controller.addHabit(Auth.currentUser.uid, Habito("test1", 10))

            var newfrag = addHabitosFrag()
            (activity as MainActivity).cambiarFragmento(newfrag);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Auth = FirebaseAuth.getInstance()
        _controller = habitsController()

        return inflater.inflate(R.layout.fragment_habitos, container, false)
        _binding = FragmentHabitosBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        return view
    }

    fun addNewHabits(value: List<Habito>){
        arrHabitos.clear()
        arrHabitos.addAll(value);
        adapter.notifyDataSetChanged();
    }

}