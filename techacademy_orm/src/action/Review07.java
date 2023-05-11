package action;

import java.util.Scanner;

import javax.persistence.EntityManager;

import entity.Person;
import util.DBUtil2;

public class Review07 {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ
        Scanner sc = new Scanner(System.in);

        System.out.println("IDを入力してください");
        int a = sc.nextInt();
        // EntityManagerのオブジェクトを生成
        EntityManager em = DBUtil2.createEntityManager();

        // 1件取得して名前を表示する
       // City city = em.find(City.class, 2);
        Person person = em.find(Person.class,a);
        //System.out.println(city.getName());
        if (person != null) {
           // System.out.println(person.getName()+" "+person.getAge());
            System.out.printf("name : %s  \n age: %d",person.getName(),person.getAge());
        }
        // EntityManagerの利用を終了する
        em.close();
    }

}
