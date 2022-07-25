package bcd.exercise.crypto.solution;

import java.util.List;
import java.util.Optional;

public class App {

    public static void main(String[] args) {

        int sheetNum    =   0;
        int rowFrom     =   4;
        int rowTo       =   20;
        int cellFrom    =   1;
        int cellTo      =   8;

        //Read-All-From-CSV
        $Control_Practitioner pctrl = new $Control_Practitioner("src/learning/tutorial/keanggotaan-majlis-perubatan-tradisional-dan-komplementari-ptk.xlsx");
        List<Practitioner> practitionerLst = pctrl.readAll(sheetNum, rowFrom, rowTo, cellFrom, cellTo);

        //Add-New 
//        boolean isAddNew = false;
        boolean isAddNew = true;
        if (isAddNew == true) {
            practitionerLst.forEach($Control_Registration::addNew);
            System.out.println("Done...");
        } else {
            Optional<List<Practitioner>> pl = Optional.ofNullable(practitionerLst);
//            pl.ifPresent( System.out::println );
            practitionerLst.forEach(System.out::println);
        }
    }

}
