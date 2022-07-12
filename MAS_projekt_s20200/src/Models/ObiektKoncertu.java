package Models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;


@Entity(name = "ObiektKoncertu")
@Table(name = "obiektKoncertu")
@Inheritance(strategy = InheritanceType.JOINED)
public class ObiektKoncertu {

    private long id;
    private String nazwa;
    private String adres;
    private WlascicielObiektu wlascicielObiektu;
    private List<Koncert> koncerty;

    public ObiektKoncertu(String nazwa, String adres, WlascicielObiektu wlascicielObiektu) {
        this.nazwa = nazwa;
        this.adres = adres;
        this.wlascicielObiektu = wlascicielObiektu;
    }
    public ObiektKoncertu(){

    }
    @OneToMany(
            mappedBy = "obiektKoncertu",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Koncert> getKoncert() {
        return koncerty;
    }

    public void setKoncert(List<Koncert> koncerty) {
        this.koncerty = koncerty;
    }

    @OneToOne
    public WlascicielObiektu getWlascicielObiektu() {
        return wlascicielObiektu;
    }
    public void setWlascicielObiektu(WlascicielObiektu wlascicielObiektu) {
        this.wlascicielObiektu = wlascicielObiektu;
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
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    @Basic
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String toString(){
        return "ID: " + this.id + " " + this.nazwa;
    }
}
