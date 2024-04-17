package com.example.dbtest.db1.vr

interface VaccRecDAO {
    fun getVaccinationRecord(vaccinationRecordId: Int): VaccinationRecord?
    fun getAllVaccinationRecords(): Set<VaccinationRecord?>?
    fun insertVaccinationRecord(vaccinationRecords: VaccinationRecord): Boolean
    fun updateVaccinationRecord(vaccinationRecordId: Int, vaccinationRecords: VaccinationRecord): Boolean
    fun deleteVaccinationRecord(vaccinationRecordId: Int): Boolean
}