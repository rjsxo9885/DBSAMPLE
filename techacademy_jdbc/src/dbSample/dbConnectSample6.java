package dbSample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class dbConnectSample6 {

    public static void main(String[] args) {
        // データベース接続と結果取得のための変数
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. ドライバのクラスをJava上で読み込む
            Class.forName("com.mysql.cj.jdbc.Driver");

            // 2. DBと接続する
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost/kadaidb?useSSL=false&allowPublicKeyRetrieval=true",
                "root",
                "Sonysega25!!"
            );

            // 3. DBとやりとりする窓口（PreparedStatementオブジェクト）の作成
            String sql = "SELECT name, age FROM person WHERE id = ?";
            pstmt = con.prepareStatement(sql);

            // 4, 5. SQL文の実行と結果の取得
            System.out.print("検索キーワードを入力してください > ");
            String str = keyIn();
            int id = Integer.parseInt(str);

            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();

            // 6. 結果の表示
            while (rs.next()) {
                System.out.println(rs.getString("name"));
                System.out.println(rs.getInt("age"));
            }

        } catch (ClassNotFoundException e) {
            System.err.println("JDBCドライバのロードに失敗しました。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("データベースに異常が発生しました。");
            e.printStackTrace();
        } finally {
            // 7. 接続を閉じる
            if( rs != null ){
                try {
                    rs.close();
                } catch (SQLException e) {
                    System.err.println("ResultSetを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if( pstmt != null ){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.err.println("PreparedStatementを閉じるときにエラーが発生しました。");
                    e.printStackTrace();
                }
            }
            if( con != null ){
                try {
                    con.close();
                } catch (SQLException e) {
                    System.err.println("データベース切断時にエラーが発生しました。");
                    e.printStackTrace();
                }
            }
        }

    }

    /*
     * キーボードから入力された値をStringで返す
     *         引数：なし
     *         戻り値：入力された文字列
     */
    private static String keyIn() {
        String line = null;
        try {
            BufferedReader key = new BufferedReader(new InputStreamReader(
                System.in));
            line = key.readLine();
        } catch (IOException e) {

        }
        return line;
    }

}