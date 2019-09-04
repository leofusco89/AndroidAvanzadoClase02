package com.example.androidavanzadoclase02.utils

import com.example.androidavanzadoclase02.models.User

class UsersFactory private constructor(){

    companion object{
        private  var sInstance: UsersFactory? = null

        fun getInstance(): UsersFactory{
            //sInstance si no es null, llama al constuctor
            sInstance = sInstance ?: UsersFactory()

            return sInstance!!
        }
    }

    private var userList: MutableList<User>? = null

    init {
        userList = ArrayList()
        addUsers()
    }

    private fun addUsers(){
        val user1 = User(0, "Juan", "juan.com", "4143-1243")
        val user2 = User(1, "Martina", "martina.com", "4555-1555")
        val user3 = User(2, "José", "jose.com", "4666-1666")

        userList!!.add(user1)
        userList!!.add(user2)
        userList!!.add(user3)
    }

    fun getAllUsers(): List<User>{
        return userList!!
    }

    fun getUserById(id: Int): User{
        return userList!![id]
    }


}