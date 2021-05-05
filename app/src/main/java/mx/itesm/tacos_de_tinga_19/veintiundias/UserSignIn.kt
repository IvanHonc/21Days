package mx.itesm.tacos_de_tinga_19.veintiundias

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth

class UserSignIn : AppCompatActivity() {
    private val RC_SIGN_IN: Int = 200
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_sign_in)
        mAuth = FirebaseAuth.getInstance()
    }

    fun authenticateOnClick(v: View) {
        authenticate()
    }

    fun signOutOnClick(v: View) {
        signOut()
    }

    fun authenticate() {
        val providers = arrayListOf(
            AuthUI.IdpConfig.GoogleBuilder().build()
        )

        startActivityForResult(
            AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build(),
            RC_SIGN_IN)
    }

    fun signOut() {
        AuthUI.getInstance().signOut(this)
    }

    override fun onStart() {
        super.onStart()
        val user = mAuth.currentUser
        if(user != null) {
            println("Bienvenido: ${user.displayName}")
            println("Correo: ${user.email}")
            println("Token: ${user.uid}")
            val intUserData = Intent(baseContext, MainActivity::class.java)
            intUserData.putExtra("name", user.displayName.toString())
            intUserData.putExtra("email", user.email.toString())
            startActivity(intUserData)
        } else {
            println("you must log in")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == RC_SIGN_IN) {
            when(resultCode) {
                RESULT_OK -> {
                    val user = FirebaseAuth.getInstance().currentUser
                    println("Bienvenido: ${user.displayName}")
                    println("Correo: ${user.email}")
                    println("Token: ${user.uid}")

                    val intUserData = Intent(baseContext, MainActivity::class.java)
                    intUserData.putExtra("name", user.displayName)
                    intUserData.putExtra("email", user.email)
                    startActivity(intUserData)
                } RESULT_CANCELED -> {
                println("Canceled (back)")
                } else -> {
                    val response = IdpResponse.fromResultIntent(data)
                    println("Error: ${response?.error?.errorCode}")
                }
            }
        }
    }
}