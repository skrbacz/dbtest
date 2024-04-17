package com.example.dbtest.db1.v

interface VaccDAO {
    fun getVaccination(vaccinationId: Int): Vaccination?
    fun getAllVaccinations(): Set<Vaccination?>?
    fun insertVaccination(vaccination: Vaccination): Boolean
    fun updateVaccination(vaccinationId: Int, vaccination: Vaccination):Boolean
    fun deleteVaccination(vaccinationId: Int): Boolean
}