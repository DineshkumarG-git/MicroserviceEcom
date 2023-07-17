package org.dinesh.controllers;

import org.dinesh.dto.UserAuthenticationDto;
import org.dinesh.dto.UserRequestDto;
import org.dinesh.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/Register")
    public Boolean userRegistry( @RequestBody  UserRequestDto userRequestDto)
    {

      return  userService.registerUser(userRequestDto);
    }

    @PostMapping("/UpdateUserDetail")
    public Boolean updateUserDetail( @RequestBody UserRequestDto userRequestDto)
    {
        return userService.updateUserDetails( userRequestDto);
    }

    @PostMapping("/Login")
    public Boolean verifyUser(@RequestBody UserAuthenticationDto userAuthenticationDto)
    {
         return userService.verifyUser(userAuthenticationDto);
    }



    @GetMapping("/test")
    public String testMethod()
    {
        return "hello";
    }

}
