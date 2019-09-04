package com.example.androidavanzadoclase02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity(), UsersListFragment.OnUserSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        var userListFragment = UsersListFragment()

        fragmentTransaction.add(R.id.fragment_container, userListFragment)
        fragmentTransaction.commit()
    }

    override fun onUserSelected(userId: Int) {

        val userDetailFragment = UserDetailFragment.newInstance(userId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, userDetailFragment)
            .addToBackStack(null) //Agregamos el fragment al stack, así al volver, no sale de la app,
            //sino que vuelve a cargar el anterior, ejecuta su ciclo post onCreate, no ejecuta el onCreate
            //pero sí el resto del ciclo. Recibe un texto que identifica la transacción, pero no lo vamos a usar
            .commit()
    }
}
