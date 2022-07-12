package GUI;

import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class DbController {
    SessionFactory sessionFactory;
    List<Operator> operatorList;
    List<Barman> barmanList;
    List<Ochroniarz> ochroniarzList;
    List<ObiektKoncertu> obiektKoncertuList;

    public DbController(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;

    }
    public boolean czySaUmowy() {
        int counter = 0;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Umowa> umowy = session.createQuery(" from Umowa").list();

        if (umowy.isEmpty()) {
            session.getTransaction().commit();
            session.close();
            return false;
        }
        for (Umowa umowa : umowy) {
            if (!umowa.isPrzypisanaDoKoncertu()) {
                counter++;
            }
        }
        if (counter > 0) {
            session.getTransaction().commit();
            session.close();
            return true;
        }
        session.getTransaction().commit();
        session.close();
        return false;
    }

    public List<Umowa> wyswietlUmowy(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Umowa> wszystkieUmowy = session.createQuery( " from Umowa" ).list();
        List<Umowa> dostepneUmowy = new ArrayList<>();
        for(Umowa umowa : wszystkieUmowy){
            if(!umowa.isPrzypisanaDoKoncertu()){
                dostepneUmowy.add(umowa);
            }
        }
        session.getTransaction().commit();
        session.close();
        return dostepneUmowy;
    }
    public List<WlascicielObiektu> wyswietlWlascicieliObiektu(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<WlascicielObiektu> wszyscyWlasciciele = session.createQuery( " from WlascicielObiektu" ).list();
        session.getTransaction().commit();
        session.close();
        return wszyscyWlasciciele;
    }

    public List<ManagerZespolu> wyswietlManagerow(){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ManagerZespolu> managerzy = session.createQuery( " from ManagerZespolu" ).list();
        session.getTransaction().commit();
        session.close();
        return managerzy;
    }
    public void dodajUmowe(Umowa umowa){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(umowa);
        session.getTransaction().commit();
        session.close();

    }
    public boolean czySaPracownicy(Date data){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        operatorList = new ArrayList<>();
        barmanList = new ArrayList<>();
        ochroniarzList = new ArrayList<>();
        obiektKoncertuList = new ArrayList<>();
        int ochroniarzCounter = 0;
        int barmanCounter= 0;
        int obiektCounter=0;
        int operatorCounter=0;
        List<Ochroniarz> ochroniarze = session.createQuery(" from Ochroniarz").list();
        List<Barman> barmani = session.createQuery(" from Barman").list();
        List<Operator> operatorzy = session.createQuery(" from Operator").list();
        List<ObiektKoncertu> obiektyKoncertu = session.createQuery(" from ObiektKoncertu").list();
        for(Barman b: barmani){
            Set<Koncert> koncerts = b.getKoncerty();
            if(koncerts.isEmpty()){
                barmanCounter++;
                barmanList.add(b);
                System.out.println("Pusty set koncertow barmana");
            }else {
                int counter = 0;
                for (Koncert k : koncerts) {
                    if (k.getDataKoncertu().toLocaleString().equals(data.toLocaleString())){
                        counter++;
                    }
                }
                if(counter==0){
                    barmanList.add(b);
                    barmanCounter++;
                }
                counter=0;
            }


        }
        for(Ochroniarz o: ochroniarze){
            Set<Koncert> koncerts = o.getkoncerty();
            if(koncerts.isEmpty()){
                ochroniarzCounter++;
                ochroniarzList.add(o);
            }else {
                int counter = 0;
                for (Koncert k : koncerts) {
                    if (k.getDataKoncertu().toLocaleString().equals(data.toLocaleString())){
                        counter++;
                    }
                }
                if(counter==0){
                    ochroniarzList.add(o);
                    ochroniarzCounter++;
                }
                counter=0;

            }

        }
        for(Operator o: operatorzy){
            List<Koncert> koncerts = o.getKoncerty();
            if(koncerts.isEmpty()){
                operatorCounter++;
                operatorList.add(o);
            }else {
                int counter = 0;
                for (Koncert k : koncerts) {
                    if (k.getDataKoncertu().toLocaleString().equals(data.toLocaleString())){
                        counter++;
                    }
                }
                if(counter==0){
                    operatorList.add(o);
                    operatorCounter++;
                }
                counter=0;
            }

        }
        for(ObiektKoncertu o: obiektyKoncertu){
            List<Koncert> koncerts = o.getKoncert();
            if(koncerts.isEmpty()){
                obiektCounter++;
                obiektKoncertuList.add(o);
            }else {
                int counter = 0;
                for (Koncert k : koncerts) {
                    if (k.getDataKoncertu().toLocaleString().equals(data.toLocaleString())){
                        counter++;
                    }
                }
                if(counter==0){
                    obiektKoncertuList.add(o);
                    obiektCounter++;
                }
                counter=0;
            }

        }

        if(obiektCounter > 0 && ochroniarzCounter >0 && barmanCounter > 0 && operatorCounter > 0 ){
            session.getTransaction().commit();
            session.close();
            return true;
        }
        session.getTransaction().commit();
        session.close();
        return false;


    }
    public void dodajKoncertdoSystemu(Umowa umowa, List<Barman> barmanKoncert, List<Ochroniarz> ochroniarzKoncert, Date data, Operator operator, int liczbaUczestnikow, ObiektKoncertu obiekt){
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        if(barmanKoncert.size()>1 && ochroniarzKoncert.size() > 1){
            Koncert koncert = Koncert.createKoncert(data,liczbaUczestnikow,operator,obiekt,umowa,barmanKoncert.get(0),ochroniarzKoncert.get(0));
            umowa.setPrzypisanaDoKoncertu(true);
            session.update(umowa);
            for(int i=1; i<barmanKoncert.size();i++){
                koncert.getBarmani().add(barmanKoncert.get(i));
            }
            for(int i =1; i< ochroniarzKoncert.size();i++){
                koncert.getOchroniarze().add(ochroniarzKoncert.get(i));
            }
            session.save(koncert);

        }else if(barmanKoncert.size() >1 && ochroniarzKoncert.size()==1){
            Koncert koncert = Koncert.createKoncert(data,liczbaUczestnikow,operator,obiekt,umowa,barmanKoncert.get(0),ochroniarzKoncert.get(0));
            umowa.setPrzypisanaDoKoncertu(true);
            session.update(umowa);
            for(int i=1; i<barmanKoncert.size();i++){
                koncert.getBarmani().add(barmanKoncert.get(i));
            }
            session.save(koncert);
        }else if(barmanKoncert.size()==1&& ochroniarzKoncert.size()>1){
            Koncert koncert = Koncert.createKoncert(data,liczbaUczestnikow,operator,obiekt,umowa,barmanKoncert.get(0),ochroniarzKoncert.get(0));
            umowa.setPrzypisanaDoKoncertu(true);
            session.update(umowa);
            for(int i =1; i< ochroniarzKoncert.size();i++){
                koncert.getOchroniarze().add(ochroniarzKoncert.get(i));
            }
            session.save(koncert);
        }else{
            Koncert koncert=  Koncert.createKoncert(data,liczbaUczestnikow,operator,obiekt,umowa,barmanKoncert.get(0),ochroniarzKoncert.get(0));
            umowa.setPrzypisanaDoKoncertu(true);
            session.update(umowa);
            session.save(koncert);
        }
        session.getTransaction().commit();
        session.close();
    }
    public List<Barman> wyswietlListeBarmanow(){
        return this.barmanList;
    }
    public List<Ochroniarz> wyswietlListeOchroniarzy(){
        return this.ochroniarzList;
    }
    public List<ObiektKoncertu> wyswietlListeObiektow(){
        return this.obiektKoncertuList;
    }
    public List<Operator> wyswietlListeOperatorw(){
        return this.operatorList;
    }
}
