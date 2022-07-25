package bcd.exercise.crypto.solution;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class $Control_Practitioner {

    private XlsxReader reader;
    
    public $Control_Practitioner(){}
    
    public $Control_Practitioner( String fileName ){
        reader = new XlsxReader(fileName);
    }
    
    //readAll-cvs
    public List<Practitioner> readAll( int sheetNum, int rowFrom, int rowTo, int cellFrom, int cellTo ){
        
        List<String> lst = this.reader.getAll(sheetNum, rowFrom, rowTo, cellFrom, cellTo);
        return lst.stream()
                .map( str -> {
                    String[] split = str.split("\\,");
                    Practitioner p1 = new Practitioner( 
                            null, 
                            split[1], 
                            split[2], 
                            new Account( split[4], split[4] ),
                            null
                    );
                    p1.setReplacement( 
                            new Replacement( 
                                    new Account( split[6], split[7] ), 
                                    split[5] ) 
                    );
                    return p1;
                } ).collect(Collectors.toCollection(ArrayList::new));
        
    }
    
    //getAll-ListPractitioner.txt
    public static final String _FILE_PRACTITIONER = "src/bcd/exercise/crypto/solution/ListPractitioner.txt";
    public List<Practitioner> getAll(){
        try {
            return Files.readAllLines(Paths.get( _FILE_PRACTITIONER )).stream()
                    .map( elem ->
                        {   String split[] = elem.split("\\,");
                            Practitioner p = new Practitioner( 
                                    split[0],
                                    split[1],
                                    split[2],
                                    new Account(split[3],split[4]),
                                    split[8] );
                            p.setReplacement(new Replacement( new Account(split[6],split[7]), split[5]));
                            return p;
                        }    
                        
                    )
                    .collect( Collectors.toCollection(ArrayList::new) );
        } catch (IOException ex) {
            Logger.getLogger($Control_Practitioner.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
}
