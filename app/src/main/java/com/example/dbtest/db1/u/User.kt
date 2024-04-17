package com.example.dbtest.db1.u

data class User(
//    val userId: Int? = null,
    val name: String? = null,
    val email: String? = null,
    val password: String? = null
) {
    constructor() : this("", "", "")
}
