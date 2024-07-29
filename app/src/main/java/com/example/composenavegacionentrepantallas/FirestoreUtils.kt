import com.google.firebase.firestore.FirebaseFirestore
import android.content.Context
import android.widget.Toast

fun savePersonalData(personal: List<Pair<String, Int>>, context: Context) {
    val db = FirebaseFirestore.getInstance()
    val personalMap = personal.map { it.first to it.second }.toMap()
    db.collection("personal").document("data")
        .set(personalMap)
        .addOnSuccessListener {
            Toast.makeText(context, "Datos guardados", Toast.LENGTH_SHORT).show()
        }
        .addOnFailureListener { e ->
            Toast.makeText(context, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
}

fun loadPersonalData(callback: (List<Pair<String, Int>>) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    db.collection("personal").document("data")
        .get()
        .addOnSuccessListener { document ->
            if (document != null) {
                val personalMap = document.data as Map<String, Int>
                val personalList = personalMap.map { Pair(it.key, it.value) }
                callback(personalList)
            }
        }
        .addOnFailureListener { e ->
            // Handle the error
        }
}
