package Models;

import net.bytebuddy.dynamic.DynamicType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SourceType;

import javax.persistence.*;
import java.util.Optional;

@Entity(name="Umowa")
public class Umowa {
private long id;
private String tytul;
private ManagerZespolu manager;
private WlascicielObiektu wlascicielObiektu;
private String warunkiUmowy;
private boolean przypisanaDoKoncertu;
private boolean zrealizowana;

@Column(nullable = true, name = "koncert")
private Koncert koncert;



    public Umowa(){

    }
    public Umowa(String tytul, ManagerZespolu manager, WlascicielObiektu wlascicielObiektu, String warunkiUmowy) {
        this.tytul = tytul;
        this.manager = manager;
        this.wlascicielObiektu = wlascicielObiektu;
        this.warunkiUmowy = warunkiUmowy;
        this.przypisanaDoKoncertu = false;
        this.zrealizowana = false;
        this.koncert=null;
    }




    @OneToOne(
            mappedBy = "umowa",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public Koncert getKoncert()throws Exception {
        return this.koncert;
    }

    public void setKoncert(Koncert koncert)throws Exception {

        if(this.koncert!=null){
            throw new Exception("Umowa jest juz przypisana do koncertu");
        }
        this.koncert = koncert;
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
    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }
    @ManyToOne
    public ManagerZespolu getManager() {
        return manager;
    }

    public void setManager(ManagerZespolu manager) {
        this.manager = manager;
    }
    @ManyToOne
    public WlascicielObiektu getWlascicielObiektu() {
        return wlascicielObiektu;
    }

    public void setWlascicielObiektu(WlascicielObiektu wlascicielObiektu) {
        this.wlascicielObiektu = wlascicielObiektu;
    }
    @Basic
    public String getWarunkiUmowy() {
        return warunkiUmowy;
    }
    public void setWarunkiUmowy(String warunkiUmowy) {
        this.warunkiUmowy = warunkiUmowy;
    }
    @Basic
    public boolean isPrzypisanaDoKoncertu() {
        return przypisanaDoKoncertu;
    }

    public void setPrzypisanaDoKoncertu(boolean przypisanaDoKoncertu) {
        this.przypisanaDoKoncertu = przypisanaDoKoncertu;
    }
    @Basic
    public boolean isZrealizowana() {
        return zrealizowana;
    }

    public void setZrealizowana(boolean zrealizowana) {
        this.zrealizowana = zrealizowana;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " " + this.tytul;
    }
}
