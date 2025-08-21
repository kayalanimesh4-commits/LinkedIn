package com.linkedIn.userservice.utils;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtils {

    //Password Hashing
    public static String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    public static boolean checkPassword(String plainTextPassword, String hashedPassword){
       return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }
}
