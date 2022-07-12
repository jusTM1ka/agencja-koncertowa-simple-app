package Models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class WlascicielAgencji extends Osoba{
    public WlascicielAgencji( String imie, String nazwisko, int pesel, String adresZamieszkania) {

        super(imie, nazwisko, pesel, adresZamieszkania);


   }
    public WlascicielAgencji(){

    }




}
