package br.com.cbgomes.user.resource

import br.com.cbgomes.user.business.UserService
import br.com.cbgomes.user.model.User
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(value= ["/api/v1/users"])
class UserResource(val userService: UserService) {


    @PostMapping("/create-user")
    @ApiOperation(value = "Create user with password encrypted HASH HS251")
    @ApiResponses(value = [
        ApiResponse(code = 201, message = "Create user and credentials"),
        ApiResponse(code = 500, message = "Internal error, please contact the administrator of system"),
        ApiResponse(code = 404, message = "Sorry, page not found"),
        ApiResponse(code = 401, message = "Sorry, unauthorized access to service, contact the administrator of system"),
        ApiResponse(code = 403, message = "Sorry, you dont have permission to access this service ")])
    @ResponseStatus(HttpStatus.CREATED)
    fun createUser(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity.ok(this.userService.createUser(user))
    }
}