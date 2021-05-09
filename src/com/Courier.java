package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Courier_dt;

public class Courier {
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/gadgetbadgetdb", "root", "hashini123");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}
	
	public String readItems() {
		String output = "";
		output = "<table border='1'><tr><th>Courier ID</th><th>Name</th>"+
		"<th>Tel No</th>"+
		"<th>Company</th>"+
		"<th>Vehical</th>"+
		"<th>Email</th></tr>";
		try {
			Connection con = connect();

			if (con == null) {
				// return "Error while connecting to the database for updating";
			}
			String sql = "select * from courier";

			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {

				Courier_dt r = new Courier_dt();

				r.setId(rs.getInt(1));
				r.setName(rs.getString(2));
				r.setTelno(rs.getInt(3));
				r.setCompany(rs.getString(4));
				r.setVehical(rs.getString(5));
				r.setEmail(rs.getString(6));

				// Add into the html table
				output += "<tr><td>" + rs.getInt(1) + "</td>";
				output += "<td>" + rs.getString(2) + "</td>";
				output += "<td>" + rs.getInt(3) + "</td>";
				output += "<td>" + rs.getString(4) + "</td>";
				output += "<td>" + rs.getString(5) + "</td>";
				output += "<td>" + rs.getString(6) + "</td></tr>";
				
				// Add into the html table
				 output += "<tr><td><input id='hidCourierIDUpdate'"
				 		+ "name='hidCourierIDUpdate'+type='hidden' value='" + id + "'>"
				 + id + "</td>";
				 output += "<td>" + name + "</td>";
				 output += "<td>" + telno + "</td>";
				 output += "<td>" + company + "</td>"; 
				 output += "<td>" + vehical + "</td>"; 
				 output += "<td>" + email + "</td>"; 
				
				
				// buttons
				 output += "<td><input name='btnUpdate'
				  type='button' value='Update'
				  class='btnUpdate btn btn-secondary'></td>"
				  + "<td><input name='btnRemove'
				  type='button' value='Remove'
				  class='btnRemove btn btn-danger'
				  data-itemid='"
				  + itemID + "'>" + "</td></tr>";
			}
			
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			System.out.println(e);
		}

		return output;
	}
	
	public String insertItem(String code, String name,
			 String price, String desc)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for inserting.";
			 }
			 // create a prepared statement
			 String query = " insert into items
			 (`itemID`,`itemCode`,`itemName`,`itemPrice`,`itemDesc`)"
			
			+ " values (?, ?, ?, ?, ?)";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, code);
			 preparedStmt.setString(3, name);
			 preparedStmt.setDouble(4, Double.parseDouble(price));
			 preparedStmt.setString(5, desc);
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":
			 \"Error while inserting the item.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String updateItem(String ID, String code, String name,
			 String price, String desc)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for updating.";
			 }
			 // create a prepared statement
			 String query = "UPDATE items SET
			 itemCode=?,itemName=?,itemPrice=?,itemDesc=? WHERE itemID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setString(1, code);
			 preparedStmt.setString(2, name);
			 preparedStmt.setDouble(3, Double.parseDouble(price));
			 preparedStmt.setString(4, desc);
			 preparedStmt.setInt(5, Integer.parseInt(ID)); 
			
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":
			 \"Error while updating the item.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
			public String deleteItem(String itemID)
			 {
			 String output = "";
			 try
			 {
			 Connection con = connect();
			 if (con == null)
			 {
			 return "Error while connecting to the database for deleting.";
			 }
			 // create a prepared statement
			 String query = "delete from items where itemID=?";
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(itemID));
			 // execute the statement
			 preparedStmt.execute();
			 con.close();
			 String newItems = readItems();
			 output = "{\"status\":\"success\", \"data\": \"" +
			 newItems + "\"}";
			 }
			 catch (Exception e)
			 {
			 output = "{\"status\":\"error\", \"data\":\"Error while deleting the item.\"}";
			 System.err.println(e.getMessage());
			 }
			 return output;
			 }
	
}
