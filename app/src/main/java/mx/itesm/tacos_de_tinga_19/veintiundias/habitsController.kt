package mx.itesm.tacos_de_tinga_19.veintiundias

import android.util.Log
import androidx.fragment.app.Fragment
import com.google.firebase.database.*


class habitsController : Fragment() {

    fun addHabit(email: String, habit: Habito)
    {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference(email)

        myRef.child("habits").child(email).get().addOnSuccessListener {
            val genericTypeIndicator: GenericTypeIndicator<List<Habito>> =
                object : GenericTypeIndicator<List<Habito>>() {}
            var habits: List<Habito>
            if(!it.exists()){
                habits = arrayListOf()
            }else{
                habits = it.getValue(genericTypeIndicator)!!
            }
            habits = habits + habit
            myRef.child("habits").child(email).setValue(habits)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

    fun setUpGetHabits (email: String, callback: Callback)
    {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference(email)

        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var it = snapshot.child("habits").child(email)
                val genericTypeIndicator: GenericTypeIndicator<List<Habito>> =
                    object : GenericTypeIndicator<List<Habito>>() {}
                var habits: List<Habito>
                if (!it.exists()) {
                    habits = arrayListOf()
                } else {
                    habits = it.getValue(genericTypeIndicator)!!
                }
                callback.onCallback(habits);
            }

            override fun onCancelled(error: DatabaseError) {
                print("data base get info cancelled")
            }
        }

        myRef.addValueEventListener(postListener)
//
    }

    fun deleteHabits(email: String, name: String)
    {
        val database: FirebaseDatabase = FirebaseDatabase.getInstance()
        val myRef: DatabaseReference = database.getReference(email)

        myRef.child("habits").child(email).get().addOnSuccessListener {
            val genericTypeIndicator: GenericTypeIndicator<List<Habito>> =
                object : GenericTypeIndicator<List<Habito>>() {}
            var habits: List<Habito>
            if(!it.exists()){
                habits = arrayListOf()
            }else{
                habits = it.getValue(genericTypeIndicator)!!
            }
            habits = habits.filter { it.name != name }

            myRef.child("habits").child(email).setValue(habits)
        }.addOnFailureListener{
            Log.e("firebase", "Error getting data", it)
        }

    }

}