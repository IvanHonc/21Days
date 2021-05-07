package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_create_habitos.*
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentCreateHabitosBinding

class addHabitosFrag: Fragment() {


    private lateinit var _controller: habitsController
    private lateinit var Auth: FirebaseAuth
    private var _binding: FragmentCreateHabitosBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentCreateHabitosBinding.bind(view)
        _binding = binding
        addHabit.setOnClickListener {
            var text:String = nameOfHabit.text.toString()
            _controller.addHabit(Auth.currentUser.uid , Habito(text, 21))
            var newfrag = HabitosFrag()
            (activity as MainActivity).cambiarFragmento(newfrag);
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Auth = FirebaseAuth.getInstance()
        _controller = habitsController()

        _binding = FragmentCreateHabitosBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        return view
    }

}