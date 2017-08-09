package ru.antony;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ru.antony.dao.WarehouseDao;
import ru.antony.model.Warehouse;


@ContextConfiguration("classpath:context.xml")
public class TestWarehouse extends AbstractJUnit4SpringContextTests {
    @Autowired
    private WarehouseDao warehouseDao;

    @Test
    public void testInsertFind() {
        // Создать тестовый объект
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress("ul. Moskovskaya, 1");
        try {
            // Сохранить тестовый объект в базе данных
            Warehouse w = warehouseDao.addWarehouse(warehouse);
            // Вытащить тестовый объект из базы данных
            Warehouse warehouseFromDb = warehouseDao.getWarehouseById(w.getId());
            // Сравнить вытащенный объект из базы данных с тестовым объектом
            assertEquals(warehouse, warehouseFromDb);
            // Удалить тестовый объект из базы данных
            warehouseDao.deleteWarehouse(warehouseFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertFindUpdateDelete() {
        // Создать тестовый объект
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress("ul. Moskovskaya, 1");
        try {
            // Сохранить тестовый объект в базе данных
            Warehouse w = warehouseDao.addWarehouse(warehouse);
            // Вытащить тестовый объект из базы данных
            Warehouse warehouseFromDb = warehouseDao.getWarehouseById(w.getId());
            //Обновить в вытащенном объекте поле address
            warehouseFromDb.setAddress("ul. Leningradskaya, 4");
            // Записать обновленый тестовый объект в базу данных
            warehouseDao.updateWarehouse(warehouseFromDb);
            // После обновления повторно вытащить тестовый объект из базы данных
            Warehouse updatedWarehouseFromDb = warehouseDao.getWarehouseById(warehouseFromDb.getId());
            // Сравнить тестовый обновленный объект с повторно вытащенным
            assertEquals(warehouseFromDb, updatedWarehouseFromDb);
            // Удалить тестовый объект из базы данных
            warehouseDao.deleteWarehouse(updatedWarehouseFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertFindDelete() {
        // Создать тестовый объект
        Warehouse warehouse = new Warehouse();
        warehouse.setAddress("test message");
        try {
            // Сохранить тестовый объект в базе данных
            Warehouse w = warehouseDao.addWarehouse(warehouse);
            // Вытащить тестовый объект из базы данных
            Warehouse warehouseToDelete = warehouseDao.getWarehouseById(w.getId());
            // Удалить тестовый объект из базы данных
            warehouseDao.deleteWarehouse(warehouseToDelete);
            // Найти удаленный объект в базе данных
            Warehouse warehouseAfterDeleting = warehouseDao.getWarehouseById(w.getId());
            // Сравнить вытащенный объект после удаления из базы данных на null
            assertNull(warehouseAfterDeleting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
