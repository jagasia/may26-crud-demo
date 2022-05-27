package com.cts.fw.model;

import java.sql.SQLException;
import java.util.List;

public interface ItemDao {

	void create(Item item) throws SQLException;

	List<Item> read() throws SQLException;

	Item read(Integer id) throws SQLException;

	void update(Item item) throws SQLException;

	void delete(Integer id) throws SQLException;

	List<String> retrieveCategories() throws SQLException;
}