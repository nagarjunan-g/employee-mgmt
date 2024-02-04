package employee.mgmt.entity

import io.micronaut.serde.annotation.Serdeable

@Serdeable
data class CreateEmployeeRequest(val name: String, val city: String)
