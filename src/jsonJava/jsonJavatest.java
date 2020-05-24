package jsonJava;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class jsonJavatest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException {
		// TODO Auto-generated method stub

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Business", "root", "root");
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");

		ArrayList<jsonData> al = new ArrayList<jsonData>();
		while(rs.next()){

			jsonData jd = new jsonData();

			jd.setCourseName(rs.getString(1));
			String rowOne = jd.getCourseName();

			jd.setPurchasedDate(rs.getString(2));
			String rowTwo = jd.getPurchasedDate();

			jd.setAmount(rs.getInt(3));
			int rowThree = 	jd.getAmount();

			jd.setLocation(rs.getString(4));
			String rowFour =jd.getLocation();

			al.add(jd);

			System.out.println(rowOne);
			System.out.println(rowTwo);
			System.out.println(rowThree);
			System.out.println(rowFour);	
		}
		for (int i=0; i<al.size(); i++) {
			ObjectMapper oj = new ObjectMapper();
			oj.writeValue(new File("C:\\Users\\vimandavilli\\eclipse-workspace\\jsonJava\\jsonformat" + i + " .json"),al.get(i) );
		}


		//con.close();

	}

}
