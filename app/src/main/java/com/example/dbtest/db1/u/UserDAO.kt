package com.example.dbtest.db1.u


interface UserDAO {
    fun getUser(userId: Int): User?
    fun getAllUsers(): Set<User?>?
    fun insertUser(user: User): Boolean
    fun updateUser(userId: Int, user: User): Boolean
    fun deleteUser(userId: Int): Boolean
}