package org.dinesh.userservicetest;


import lombok.RequiredArgsConstructor;
import org.dinesh.dto.UserAuthenticationDto;
import org.dinesh.dto.UserRequestDto;
import org.dinesh.model.User;
import org.dinesh.repository.UserRepository;
import org.dinesh.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(MockitoJUnitRunner.class)
@RequiredArgsConstructor

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void  registerUserWithoutNocase()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dinesh@gmail.com").state("tamilNadu").address("tuty")
                        .country("India").street("4th street").passWord("Dinesh@2000").build();
        var  user = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>() );
        var result =  userService.registerUser(userRequestDto);
        Assertions.assertTrue(result);
    }

    @Test
    public  void registUserWithExistingEmailIdCase()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dinesh@gmail.com").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("Dinesh@2000").build();
        var  user = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>(  ){ {add(user); }});
        var result =  userService.registerUser(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public  void registUserWithWrongPasswordPatern()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dinesh@gmail.com").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("dinesh@2000").build();
        var  user = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>(  ));
        var result =  userService.registerUser(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public  void registUserWithWrongEmailPatern()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dineshgmail.com").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("Dinesh@2000").build();
        var  user = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.save(user)).thenReturn(user);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>(  ));
        var result =  userService.registerUser(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public void updateUserWithNoCase()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dinesh@gmail.com").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("Dinesh@2000").build();
        var updatedUser = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>() {{ add(updatedUser);} });
        Mockito.when((userRepository.save(updatedUser))).thenReturn(updatedUser);
        var result = userService.updateUserDetails(userRequestDto);
        Assertions.assertTrue(result);
    }

    @Test
    public void updateUserWithNoUserInDb()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dineshgmail.com").state("tamilNadu").address("tuty")
                   .country("India").street("4th street").passWord("Dinesh@2000").build();
        var updatedUser = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>() );
        Mockito.when((userRepository.save(updatedUser))).thenReturn(updatedUser);
        var result = userService.updateUserDetails(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public void updateUserWithNoEmailId()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("Dinesh@2000").build();
        var updatedUser = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>() );
        Mockito.when((userRepository.save(updatedUser))).thenReturn(updatedUser);
        var result = userService.updateUserDetails(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public void updateUserWithWrongEmailPattern()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("din").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("Dinesh@2000").build();
        var updatedUser = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>() );
        Mockito.when((userRepository.save(updatedUser))).thenReturn(updatedUser);
        var result = userService.updateUserDetails(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public void updateUserWithWrongPassWordPattern()
    {
        UserRequestDto userRequestDto = UserRequestDto.builder().userName("dinesh").email("dinesh@gmail.com").state("tamilNadu").address("tuty")
                .country("India").street("4th street").passWord("sdssaf").build();
        var updatedUser = userService.userRequestDtotoUserPojo(userRequestDto);
        Mockito.when(userRepository.findByEmail(userRequestDto.getEmail())).thenReturn(new ArrayList<User>(){{ add(updatedUser) ;} } );
        Mockito.when((userRepository.save(updatedUser))).thenReturn(updatedUser);
        var result = userService.updateUserDetails(userRequestDto);
        Assertions.assertFalse(result);
    }

    @Test
    public void verifyUserWithNoCase()
    {
        UserAuthenticationDto userAuthenticationDto = UserAuthenticationDto.builder().emailId("dinesh@gmail.com").passWord("Dinesh@2000").build();
        User user = User.builder().email("dinesh@gmail.com").passWord("Dinesh@2000").build();
        Mockito.when(userRepository.findByEmail(userAuthenticationDto.getEmailId())).thenReturn(new ArrayList<User>(){{ add(user) ;} } );
        var result = userService.verifyUser(userAuthenticationDto);
        Assertions.assertTrue(result);

    }
    @Test
    public void verifyUserWithNoUserInDb()
    {
        UserAuthenticationDto userAuthenticationDto = UserAuthenticationDto.builder().emailId("dinesh@gmail.com").passWord("Dinesh@2000").build();
        User user = User.builder().email("dinesh@gmail.com").passWord("Dinesh@2000").build();
        Mockito.when(userRepository.findByEmail(userAuthenticationDto.getEmailId())).thenReturn(new ArrayList<User>() );
        var result = userService.verifyUser(userAuthenticationDto);
        Assertions.assertFalse(result);

    }
    @Test
    public void verifyUserWithWrongPassWord()
    {
        UserAuthenticationDto userAuthenticationDto = UserAuthenticationDto.builder().emailId("dinesh@gmail.com").passWord("Dinesh@2000").build();
        User user = User.builder().email("dinesh@gmail.com").passWord("dinesh@2000").build();
        Mockito.when(userRepository.findByEmail(userAuthenticationDto.getEmailId())).thenReturn(new ArrayList<User>(){{add(user);}} );
        var result = userService.verifyUser(userAuthenticationDto);
        Assertions.assertFalse(result);

    }



}
