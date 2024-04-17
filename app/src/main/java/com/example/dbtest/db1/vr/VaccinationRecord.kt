package com.example.dbtest.db1.vr

import java.sql.Date

data class VaccinationRecord (
    val vaccinationRecordsId: Int? = null,
    val userId: Int? = null,
    val vaccinationId: Int? = null,
    val dateAdministrated: Date? = null,
    val nextDoseDueDate: Date? = null
) {
    constructor() : this(0, 0, 0, Date(0), Date(0))
}