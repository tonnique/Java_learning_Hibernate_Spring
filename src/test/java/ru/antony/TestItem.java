package ru.antony;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ru.antony.dao.ItemDao;
import ru.antony.model.Item;


@ContextConfiguration("classpath:context.xml")
public class TestItem extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ItemDao itemDao;

    @Test
    public void testInsertFind() {
        // Создать тестовый объект
        Item item = new Item();
        item.setName("zheka");
        try {
            // Сохранить тестовый объект в базе данных
            Item i = itemDao.addItem(item);
            // Вытащить тестовый объект из базы данных
            Item itemFromDb = itemDao.getItemById(i.getId());
            // Сравнить вытащенный объект из базы данных с тестовым объектом
            assertEquals(item, itemFromDb);
            // Удалить тестовый объект из базы данных
            itemDao.deleteItem(itemFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertFindUpdate() {
        // Создать тестовый объект
        Item item = new Item();
        item.setName("zheka");
        try {
            // Сохранить тестовый объект в базе данных
            Item i = itemDao.addItem(item);
            // Вытащить тестовый объект из базы данных
            Item itemFromDb = itemDao.getItemById(i.getId());
            // Обновить в вытащенном объекте поле name
            itemFromDb.setName("vasya");
            // Записать обновленый тестовый объект в базу данных
            itemDao.updateItem(itemFromDb);
            // После обновления повторно вытащить тестовый объект из базы данных
            Item updatedItemFromDb = itemDao.getItemById(itemFromDb.getId());
            // Сравнить тестовый обновленный объект с повторно вытащенным
            assertEquals(itemFromDb, updatedItemFromDb);
            // Удалить тестовый объект из базы данных
            itemDao.deleteItem(updatedItemFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        // Создать тестовый объект
        Item item = new Item();
        item.setName("test message");
        try {
            // Сохранить тестовый объект в базе данных
            Item i = itemDao.addItem(item);
            // Вытащить тестовый объект из базы данных
            Item itemToDelete = itemDao.getItemById(i.getId());
            // Удалить тестовый объект из базы данных
            itemDao.deleteItem(itemToDelete);
            // Найти удаленный объект в базе данных
            Item itemAfterDeleting = itemDao.getItemById(i.getId());
            // Сравнить вытащенный объект после удаления из базы данных на null
            assertNull(itemAfterDeleting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}