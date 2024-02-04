package employee.mgmt

import employee.mgmt.entity.Employee
import employee.mgmt.service.EmployeeStore
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpResponse.created
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Put
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

@Controller("/employee")
class EmployeeController(private val employeeStore: EmployeeStore) {

    @Put
    suspend fun createOrUpdate(@Body createOrUpdateEmployee: Employee): HttpResponse<Employee> =
        withContext(Dispatchers.IO){
            created(
                employeeStore.createOrUpdate(
                    createOrUpdateEmployee.employeeId,
                    createOrUpdateEmployee.name,
                    createOrUpdateEmployee.city
                )
            )
        }
    @Get("/{employeeId}")
    suspend fun get(employeeId: String): HttpResponse<Employee> =
        withContext(Dispatchers.IO){
            employeeStore.get(employeeId)?.let { HttpResponse.ok(it) }
                ?: HttpResponse.notFound()
    }
}