package com.example.dbtest.db1.v

data class Vaccination(val vaccinationId: Int? = null,
                       val daysTillNextDose: Int? = null,
                       val name: String? = null,
                       val shortDescription: String? = null
) {
    constructor() : this(0, 0, "", "")
}
