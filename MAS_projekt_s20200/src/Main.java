import GUI.MyFrame;
import Models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {
            public static void main(String[]args){

            StandardServiceRegistry registry = null;
            SessionFactory sessionFactory = null;

            try {
                registry = new StandardServiceRegistryBuilder()
                        .configure() // configures settings from hibernate.cfg.xml
                        .build();
                sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
                /*
                Date date = new Date();
                WlascicielObiektu wlascicielObiektu = new WlascicielObiektu("Michal", "Kaczanowski", 990712063,"Jakas 44", 25000 );
                Barman barman = new Barman( "Michal", "Barmanowski", 990712063,"Jakas 44","mohito", 25, 2, 997);
                ObiektKoncertu klub= new Klub("Stodola","jakas 42", wlascicielObiektu, 5);
                ManagerZespolu managerZespolu = new ManagerZespolu("Michal", "Kaczanowski", 990712063,"Jakas 44","Architects");
                Umowa umowa = new Umowa("koncert architects",managerZespolu,wlascicielObiektu,"duzo hajsu duzo zdrowia");
                Operator operator = new Operator("Bartek", "Operatorski", 990712063,"Jakas 44","nie ma", 25, 1, 997);
                Ochroniarz ochroniarz = new Ochroniarz("Bartek", "Ochronny", 990712063,"Jakas 44",25, 2, 997, "bramki");

                WlascicielObiektu wlascicielObiektu1 = new WlascicielObiektu("Bartek", "Jakis", 990712063,"Mala 44", 5000 );
                ObiektKoncertu klub1= new Klub("Progresja","Fort Wola 20", wlascicielObiektu1, 5);
                ManagerZespolu managerZespolu1 = new ManagerZespolu("Adam", "Adamski", 990712063,"Druga 2","Bring me the Horizon");
                Umowa umowa1 = new Umowa("koncert BMTH",managerZespolu1,wlascicielObiektu1,"duzo hajsu duzo zdrowia");
                String data = date.getYear()+1900+"-"+date.getMonth()+"-"+date.getDate();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date convertedCurrentDate = sdf.parse(data);
                Koncert koncert = Koncert.createKoncert(convertedCurrentDate,200,operator,klub,umowa, barman, ochroniarz);
                umowa.setPrzypisanaDoKoncertu(true);
                Operator operator1 = new Operator("Bartek", "Swietlny", 990712063,"Jakas 44","nie ma", 25, 1, 997);
                Barman barman1 = new Barman( "Jacek", "Drinkowy", 990712063,"Jakas 44","mohito", 25, 2, 997);
                Ochroniarz ochroniarz1 = new Ochroniarz("Jacek", "Mocny", 990712063,"Jakas 44",25, 2, 997, "bramki");

                Session session = sessionFactory.openSession();
                session.beginTransaction();

                session.save(wlascicielObiektu);
                session.save(managerZespolu);
                session.save(klub);
                session.save(umowa);
                session.save(operator);
                session.save(ochroniarz);
                session.save(barman);
                session.save(koncert);
                session.save(barman1);
                session.save(ochroniarz1);
                session.save(operator1);
                session.save(wlascicielObiektu1);
                session.save(klub1);
                session.save(managerZespolu1);
                session.save(umowa1);

                session.getTransaction().commit();
                session.close();

                */

            }
            catch (Exception e) {
                e.printStackTrace();

                StandardServiceRegistryBuilder.destroy(registry);
            }
            finally {

                if (sessionFactory != null) {
                    sessionFactory.close();
                }


            }



    }
}
