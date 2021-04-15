package mx.itesm.tacos_de_tinga_19.veintiundias

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.ActivityMainBinding

// Autor: Bruno Hae sal Vázquez Hwang

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        configurarMenu()
        configurarFragmentoInicio()
    }

    private fun configurarFragmentoInicio(){
        val fragPerfil = PerfilFrag()
        cambiarFragmento(fragPerfil)
    }

    private fun configurarMenu() {
        binding.menuNavegacion.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navPerfil -> {
                    println("Perfil")
                    val fragPerfil = PerfilFrag()
                    cambiarFragmento(fragPerfil)
                }
                R.id.navHabitos -> {
                    println("Habitos")
                    val fragHabitos = HabitosFrag()
                    cambiarFragmento(fragHabitos)
                }
                R.id.navCalendario -> {
                    println("Calendario")
                    val fragCalendario = CalendarioFrag()
                    cambiarFragmento(fragCalendario)
                }
                R.id.navMusica -> {
                    println("Musica")
                    val fragMusica = MusicaFrag()
                    cambiarFragmento(fragMusica)
                }
            }

            true
        }
    }

    private fun cambiarFragmento(fragmento: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(binding.contenedorFragmentos.id, fragmento)
            .addToBackStack(null)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
            .commit()
    }

}