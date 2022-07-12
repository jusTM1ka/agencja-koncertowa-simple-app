package Models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/*
@Entity
@Table(name = "osoba")
@Inheritance(strategy = InheritanceType.JOINED)
*/
@MappedSuperclass
public abstract class Osoba {

    private long id;
    private String imie;
    private String nazwisko;
    private int pesel;
    private String adresZamieszkania;

    public Osoba(){

    }

    public Osoba( String imie, String nazwisko, int pesel, String adresZamieszkania){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.pesel = pesel;
        this.adresZamieszkania = adresZamieszkania;
    }

    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy =
            "increment")
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    @Basic
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }
    @Basic
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }
    @Basic
    public int getPesel() {
        return pesel;
    }

    public void setPesel(int pesel) {
        this.pesel = pesel;
    }
    @Basic
    public String getAdresZamieszkania() {
        return adresZamieszkania;
    }

    public void setAdresZamieszkania(String adresZamieszkania) {
        this.adresZamieszkania = adresZamieszkania;
    }

    @Override
    public String toString() {
        return
                "ID=" + id+ " "+ nazwisko.toUpperCase() ;
    }
}
