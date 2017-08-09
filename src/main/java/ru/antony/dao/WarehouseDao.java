package ru.antony.dao;

import java.sql.SQLException;
import java.util.Collection;
import ru.antony.model.Warehouse;

public interface WarehouseDao {
    public Warehouse addWarehouse(Warehouse warehouse) throws SQLException, Exception;
    public void updateWarehouse(Warehouse warehouse) throws SQLException, Exception;
    public Warehouse getWarehouseById(Long id) throws SQLException, Exception;
    public Collection<Warehouse> getAllWarehouses() throws SQLException, Exception;
    public void deleteWarehouse(Warehouse warehouse) throws SQLException, Exception;
}