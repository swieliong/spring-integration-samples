## Spring Exception Handling

#### Sources:
- (done) https://www.javainuse.com/spring/boot-exception-handling
- (done) https://howtodoinjava.com/spring-boot2/spring-rest-request-validation/
- (done) https://www.toptal.com/java/spring-boot-rest-api-error-handling
- Handle the Access Denied in Spring Security - https://www.baeldung.com/exception-handling-for-rest-with-spring

Step to run:
1. mvn spring-boot:run
2. Using postman go to:
    localhost:8080/employee2 Employee is not found, so ResourceNotFoundException is thrown. Response code is sent as 404.
    localhost:8080/employee3 EmployeeServiceException is thrown. Response code is sent as 500.


#### Reference
https://restfulapi.net/http-status-codes/

Let’s consider a few HTTP Methods:
 * GET : Should not update anything. Should be idempotent (same result in multiple calls).
 * 		Return Codes 200 (OK) + 404 (NOT FOUND) + 400 (BAD REQUEST)
 * POST : Should create new resource. Ideally return JSON with link to newly created resource.
 * 		Return code 201 (CREATED) can be used.
 * PUT : Update a known resource. ex: update client details.
 * 		Return Codes : 200(OK) + 404 (NOT FOUND) + 400 (BAD REQUEST)
 * DELETE : Used to delete a resource.
 * 		Return Codes : 200(OK).