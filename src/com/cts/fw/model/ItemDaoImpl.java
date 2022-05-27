package com.cts.fw.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDaoImpl implements ItemDao {
	
	
	@Override
	public void create(Item item) throws SQLException {
		Connection con = ConnectionFactory.retrieveConnection();
		PreparedStatement st = con.prepareStatement("INSERT INTO Item VALUES(?,?,?,?,?)");
		st.setInt(1, item.getId());
		st.setString(2, item.getCategory());
		st.setString(3, item.getName());
		st.setDouble(4, item.getPrice());
		st.setBytes(5, item.getPicture());
		int no=st.executeUpdate();
		System.out.println(no+" row(s) inserted");
	}
	@Override
	public List<Item> read() throws SQLException {
		Connection con = ConnectionFactory.retrieveConnection();
		ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Item");
		List<Item> items=new ArrayList<>();
		while(rs.next())
		{
			Item item=new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBytes(5));
			items.add(item);
		}
		return items;
	}
	@Override
	public Item read(Integer id) throws SQLException {
		Connection con = ConnectionFactory.retrieveConnection();
		PreparedStatement st = con.prepareStatement("SELECT * FROM Item WHERE id=?");
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		Item item=null;
		if(rs.next()) {
			item=new Item(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getBytes(5));
		}
		return item;
	}
	@Override
	public void update(Item item) throws SQLException {
		Connection con = ConnectionFactory.retrieveConnection();
		PreparedStatement st = con.prepareStatement("UPDATE Item SET category=?, name=?, price=?, picture=? WHERE id=?");

		st.setString(1, item.getCategory());
		st.setString(2, item.getName());
		st.setDouble(3, item.getPrice());
		st.setBytes(4, item.getPicture());
		st.setInt(5, item.getId());
		
		int no=st.executeUpdate();
		System.out.println(no+" row(s) updated");
	}
	@Override
	public void delete(Integer id) throws SQLException {
		Connection con = ConnectionFactory.retrieveConnection();
		PreparedStatement st = con.prepareStatement("DELETE FROM Item WHERE id=?");
		st.setInt(1, id);
		
		int no=st.executeUpdate();
		System.out.println(no+" row(s) deleted");
	}
	
	public List<String> retrieveCategories() throws SQLException
	{
		Connection con = ConnectionFactory.retrieveConnection();
		ResultSet rs = con.createStatement().executeQuery("SELECT DISTINCT category FROM Item ORDER BY category ASC");
		List<String> categories=new ArrayList<>();
		while(rs.next())
		{
			categories.add(rs.getString(1));
		}
		return categories;
	}
	
}
