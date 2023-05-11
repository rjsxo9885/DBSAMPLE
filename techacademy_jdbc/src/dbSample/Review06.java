
package dbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Review06 {

    public static void main(String[] args) {
        // データベース接続と結果取得のための変数
        try (Scanner scanner = new Scanner(System.in);
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                        "root",
                        "Sonysega25!!");
                PreparedStatement pstmt = con.prepareStatement(
                        "SELECT name, age FROM person WHERE id = ?")) {

               System.out.print("検索キーワードを入力してください > ");
               int id = scanner.nextInt();
               pstmt.setInt(1, id);

               try (ResultSet rs = pstmt.executeQuery()) {
                   while (rs.next()) {
                       System.out.println("===================================================");
                       System.out.printf("名前：%s %d歳 \n", rs.getString("name"),rs.getInt("age"));
                       System.out.println("===================================================");
                   }
               }

           } catch (SQLException e) {
               System.err.println("データベースに異常が発生しました。");
               e.printStackTrace();
           }
        }

    }



