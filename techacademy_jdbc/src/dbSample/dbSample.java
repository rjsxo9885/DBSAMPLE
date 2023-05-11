package dbSample;
import java.util.List;
import java.util.Scanner;

import dbSample.dao.CoutryDAO;
import dbSample.entity.Country;

public class dbSample {

    public static void main(String[] args) {
        // TODO 自動生成されたメソッド・スタブ

        Scanner sc = new Scanner(System.in);

        CoutryDAO dao = new CoutryDAO();

        System.out.print("검색 키워드 입력");
        String name = sc.next();

        List<Country> list = dao.getCountryFromName(name);

        for(Country item : list) {
            System.out.println(item.getName());
            System.out.println(item.getPopulation());
        }
    }

}


