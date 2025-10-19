package dev.wticona.a23100198practicacalificada01.firebase

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class FirestoreManager {
    private val db = FirebaseFirestore.getInstance()

    // Function to get rates from Firestore
    suspend fun getRates(): Map<String, Double> {
        val rates = mutableMapOf<String, Double>()
        try {
            val snapshot = db.collection("rates").get().await()
            for (document in snapshot.documents) {
                document.data?.forEach { (key, value) ->
                    if (value is Number) {
                        rates[key] = value.toDouble()
                    }
                }
            }
        } catch (e: Exception) {
            // Handle exception
        }
        return rates
    }
}