package org.dinesh.service;

import lombok.RequiredArgsConstructor;
import org.dinesh.dto.UserAuthenticationDto;
import org.dinesh.dto.UserRequestDto;
import org.dinesh.model.User;
import org.dinesh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {


    @Autowired
    UserRepository userRepository;

    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$";

     private static Pattern passWordPattern = Pattern.compile(PASSWORD_PATTERN);


    private static Pattern emailPattern =  Pattern.compile(EMAIL_PATTERN);
    @Autowired
    ApplicationContext applicationContext;

    public boolean registerUser(UserRequestDto userRequestDto)
    {


            if(findUserByEmail(userRequestDto.getEmail()).size()==0 && verifyEmail(userRequestDto.getEmail())&& verifyPassWord(userRequestDto.getPassWord()))  {
                var user = userRequestDtotoUserPojo(userRequestDto);
                userRepository.save(user);
                return true;
            }
            return false ;



    }

    private  boolean verifyEmail(String email)
    {
        return    emailPattern.matcher(email).matches();
    }

    private boolean verifyPassWord(String passWord)
    {
        return  passWordPattern.matcher(passWord).matches();
    }

    public boolean verifyUser(UserAuthenticationDto userAuthenticationDto)
    {
        var  userList = findUserByEmail(userAuthenticationDto.getEmailId());
        if(userList.size()>0)
        {
            var passsword  = userList.get(0).getPassWord();
            return     passsword.equals(userAuthenticationDto.getPassWord());
        }
        return  false;
    }


    public List<User> findUserByEmail(String emailId)
    {
         return userRepository.findByEmail(emailId);
    }

    public User userRequestDtotoUserPojo(UserRequestDto userRequestDto)
    {
      //  var bCryptEncoder  = applicationContext.getBean(BCryptPasswordEncoder.class);
     return     User.builder().email(userRequestDto.getEmail()).state(userRequestDto.getState())
                .address(userRequestDto.getAddress()).country(userRequestDto.getCountry())
                .createdTime(Timestamp.from(Instant.now())).updatedTime(Timestamp.from(Instant.now()))
                .street(userRequestDto.getStreet()).userName(userRequestDto.getUserName())
                .phoneNo(userRequestDto.getPhoneNo()).passWord(userRequestDto.getPassWord()).build();

    }

    public  boolean updateUserDetails(UserRequestDto userRequestDto)
    {
         if(userRequestDto.getEmail()!=null)
         {
             var result =  userRepository.findByEmail(userRequestDto.getEmail());
             if( result.size()==0 || !verifyEmail(userRequestDto.getEmail()) || !verifyPassWord(userRequestDto.getPassWord()))
                 return false;
             User user = result.get(0);
            var updatedUser =  userRequestDtotoUserPojo(userRequestDto);
            updatedUser.setId(user.getId());
            updatedUser.setCreatedTime(user.getCreatedTime());
            userRepository.save(updatedUser);
            return true;

         }
         return false ;
    }



}
