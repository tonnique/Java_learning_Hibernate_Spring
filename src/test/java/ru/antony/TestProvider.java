package ru.antony;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import ru.antony.dao.ItemDao;
import ru.antony.dao.ProviderDao;
import ru.antony.model.Item;
import ru.antony.model.Provider;


@ContextConfiguration("classpath:context.xml")
public class TestProvider extends AbstractJUnit4SpringContextTests {
    @Autowired
    private ProviderDao providerDao;
    @Autowired
    private ItemDao itemDao;

    @Test
    public void testInsertProviderWithItems() {
        // Создать тестовый объект Provider
        Provider provider = new Provider();
        provider.setName("test provider");

        // Создать тестовый объекты Item
        Set<Item> items = new HashSet<Item>();

        Item item1 = new Item();
        item1.setName("item1");
        Item item2 = new Item();
        item2.setName("item2");
        Item item3 = new Item();
        item3.setName("item3");
        Item item4 = new Item();
        item4.setName("item4");
        Item item5 = new Item();
        item5.setName("item5");

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        provider.setItems(items);

        try {
            // Сохранить тестовый объект Provider в базе данных
            Provider p = providerDao.addProvider(provider);
            // Вытащить тестовый объект из базы данных
            Provider providerFromDb = providerDao.getProviderById(p.getId());
            // Сравнить вытащенный объект из базы данных с тестовым объектом
            assertEquals(provider, providerFromDb);
            // Вытащить из вытащенного объекта Provider набор объектов Item
            Set<Item> itemsFromDb = provider.getItems();
            // Пройти по каждому объекту Item и сравнить с исходными
            for (Item itemFromDb : itemsFromDb) {
                // Проверить входит ли вытащенный объект item из базы данных в
                // набор items тестового объекта Provider
                assertEquals(items.contains(itemFromDb), true);
            }
            // Удалить тестовый объект Provider из базы данных
            providerDao.deleteProvider(providerFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertFind() {
        // Создать тестовый объект
        Provider provider = new Provider();
        provider.setName("test provider");
        try {
            // Сохранить тестовый объект в базе данных
            Provider p = providerDao.addProvider(provider);
            // Вытащить тестовый объект из базы данных
            Provider providerFromDb = providerDao.getProviderById(p.getId());
            // Сравнить вытащенный объект из базы данных с тестовым объектом
            assertEquals(provider, providerFromDb);
            // Удалить тестовый объект из базы данных
            providerDao.deleteProvider(providerFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInsertFindUpdate() {
        // Создать тестовый объект
        Provider provider = new Provider();
        provider.setName("test provider");
        try {
            // Сохранить тестовый объект в базе данных
            Provider p = providerDao.addProvider(provider);
            // Вытащить тестовый объект из базы данных
            Provider providerFromDb = providerDao.getProviderById(p.getId());
            // Обновить в вытащенном объекте поле name
            providerFromDb.setName("other provider");
            // Записать обновленый тестовый объект в базу данных
            providerDao.updateProvider(providerFromDb);
            // После обновления повторно вытащить тестовый объект из базы данных
            Provider updatedProviderFromDb = providerDao.getProviderById(providerFromDb.getId());
            // Сравнить тестовый обновленный объект с повторно вытащенным
            assertEquals(providerFromDb, updatedProviderFromDb);
            // Удалить тестовый объект из базы данных
            providerDao.deleteProvider(updatedProviderFromDb);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testDelete() {
        // Создать тестовый объект
        Provider provider = new Provider();
        provider.setName("test provider");
        try {
            // Сохранить тестовый объект в базе данных
            Provider p = providerDao.addProvider(provider);
            // Вытащить тестовый объект из базы данных
            Provider providerFromDb = providerDao.getProviderById(p.getId());
            // Удалить тестовый объект из базы данных
            providerDao.deleteProvider(providerFromDb);
            // Найти удаленный объект в базе данных
            Provider providerAfterDeleting = providerDao.getProviderById(providerFromDb.getId());
            // Сравнить вытащенный объект после удаления из базы данных на null
            assertNull(providerAfterDeleting);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}