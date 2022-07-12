package Models;

import javax.persistence.*;

@MappedSuperclass
public class Pracownik extends Osoba{

    private double wysokoscWynagrodzenia;
    private int stazPracy;
    private int nrTelefonu;
    public Pracownik(){

    }
    public Pracownik(String imie, String nazwisko, int pesel, String adresZamieszkania, double wysokoscWynagrodzenia, int stazPracy, int nrTelefonu) {
        super(imie, nazwisko, pesel, adresZamieszkania);
        this.wysokoscWynagrodzenia = wysokoscWynagrodzenia;
        this.stazPracy = stazPracy;
        this.nrTelefonu = nrTelefonu;

    }

    @Transient
    public double getPensjaMiesieczna(int liczbaGodzin) {
        return liczbaGodzin * wysokoscWynagrodzenia;
    }

    @Basic
    public double getWysokoscWynagrodzenia() {
        return wysokoscWynagrodzenia;
    }

    public void setWysokoscWynagrodzenia(double wysokoscWynagrodzenia) {
        this.wysokoscWynagrodzenia = wysokoscWynagrodzenia;
    }
    @Basic
    public int getStazPracy() {
        return stazPracy;
    }

    public void setStazPracy(int stazPracy) {
        this.stazPracy = stazPracy;
    }
    @Basic
    public int getNrTelefonu() {
        return nrTelefonu;
    }

    public void setNrTelefonu(int nrTelefonu) {
        this.nrTelefonu = nrTelefonu;
    }

}
