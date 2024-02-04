# Employee Management

## Problem statement

You are writing an employee management system where application can create and update employee details. The application should also be able to ready employee details by employeeId. The application needs to persist data across restarts.

Another vendor has already worked on the project and has implemented few functionalities. They have handed over the solution, but we are facing issues with the application. Application is not able to handle concurrent requests.

You have been asked to fix the issue and make application handle concurrent requests. Also, as performance is a key requirement, you need to write the fastest solution.

------

### Technical details
1. Your HTTP app need to expose APIs ([API contract](#api-contract)) on port 8080
2. No existing databases, libraries and services can be used to store the data
3. Application needs to persist data across restarts

## Data to be stored
```
{
    employeeId: string,
    name: string,
    city: string
}
```

---
## API contract

>##### GET /employee/:id
Returns the specified employee.

###### URL Params
`id=[string]` *Required*

###### Success Response
* Status code: 201
* Content: `{ <employee_object> }`

###### Error Response
* Status code: 404
---

>##### PUT /employee/
Creates or updates a Employee with employeeId with new details returns the employeeId

###### Request & Response headers
Content-Type: application/json

###### Body
```
{
    employeeId: string,
    name: string,
    city: string
}
```
###### Success Response
* Status code: 201
* Content: `{ <employee_object> }`

###### Response Body
```
{
    employeeId: string,
    name: string,
    city: string
}
```
---