package mx.itesm.tacos_de_tinga_19.veintiundias

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.database.*
import com.google.firebase.database.ktx.getValue

class habitsController : Fragment() {

    fun addHabit(email: String, habit: habitModel)
    {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference(email)

        myRef.child("habits").child(email).get().addOnSuccessListener {
            var habits: MutableList<habitModel>
            if(!it.exists()){
                habits = mutableListOf()
            }else{
                habits = it.value as MutableList<habitModel>
            }
            habits.add(habit)
            myRef.child("habits").child(email).setValue(habits)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    fun setUpGetHabits (email: String, callback: Callback)
    {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference(email)

        myRef.child("habits").child(email).get().addOnSuccessListener {
            var habits: MutableList<habitModel>
            if(!it.exists()){
                habits = mutableListOf()
            }else{
                habits = it.value as MutableList<habitModel>
            }
            callback.onCallback(habits);
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }
    }

    fun deleteHabits(email: String, name: String)
    {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference(email)

        myRef.child("habits").child(email).get().addOnSuccessListener {
            var habits: MutableList<habitModel>
            if(!it.exists()){
                habits = mutableListOf()
            }else{
                habits = it.value as MutableList<habitModel>
            }
            habits.filter { it.name != name }

            myRef.child("habits").child(email).setValue(habits)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

}