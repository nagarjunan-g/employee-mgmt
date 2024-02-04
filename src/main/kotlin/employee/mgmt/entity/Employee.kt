package employee.mgmt.entity

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class Employee(val employeeId: String, val name: String, val city: String)
