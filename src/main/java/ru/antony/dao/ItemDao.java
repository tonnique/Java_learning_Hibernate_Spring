package ru.antony.dao;

import java.sql.SQLException;
import java.util.Collection;

import ru.antony.model.Item;

public interface ItemDao {
    public Item addItem(Item item) throws SQLException, Exception;
    public void updateItem(Item item) throws SQLException, Exception;
    public Item getItemById(Long id) throws SQLException, Exception;
    public Collection<Item> getAllItems() throws SQLException, Exception;
    public void deleteItem(Item item) throws SQLException, Exception;
}
