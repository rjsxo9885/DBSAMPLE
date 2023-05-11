package dbSample.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dbSample. entity.Country ;
import dbSample.util.DatabaseManager;

public class CoutryDAO {
    private PreparedStatement pstmt;
    private ResultSet rs;

    public List<Country> getCountryFromName(String name){

        List<Country> results = new ArrayList<Country>();

        try {
            Connection con = DatabaseManager.getConnection();


            String sql = "select * from country where Name LIKE ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name + "%");
            rs = pstmt.executeQuery();

            while(rs.next()) {
                Country country = new Country();
                country.setName(rs.getString("name"));
                country.setPopulation(rs.getInt("Population"));

                results.add(country);


            }



        }catch(ClassCastException e) {
            e.printStackTrace();
        }catch(SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO 自動生成された catch ブロック
            e.printStackTrace();
        }finally{
            if( rs != null ){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if( pstmt != null ){
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseManager.close();
        }
        return results;
    }

}
