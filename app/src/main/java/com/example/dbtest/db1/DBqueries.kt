package com.example.dbtest.db1


import com.example.dbtest.db1.u.User
import com.example.dbtest.db1.u.UserDAO
import com.example.dbtest.db1.v.VaccDAO
import com.example.dbtest.db1.v.Vaccination
import com.example.dbtest.db1.vr.VaccRecDAO
import com.example.dbtest.db1.vr.VaccinationRecord
import java.sql.Connection
import java.sql.ResultSet

class DBqueries(private val connection: Connection) : UserDAO, VaccDAO, VaccRecDAO {

        override fun getUser(userId: Int): User? {
            val query = "{CALL getUser(?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, userId)
            val resultSet = callableStatement.executeQuery()
            return if (resultSet.next()) {
                mapResultSetToUser(resultSet)
            } else {
                null
            }
        }

        override fun getAllUsers(): Set<User?>? {
            val query = "{CALL getUsers()}"
            val callableStatement = connection.prepareCall(query)
            val resultSet = callableStatement.executeQuery()
            val users = mutableSetOf<User?>()
            while (resultSet.next()) {
                users.add(mapResultSetToUser(resultSet))
            }
            return if (users.isEmpty()) null else users
        }

        override fun insertUser(user: User): Boolean {
            val call = "{CALL insertUser(?, ?,?)}"
            val statement = connection.prepareCall(call)
            statement.setString(1, user.name)
            statement.setString(2, user.email)
            statement.setString(3,user.password)
            val result = !statement.execute()
            statement.close()
            return result
        }

        override fun updateUser(userId: Int, user: User): Boolean {
            val query = "{CALL updateUser(?, ?, ?, ?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, userId)
            callableStatement.setString(2, user.name)
            callableStatement.setString(3, user.email)
            callableStatement.setString(4, user.password)
            return callableStatement.executeUpdate() > 0
        }

        override fun deleteUser(userId: Int): Boolean {
            val query = "{CALL deleteUser(?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, userId)
            return callableStatement.executeUpdate() > 0
        }

        private fun mapResultSetToUser(resultSet: ResultSet): User? {
            return User(
                name = resultSet.getString("name"),
                email = resultSet.getString("email"),
                password = resultSet.getString("password")
            )
        }

        override fun getVaccination(vaccinationId: Int): Vaccination? {
            val query = "{CALL getVaccination(?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, vaccinationId)
            val resultSet = callableStatement.executeQuery()
            return if (resultSet.next()) {
                mapResultSetToVaccination(resultSet)
            } else {
                null
            }
        }

        override fun getAllVaccinations(): Set<Vaccination?>? {
            val query = "{CALL getAllVaccinations()}"
            val callableStatement = connection.prepareCall(query)
            val resultSet = callableStatement.executeQuery()
            val vaccinations = mutableSetOf<Vaccination?>()
            while (resultSet.next()) {
                vaccinations.add(mapResultSetToVaccination(resultSet))
            }
            return if (vaccinations.isEmpty()) null else vaccinations
        }

        override fun insertVaccination(vaccination: Vaccination): Boolean {
            val call = "{CALL insertVaccination(?, ?, ?)}"
            val statement = connection.prepareCall(call)
            statement.setInt(1, vaccination.daysTillNextDose!!)
            statement.setString(2, vaccination.name)
            statement.setString(3, vaccination.shortDescription)
            val result = !statement.execute()
            statement.close()
            return result
        }

        override fun updateVaccination(vaccinationId: Int, vaccination: Vaccination): Boolean {
            val query = "{CALL updateVaccination(?, ?, ?, ?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, vaccinationId)
            callableStatement.setInt(2, vaccination.daysTillNextDose!!)
            callableStatement.setString(3, vaccination.name)
            callableStatement.setString(4, vaccination.shortDescription)
            return callableStatement.executeUpdate() > 0
        }

        override fun deleteVaccination(vaccinationId: Int): Boolean {
            val query = "{CALL deleteVaccination(?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, vaccinationId)
            return callableStatement.executeUpdate() > 0
        }

        private fun mapResultSetToVaccination(resultSet: ResultSet): Vaccination? {
            return Vaccination(
                daysTillNextDose = resultSet.getInt("daysTillNextDose"),
                name = resultSet.getString("name"),
                shortDescription = resultSet.getString("shortDescription")
            )
        }


        override fun getVaccinationRecord(vaccinationRecordId: Int): VaccinationRecord? {
            val query = "{CALL getVaccinationRecord(?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, vaccinationRecordId)
            val resultSet = callableStatement.executeQuery()
            return if (resultSet.next()) {
                mapResultSetToVaccinationRecord(resultSet)
            } else {
                null
            }
        }

        override fun getAllVaccinationRecords(): Set<VaccinationRecord?>? {
            val query = "{CALL getAllVaccinationRecords()}"
            val callableStatement = connection.prepareCall(query)
            val resultSet = callableStatement.executeQuery()
            val vaccinationRecords = mutableSetOf<VaccinationRecord?>()
            while (resultSet.next()) {
                vaccinationRecords.add(mapResultSetToVaccinationRecord(resultSet))
            }
            return if (vaccinationRecords.isEmpty()) null else vaccinationRecords
        }

        override fun insertVaccinationRecord(vaccinationRecords: VaccinationRecord): Boolean {
            val call = "{CALL insertVaccinationRecord(?, ?, ?)}"
            val statement = connection.prepareCall(call)
            statement.setInt(1, vaccinationRecords.userId!!)
            statement.setInt(2, vaccinationRecords.vaccinationId!!)
            statement.setDate(3, vaccinationRecords.dateAdministrated)
            val result = !statement.execute()
            statement.close()
            return result
        }

        override fun updateVaccinationRecord(vaccinationRecordId: Int, vaccinationRecords: VaccinationRecord): Boolean {
            val query = "{CALL updateVaccinationRecord(?, ?, ?, ?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, vaccinationRecordId)
            callableStatement.setInt(2, vaccinationRecords.userId!!)
            callableStatement.setInt(3, vaccinationRecords.vaccinationId!!)
            callableStatement.setDate(4, vaccinationRecords.dateAdministrated)
            return callableStatement.executeUpdate() > 0
        }

        override fun deleteVaccinationRecord(vaccinationRecordId: Int): Boolean {
            val query = "{CALL deleteVaccinationRecord(?)}"
            val callableStatement = connection.prepareCall(query)
            callableStatement.setInt(1, vaccinationRecordId)
            return callableStatement.executeUpdate() > 0
        }

        private fun mapResultSetToVaccinationRecord(resultSet: ResultSet): VaccinationRecord? {
            return VaccinationRecord(
                userId = resultSet.getInt("userId"),
                vaccinationId = resultSet.getInt("vaccinationId"),
                dateAdministrated = resultSet.getDate("dateAdministrated"),
                nextDoseDueDate = resultSet.getDate("nextDoseDueDate")
            )
        }
}