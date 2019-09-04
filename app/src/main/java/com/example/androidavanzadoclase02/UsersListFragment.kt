package com.example.androidavanzadoclase02

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.androidavanzadoclase02.models.User
import com.example.androidavanzadoclase02.utils.UsersFactory
import kotlinx.android.synthetic.main.fragment_users_list.*
import java.lang.ClassCastException

class UsersListFragment : Fragment() {
    private lateinit var users: List<User>
    private lateinit var adapter: UsersAdapter
    private lateinit var listener: OnUserSelectedListener

    //Para que las activities implementen el método
    interface OnUserSelectedListener{
        fun onUserSelected(userId: Int)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = UsersFactory.getInstance()
        users = factory.getAllUsers()
        adapter = UsersAdapter(users)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lv_users_list.adapter = adapter
        lv_users_list.setOnItemClickListener { _, _, _, id ->
            listener.onUserSelected(id.toInt())
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        //Acá guardamos la activity que llama a este fragment, para poder usarla después y saber
        //en que fragment hay que hacer cambios, sino no sabemos, basicamente guardamos el
        //contexto de la instancia que se está cargando
        try {
            listener = context as OnUserSelectedListener
        }catch (ex: ClassCastException){
            throw ClassCastException("Tenés que implementar la interfaz OnUserSelectedListener desde la activity")
        }
    }
}