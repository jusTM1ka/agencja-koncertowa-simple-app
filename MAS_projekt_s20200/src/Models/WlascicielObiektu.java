package Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name="WlascicielObiektu")
public class WlascicielObiektu extends Osoba{

    private double cenaZaWynajemObiektu;
    private ObiektKoncertu obiektKoncertu;
    private List<Umowa> umowy;

    public WlascicielObiektu( String imie, String nazwisko, int pesel, String adresZamieszkania, double cenaZaWynajemObiektu) {
        super(imie, nazwisko, pesel, adresZamieszkania);
        this.cenaZaWynajemObiektu = cenaZaWynajemObiektu;
        this.umowy = new ArrayList<>();
    }
    public WlascicielObiektu(){

    }
    @OneToMany(
            mappedBy = "wlascicielObiektu",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Umowa> getUmowy() {
        return umowy;
    }

    public void setUmowy(List<Umowa> umowy) {
        this.umowy = umowy;
    }

    @OneToOne(
            mappedBy = "wlascicielObiektu",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public ObiektKoncertu getObiektKoncertu() {
        return obiektKoncertu;
    }

    public void setObiektKoncertu(ObiektKoncertu obiektKoncertu) {
        this.obiektKoncertu = obiektKoncertu;
    }

    @Basic
    public double getCenaZaWynajemObiektu() {
        return cenaZaWynajemObiektu;
    }

    public void setCenaZaWynajemObiektu(double cenaZaWynajemObiektu) {
        this.cenaZaWynajemObiektu = cenaZaWynajemObiektu;
    }

}
