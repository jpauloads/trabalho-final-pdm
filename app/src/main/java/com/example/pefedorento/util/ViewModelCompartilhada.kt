package com.example.pefedorento.util

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.tasks.Tasks
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewModelCompartilhada() : ViewModel() {

    //================ CRUD CLIENTE ================
    //salvar cliente com CPF unico
    fun salvarCliente(cliente: Cliente, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val firestore = Firebase.firestore
        val collectionRef = firestore.collection("cliente")
        val query = collectionRef.whereEqualTo("cpf", cliente.cpf)

        try {
            val task = query.get()
            val querySnapshot = Tasks.await(task)

            if (querySnapshot.isEmpty) {
                collectionRef.document(cliente.cpf)
                    .set(cliente)
                    .addOnSuccessListener {
                        (context as Activity).runOnUiThread {
                            Toast.makeText(context, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { exception ->
                        (context as Activity).runOnUiThread {
                            Toast.makeText(context, "Falha ao salvar o cliente: ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                (context as Activity).runOnUiThread {
                    Toast.makeText(context, "Já existe um cliente com o CPF informado!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            (context as Activity).runOnUiThread {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

//    fun salvarCliente(
//        cliente: Cliente,
//        context: Context
//    ) = CoroutineScope(Dispatchers.IO).launch {
//        val firestoreRef = Firebase.firestore.collection("cliente").document(cliente.cpf)
//
//        try {
//
//            firestoreRef.set(cliente).addOnSuccessListener {
//                Toast.makeText(context, "Cliente salvo com sucesso!", Toast.LENGTH_SHORT).show()
//            }
//        } catch (e: Exception) {
//            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
//        }
//    }

    fun recuperarCliente(
        cpf: String,
        context: Context,
        cliente: (Cliente) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("cliente").document(cpf)
        try {
            firestoreRef.get().addOnSuccessListener {
                if (it.exists()) {
                    val dadosCliente = it.toObject<Cliente>()!!
                    cliente(dadosCliente)
                } else {
                    Toast.makeText(context, "Cliente não encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deletarCliente(
        cpf: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val firestoreRef = Firebase.firestore.collection("cliente").document(cpf)

        try {
            firestoreRef.delete().addOnSuccessListener {
                Toast.makeText(context, "Deletado com sucesso", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    //=================== CRUD PRODUTO =====================
    //salvar produto com id unico
    fun salvarProduto(produto: Produto, context: Context) = CoroutineScope(Dispatchers.IO).launch {
        val firestore = Firebase.firestore
        val collectionRef = firestore.collection("produto")
        val query = collectionRef.whereEqualTo("id", produto.id)

        try {
            val task = query.get()
            val querySnapshot = Tasks.await(task)

            if (querySnapshot.isEmpty) {
                collectionRef.document(produto.id)
                    .set(produto)
                    .addOnSuccessListener {
                        (context as Activity).runOnUiThread {
                            Toast.makeText(context, "Produto salvo com sucesso!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { exception ->
                        (context as Activity).runOnUiThread {
                            Toast.makeText(context, "Falha ao salvar o produto: ${exception.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                (context as Activity).runOnUiThread {
                    Toast.makeText(context, "Já existe um produto com esse Id!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            (context as Activity).runOnUiThread {
                Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun recuperarProduto(
        id: String,
        context: Context,
        produto: (Produto) -> Unit = {}
    ) = CoroutineScope(Dispatchers.IO).launch {
        val firestoreRef = Firebase.firestore.collection("produto").document(id)
        try {
            firestoreRef.get().addOnSuccessListener {
                if (it.exists()) {
                    val dadosProduto = it.toObject<Produto>()!!
                    produto(dadosProduto)
                } else {
                    Toast.makeText(context, "Produto não encontrado!", Toast.LENGTH_SHORT).show()
                }
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }

    fun deletarProduto(
        id: String,
        context: Context,
        navController: NavController,
    ) = CoroutineScope(Dispatchers.IO).launch {

        val firestoreRef = Firebase.firestore.collection("produto").document(id)

        try {
            firestoreRef.delete().addOnSuccessListener {
                Toast.makeText(context, "Deletado com sucesso", Toast.LENGTH_SHORT).show()
                navController.popBackStack()
            }
        } catch (e: Exception) {
            Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
        }
    }


    //============= CRUD PEDIDO ===============

}