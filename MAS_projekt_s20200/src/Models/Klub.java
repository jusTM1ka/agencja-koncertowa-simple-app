package Models;

import javax.persistence.Basic;
import javax.persistence.Entity;
@Entity(name="Klub")
public class Klub extends ObiektKoncertu{
    int liczbaDostepnychLazienek;
    public Klub(){

    }
    public Klub(String nazwa, String adres, WlascicielObiektu wlascicielObiektu, int liczbaDostepnychLazienek) {
        super(nazwa,adres,wlascicielObiektu);
        this.liczbaDostepnychLazienek = liczbaDostepnychLazienek;

    }
    @Basic
    public int getLiczbaDostepnychLazienek() {
        return liczbaDostepnychLazienek;
    }
    public void setLiczbaDostepnychLazienek(int liczbaDostepnychLazienek) {
        this.liczbaDostepnychLazienek = liczbaDostepnychLazienek;
    }
}
