package Models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity(name="Koncert")
public class Koncert {

    private long id;
    private Date dataKoncertu;
    private int maksymalnaLiczbaUczestnikow;
    private Operator operator;
    private ObiektKoncertu obiektKoncertu;
    private Umowa umowa;
    private Set<Barman> barmani;
    private Set<Ochroniarz> ochroniarze ;
    private static int ograniczenieWiekowe = 18;

    public Koncert(){

    }
    private Koncert( Date dataKoncertu, int maksymalnaLiczbaUczestnikow, Operator operator, ObiektKoncertu obiektKoncertu, Umowa umowa, Barman barman, Ochroniarz ochroniarz) {
        this.dataKoncertu = dataKoncertu;
        this.maksymalnaLiczbaUczestnikow = maksymalnaLiczbaUczestnikow;
        this.operator = operator;
        this.obiektKoncertu = obiektKoncertu;
        this.umowa = umowa;
        this.barmani = new HashSet<>();
        this.ochroniarze = new HashSet<>();
        ochroniarze.add(ochroniarz);
        barmani.add(barman);
    }
    public static Koncert createKoncert( Date dataKoncertu, int maksymalnaLiczbaUczestnikow, Operator operator, ObiektKoncertu obiektKoncertu, Umowa umowa, Barman barman, Ochroniarz ochroniarz){
        Koncert koncert = new Koncert(dataKoncertu, maksymalnaLiczbaUczestnikow, operator , obiektKoncertu , umowa, barman, ochroniarz);
        try {
            umowa.setKoncert(koncert);
        }catch(Exception e){
            e.printStackTrace();
        }
        return koncert;
    }

    public void dodajOchroniarza(Ochroniarz ochroniarz){
        ochroniarze.add(ochroniarz);
    }
    public void dodajBarmana(Barman barman){
        barmani.add(barman);
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
    public Date getDataKoncertu() {
        return dataKoncertu;
    }

    public void setDataKoncertu(Date dataKoncertu) {
        this.dataKoncertu = dataKoncertu;
    }
    @Basic
    public int getMaksymalnaLiczbaUczestnikow() {
        return maksymalnaLiczbaUczestnikow;
    }

    public void setMaksymalnaLiczbaUczestnikow(int maksymalnaLiczbaUczestnikow) {
        this.maksymalnaLiczbaUczestnikow = maksymalnaLiczbaUczestnikow;
    }
    @ManyToOne
    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }
    @ManyToOne
    public ObiektKoncertu getObiektKoncertu() {
        return obiektKoncertu;
    }

    public void setObiektKoncertu(ObiektKoncertu obiektKoncertu) {
        this.obiektKoncertu = obiektKoncertu;
    }
    @OneToOne
    public Umowa getUmowa() {
        return umowa;
    }

    public void setUmowa(Umowa umowa) {
        this.umowa = umowa;
    }

    @ManyToMany
    @JoinTable(
            name = "barman_koncert",
            joinColumns = @JoinColumn(name = "koncert_id"),
            inverseJoinColumns = @JoinColumn(name = "barman_id"))
    public Set<Barman> getBarmani() {
        return barmani;
    }

    public void setBarmani(Set<Barman> barmani) {
        this.barmani = barmani;
    }
    @ManyToMany
    @JoinTable(
            name = "ochroniarz_koncert",
            joinColumns = @JoinColumn(name = "koncert_id"),
            inverseJoinColumns = @JoinColumn(name = "ochroniarz_id"))
    public Set<Ochroniarz> getOchroniarze() {
        return ochroniarze;
    }

    public void setOchroniarze(Set<Ochroniarz> ochroniarze) {
        this.ochroniarze = ochroniarze;
    }
    @Basic
    public static int getOgraniczenieWiekowe() {
        return ograniczenieWiekowe;
    }

    public static void setOgraniczenieWiekowe(int ograniczenieWiekowe) {
        Koncert.ograniczenieWiekowe = ograniczenieWiekowe;
    }

    @Override
    public String toString() {
        return "Koncert{" +
                "id=" + id +
                ", dataKoncertu=" + dataKoncertu +
                ", maksymalnaLiczbaUczestnikow=" + maksymalnaLiczbaUczestnikow +
                ", operator=" + operator +
                ", obiektKoncertu=" + obiektKoncertu +
                ", umowa=" + umowa +
                ", barmani=" + barmani +
                ", ochroniarze=" + ochroniarze +
                '}';
    }
}
