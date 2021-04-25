package mx.itesm.tacos_de_tinga_19.veintiundias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.firebase.ui.auth.AuthUI
import com.google.firebase.database.FirebaseDatabase
import mx.itesm.tacos_de_tinga_19.veintiundias.databinding.ActivityMainBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// Autor: Bruno Hae sal Vázquez Hwang

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance()
        configurarMenu()
        configurarFragmentoInicio()
    }

    fun signOutOnClick(v: View) {
        signOut()
    }

    private fun configurarFragmentoInicio(){
        val frag_ini_perf = InicPerfilFrag()
        cambiarFragmento(frag_ini_perf)
    }

    private fun configurarMenu() {
        binding.menuNavegacion.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.navPerfil -> {
                    println("Perfil")
                    val frag_ini_perf = InicPerfilFrag()
                    cambiarFragmento(frag_ini_perf)
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

    fun cambiarPerfilFrag(v: View){
        val fragPerfil = PerfilFrag()
        cambiarFragmento(fragPerfil)
    }

    fun signOut() {
        AuthUI.getInstance().signOut(this)
        val intMainActivity = Intent(baseContext, UserSignIn::class.java)
        startActivity(intMainActivity)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val intUserSignInActivity = Intent(baseContext, MainActivity::class.java)
        startActivity(intUserSignInActivity)
    }
}