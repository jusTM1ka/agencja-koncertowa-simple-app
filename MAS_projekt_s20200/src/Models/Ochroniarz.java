package Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name="Ochroniarz")
public class Ochroniarz extends Pracownik{
    private String specjalizacjaOchrony;
    private Set<Koncert> koncerty;


    public  Ochroniarz(){

    }
    public Ochroniarz( String imie, String nazwisko, int pesel, String adresZamieszkania, double wysokoscWynagrodzenia, int stazPracy, int nrTelefonu, String specjalizacjaOchrony) {
        super(imie, nazwisko, pesel, adresZamieszkania, wysokoscWynagrodzenia, stazPracy, nrTelefonu);
        this.specjalizacjaOchrony = specjalizacjaOchrony;
        this.koncerty = new HashSet<>();

    }
    @ManyToMany(mappedBy = "ochroniarze")
    public Set<Koncert> getkoncerty() {
        return koncerty;
    }

    public void setkoncerty(Set<Koncert> koncerty) {
        this.koncerty = koncerty;
    }

    @Basic
    public String getSpecjalizacjaOchrony() {
        return specjalizacjaOchrony;
    }
    public void setSpecjalizacjaOchrony(String specjalizacjaOchrony) {
        this.specjalizacjaOchrony = specjalizacjaOchrony;
    }

}
