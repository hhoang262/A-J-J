package com.hoangha.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class AccountRepository {
	
	@Value("${sqlUser}")
	String userMySql;
	
	@Value("${sqlPassword}")
	String passwordMySql;
	
	@Value("${databaseName}")
	String databaseName;
	
	String driver = "com.mysql.jdbc.Driver";
	String connection = "jdbc:mysql://localhost:3306/"+databaseName;
	
	public String registerUser(String username, String password) throws SQLException, ClassNotFoundException {
		Class.forName(driver);
		Connection con = DriverManager.getConnection(connection, userMySql, passwordMySql);
		Statement st = con.createStatement();
		
		try{
			String registerUserQuery ="INSERT INTO user (ID, username, password_hash, password, location) "
					+ "VALUES ('012345','"+username+"','hha6','"+password+"', 'hochiminhcity')";
			ResultSet rs = st.executeQuery(registerUserQuery);
			if(rs.rowInserted()) {
				return "Create account successfully!";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		finally {
			con.close();
		}
		return "Error when create account!";		
	}
}
