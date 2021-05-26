package mx.itesm.tacos_de_tinga_19.veintiundias

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_inic_perfil.*
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentInicPerfilBinding
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.FragmentMusicaBinding

class InicPerfilFrag : Fragment() {
    private var _binding: FragmentInicPerfilBinding? = null
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentInicPerfilBinding.inflate(inflater, container, false)
        val view = _binding!!.root
        mAuth = FirebaseAuth.getInstance()
        _binding!!.tvUser.text = mAuth.currentUser.displayName
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentInicPerfilBinding.bind(view)
        _binding = binding
    }
}