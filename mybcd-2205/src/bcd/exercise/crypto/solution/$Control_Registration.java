package bcd.exercise.crypto.solution;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import bcd.crypto.Crypto;
import bcd.crypto.Symmetric;
import bcd.keygen.PredefinedCharsSecretKey;

public class $Control_Registration {

    private static final String _FILE_REGISTRATION = "src/bcd/exercise/crypto/solution/ListRegistration.txt";

    private static final String _ALGORITHM = "RSA";
    private static final String _DIRECTORY = "KeyStore";
    private static final String _FILE_PUBLICKEY = "PUBLICKEY";
    private static final String _FILE_PRIVATEKEY = "PRIVATEKEY";

    //addRegistration
    public static void addNew(Practitioner p) {
        
        Registration reg = new Registration( p );
        
        Crypto crypto = new Symmetric("AES");
        String key = null;

        if (p != null) {
            try {
                key = crypto.encrypt(p.getName(), PredefinedCharsSecretKey.create());
                p.setKey( key );
                p.setKeyPairDirectory( key.replace("/", "") );//clean the '/' if any
                
                String practitioner_record = p.toString() + System.lineSeparator();
                String registration_record = reg.toString() + System.lineSeparator();

                if (key != null) {
                    Files.write(
                            Paths.get($Control_Practitioner._FILE_PRACTITIONER ), 
                            practitioner_record.getBytes(), 
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    Files.write(Paths.get(_FILE_REGISTRATION ), 
                            registration_record.getBytes(), 
                            StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    
                    $Control_Registration.generateKeyPair(p);
                }
                //VIEW: Decrypting the key for Practitioner name 
                System.out.println( crypto.decrypt(p.getKey(), PredefinedCharsSecretKey.create()) );
                TimeUnit.MILLISECONDS.sleep(10);
            } catch (Exception ex) {
                Logger.getLogger($Control_Registration.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            throw new NullPointerException("Practitioner is null object.");
        }
        
    }
    
    //updateRegistration

    //deleteRegistration
    
    
    //generateKeypair
    public static boolean generateKeyPair(Practitioner p) {

        File _directory = new File(_DIRECTORY);
        _directory.mkdir();
        
        if (_directory.exists()) {

            if (p != null) {
                String keyPairDir = p.getKeyPairDirectory();
                String keyFileName = String.join("/", _DIRECTORY, keyPairDir);
                boolean isCreated = new File(keyFileName).mkdirs();

                try {
                    if (isCreated) {
                        KeyPairGenerator keygen = KeyPairGenerator.getInstance(_ALGORITHM);
                        keygen.initialize(1024);
                        KeyPair keyPair = keygen.generateKeyPair();
                        PublicKey pubk = keyPair.getPublic();
                        put(pubk.getEncoded(), String.join("/", keyFileName, _FILE_PUBLICKEY));
                        PrivateKey privk = keyPair.getPrivate();
                        put(privk.getEncoded(), String.join("/", keyFileName, _FILE_PRIVATEKEY));
                    }else{
                        System.out.println("INFO: KeyPair directory not created.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } else {
            System.out.println(_directory.mkdir() ? "KeyStore created." : "KeyStore is not created.");
        }

        return false;
    }

    private static void put(byte[] keyBytes, String loc) {
        try {
            Files.write(Paths.get(loc), keyBytes, StandardOpenOption.CREATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
