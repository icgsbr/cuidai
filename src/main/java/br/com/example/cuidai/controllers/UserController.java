package br.com.example.cuidai.controllers;

import br.com.example.cuidai.dtos.UserDTO;
import br.com.example.cuidai.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/save")
    @ApiOperation(value = "Save user")
    public ResponseEntity<Object> save(
            @RequestBody
            @ApiParam(value = "User DTO") UserDTO user) {
        try {
            if (user == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not saved");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not saved\n\n" + e.getMessage());
        }
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "Get all users")
    public ResponseEntity<Object> getAll() {
        try {
            if (userService.find().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No users found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.find());
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No users found\n\n" + e.getMessage());
        }
    }

    @GetMapping(value = "/email={email}")
    @ApiOperation(value = "Get user by email")
    public ResponseEntity<Object> getByEmail(
            @RequestBody
            @PathVariable(value = "email")
            @ApiParam(value = "Email") String email) {
        try {
            if (userService.find(email) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.find(email));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No users found\n\n" + e.getMessage());
        }
    }

    @GetMapping(value = "/id={id}")
    @ApiOperation(value = "Get user by id")
    public ResponseEntity<Object> getById(
            @RequestBody
            @PathVariable(value = "id")
            @ApiParam(value = "User id") UUID id) {
        try {
            if (userService.find(id) == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No user found");
            }
            return ResponseEntity.status(HttpStatus.OK).body(userService.find(id));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No users found\n\n" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/all")
    @ApiOperation(value = "Delete all users")
    public ResponseEntity<Object> deleteAll() {
        try {
            if (userService.delete()) {
                return ResponseEntity.status(HttpStatus.OK).body("Users deleted");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Users not deleted");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Users not deleted\n\n" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/email={email}")
    @ApiOperation(value = "Delete user by email")
    public ResponseEntity<Object> deleteByEmail(
            @RequestBody
            @PathVariable(value = "email")
            @ApiParam(value = "Email") String email) {
        try {
            if (userService.delete(email)) {
                return ResponseEntity.status(HttpStatus.OK).body("User deleted");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not deleted");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not deleted\n\n" + e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete/id={id}")
    @ApiOperation(value = "Delete user by id")
    public ResponseEntity<Object> deleteById(
            @RequestBody
            @PathVariable(value = "id")
            @ApiParam(value = "User id") UUID id) {
        try {
            if (userService.delete(id)) {
                return ResponseEntity.status(HttpStatus.OK).body("User deleted");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not deleted");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not deleted\n\n" + e.getMessage());
        }
    }

    @GetMapping(value = "/login")
    @ApiOperation(value = "Login user")
    public ResponseEntity<Object> login(
            @ApiParam(value = "Email") String email,
            @ApiParam(value = "Password") String password) {
        try {
            if (!userService.login(email, password)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Logged: " + userService.login(email, password));
            }
            return ResponseEntity.status(HttpStatus.OK).body("Logged: " + userService.login(email, password));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't do login\n\n" + e.getMessage());
        }
    }

    @GetMapping(value = "/count/gender={gender}")
    @ApiOperation(value = "Count all users by gender")
    public ResponseEntity<Object> countByGender(
            @ApiParam(value = "Gender Dropdown", allowableValues = "Masculino, Feminino, Outro")
            @PathVariable String gender) {
        try {
            if (!gender.isEmpty()) {
                return ResponseEntity.status(HttpStatus.OK).body(userService.countByGender(gender));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't count");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Couldn't do count\n\n" + e.getMessage());
        }
    }
}
