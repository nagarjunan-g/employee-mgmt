package employee.mgmt.service

import employee.mgmt.entity.Employee
import io.micronaut.core.type.Argument
import io.micronaut.serde.ObjectMapper
import jakarta.inject.Singleton
import kotlinx.coroutines.*
import java.io.File

@Singleton
class EmployeeStore(private val objectMapper: ObjectMapper) {

    private val persistentStoreFileName: String = "employee-store.json"

    private suspend fun readEmployeeMapDataFromPersistentStore(fileName: String): Map<String, Employee> =
        withContext(Dispatchers.IO){
            if (doesFileExist(fileName)) {
                val file = File(fileName)
                val content = file.readText()
                objectMapper.readValue(content, Argument.mapOf(String::class.java, Employee::class.java))
            } else {
                mapOf()
            }
        }


    private suspend fun writeEmployeeMapDataToPersistentStore(fileName: String, employeesMap: Map<String, Employee>) {
        withContext(Dispatchers.IO){
            val file = File(fileName)
            val content = objectMapper.writeValueAsString(employeesMap)
            file.writeText(content)
        }
    }

    private fun doesFileExist(fileName: String): Boolean = File(fileName).exists()

    suspend fun createOrUpdate(employeeId: String, name: String, city: String): Employee {

        val employee = Employee(employeeId, name, city)


        readEmployeeMapDataFromPersistentStore(persistentStoreFileName).toMutableMap().let {
            it[employeeId] = employee
            writeEmployeeMapDataToPersistentStore(persistentStoreFileName, it)
        }

        return employee
    }

    suspend fun get(employeeId: String): Employee? = withContext(Dispatchers.IO){
        readEmployeeMapDataFromPersistentStore(persistentStoreFileName)[employeeId]
    }
}