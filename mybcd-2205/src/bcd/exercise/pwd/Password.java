/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bcd.exercise.pwd;

import java.util.Base64;
import java.util.UUID;

import bcd.function.Hasher;
import bcd.function.Salt;

/**
 *
 * @author kimlee
 */
public class Password {
    
    private static final String ALGO = "MD5";
//    private static final String ALGO = "SHA-256";
//    private static final String ALGO = "SHA-512";
    
    private static final String FILE = "secret.txt";
    private static final String FILE_LOGIN = "login.txt";
    
    private static String uuid;
    static{
        uuid = UUID.randomUUID().toString();
        System.out.println( uuid );
        
    }
    
    public static void create( String username, String passwd ) throws Exception{
         String rand = Base64.getEncoder().encodeToString( Salt.generate() );
         String hash = Hasher.md5( Txt.append(rand, passwd) );
         //write to file
         //FORMAT: UID|SALT|PASSWORDHASH
         IO.write(FILE, String.join("|", uuid, rand, hash));
         //FORMAT: USERNAME|UUID
         IO.write(FILE_LOGIN, String.join("|", username, uuid));
         System.out.println( "rand: " + rand );
         System.out.println( "Password Hash: " + hash );
    }
  
}
