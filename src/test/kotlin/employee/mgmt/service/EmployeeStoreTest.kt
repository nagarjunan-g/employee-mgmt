package employee.mgmt.service

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.nulls.beNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.micronaut.serde.ObjectMapper
import java.io.File

class EmployeeStoreTest : DescribeSpec({
    beforeEach {
        File("employee-store.json").delete()
    }
    describe("EmployeeStore") {
        val employeeStore = EmployeeStore(ObjectMapper.getDefault())
        it("should update employee") {
            val createdEmployee = employeeStore.createOrUpdate("123", "John", "London")

            createdEmployee.employeeId shouldBe "123"
            createdEmployee.name shouldBe "John"
            createdEmployee.city shouldBe "London"

            val updatedEmployee = employeeStore.createOrUpdate("123", "John Doe", "New York")

            updatedEmployee.employeeId shouldBe "123"
            updatedEmployee.name shouldBe "John Doe"
            updatedEmployee.city shouldBe "New York"
        }

        it("should create employee if employee does not exist for createOrUpdate") {
            val updatedEmployee = employeeStore.createOrUpdate("non-existing-id", "John Doe", "New York")

            updatedEmployee.employeeId shouldBe "non-existing-id"
            updatedEmployee.name shouldBe "John Doe"
            updatedEmployee.city shouldBe "New York"
        }

        it("should update employee if employee exists for createOrUpdate") {
            val createdEmployee = employeeStore.createOrUpdate("123", "John", "London")

            createdEmployee.employeeId shouldBe "123"
            createdEmployee.name shouldBe "John"
            createdEmployee.city shouldBe "London"

            val updatedEmployee = employeeStore.createOrUpdate("123", "John Doe", "New York")

            updatedEmployee.employeeId shouldBe "123"
            updatedEmployee.name shouldBe "John Doe"
            updatedEmployee.city shouldBe "New York"
        }

        it("should get employee") {
            val createdEmployee = employeeStore.createOrUpdate("123", "John", "London")
            val employee = employeeStore.get(createdEmployee.employeeId)

            employee shouldNot beNull()
            employee!!.employeeId shouldBe "123"
            employee.name shouldBe "John"
            employee.city shouldBe "London"
        }
    }
})