package Kids2Kids;

import java.util.*;
import org.overture.codegen.runtime.*;

@SuppressWarnings("all")
public class StoreTest {
  private void assertTrue(final Boolean cond) {

    return;
  }

  private void assertEqual(final Object expected, final Object actual) {

    return;
  }

  private void testDate() {

    Date date = null;
    date = new Date(29L, 2L, 2020L);
    assertEqual(date.getDate(), "29/02/2020");
    date = new Date(28L, 2L, 2019L);
    assertEqual(date.getDate(), "28/02/2019");
    date = new Date(4L, 6L, 5789L);
    assertEqual(date.getDate(), "04/06/5789");
    date = new Date(8L, 3L, 2019L);
    assertEqual(date.getDate(), "08/03/2019");
  }

  private void testCreateStore() {

    Store store = new Store("Kids2Kids", "UK");
    assertEqual(store.getName(), "Kids2Kids");
    assertEqual(store.getCountry(), "UK");
    assertEqual(store.getCash(), 0L);
  }

  private void testAddProductClass() {

    Store store = new Store("Kids2Kids", "UK", 1000L);
    ProductClass pc1 = new ProductClass("food");
    ProductClass pc2 = new ProductClass("toy");
    store.addProductClass(pc1);
    store.addProductClass(pc2);
    assertEqual(store.getNumberOfProductClasses(), 2L);
    Long exists1Counter_2 = 0L;
    VDMSet set_13 = store.getProductClasses();
    for (Iterator iterator_13 = set_13.iterator();
        iterator_13.hasNext() && (exists1Counter_2.longValue() < 2L);
        ) {
      ProductClass pc = ((ProductClass) iterator_13.next());
      if (Utils.equals(pc.getName(), "food")) {
        exists1Counter_2++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_2, 1L));

    Long exists1Counter_3 = 0L;
    VDMSet set_14 = store.getProductClasses();
    for (Iterator iterator_14 = set_14.iterator();
        iterator_14.hasNext() && (exists1Counter_3.longValue() < 2L);
        ) {
      ProductClass pc = ((ProductClass) iterator_14.next());
      if (Utils.equals(pc.getName(), "toy")) {
        exists1Counter_3++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_3, 1L));
  }

  private void testRemoveProductClass() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("food");
    ProductClass pc2 = new ProductClass("toy");
    Product p1 = new Product(pc1, "apple", 0.1, 0.2);
    Product p2 = new Product(pc2, "ball", 5L, 6L);
    Product p3 = new Product(pc1, "banana", 0.2, 0.3);
    store.addProductClass(pc1);
    store.addProductClass(pc2);
    store.addProduct(p1, 3L);
    store.addProduct(p2, 2L);
    store.addProduct(p3, 3L);
    assertEqual(store.getNumberOfProductClasses(), 2L);
    assertEqual(store.getNumberOfProducts(), 3L);
    store.removeProductClass("food");
    assertEqual(store.getNumberOfProductClasses(), 1L);
    assertEqual(store.getNumberOfProducts(), 1L);
    Boolean existsExpResult_5 = false;
    VDMSet set_15 = store.getProductClasses();
    for (Iterator iterator_15 = set_15.iterator();
        iterator_15.hasNext() && !(existsExpResult_5);
        ) {
      ProductClass pc = ((ProductClass) iterator_15.next());
      existsExpResult_5 = Utils.equals(pc.getName(), "food");
    }
    assertTrue(!(existsExpResult_5));

    Boolean existsExpResult_6 = false;
    VDMSet set_16 = MapUtil.dom(store.getStoreProducts());
    for (Iterator iterator_16 = set_16.iterator();
        iterator_16.hasNext() && !(existsExpResult_6);
        ) {
      Product p = ((Product) iterator_16.next());
      existsExpResult_6 = Utils.equals(p.cg_getClass().getName(), "food");
    }
    assertTrue(!(existsExpResult_6));
  }

  private void testAddProduct() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("toy");
    Product p1 = new Product(pc1, "doll", 5L, 6L);
    VDMMap products = MapUtil.map();
    store.addProductClass(pc1);
    store.addProduct(p1, 3L);
    assertEqual(store.getNumberOfProducts(), 1L);
    Long exists1Counter_4 = 0L;
    VDMSet set_17 = MapUtil.dom(store.getStoreProducts());
    for (Iterator iterator_17 = set_17.iterator();
        iterator_17.hasNext() && (exists1Counter_4.longValue() < 2L);
        ) {
      Product p = ((Product) iterator_17.next());
      if (Utils.equals(p.getName(), "doll")) {
        exists1Counter_4++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_4, 1L));

    products = store.getStoreProducts();
    for (Iterator iterator_39 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_39.hasNext();
        ) {
      Product p = (Product) iterator_39.next();
      if (Utils.equals(p.getName(), p1.getName())) {
        assertEqual(3L, ((Number) Utils.get(products, p)));
      }
    }
  }

  private void testRemoveProduct() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("toy");
    Product p1 = new Product(pc1, "doll", 5L, 6L);
    Product p2 = new Product(pc1, "ball", 5L, 6L);
    store.addProductClass(pc1);
    store.addProduct(p1, 3L);
    store.addProduct(p2, 2L);
    store.removeProduct("ball");
    Boolean existsExpResult_7 = false;
    VDMSet set_18 = MapUtil.dom(store.getStoreProducts());
    for (Iterator iterator_18 = set_18.iterator();
        iterator_18.hasNext() && !(existsExpResult_7);
        ) {
      Product p = ((Product) iterator_18.next());
      existsExpResult_7 = Utils.equals(p.getName(), "ball");
    }
    assertTrue(!(existsExpResult_7));

    store.removeProduct("doll");
    Boolean existsExpResult_8 = false;
    VDMSet set_19 = MapUtil.dom(store.getStoreProducts());
    for (Iterator iterator_19 = set_19.iterator();
        iterator_19.hasNext() && !(existsExpResult_8);
        ) {
      Product p = ((Product) iterator_19.next());
      existsExpResult_8 = Utils.equals(p.getName(), "doll");
    }
    assertTrue(!(existsExpResult_8));

    assertEqual(store.getNumberOfProductClasses(), 1L);
    assertEqual(store.getNumberOfProducts(), 0L);
  }

  private void testAddStock() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("food");
    Product p1 = new Product(pc1, "apple", 0.1, 0.2);
    Product p2 = new Product(pc1, "banana", 0.2, 0.3);
    VDMMap products = MapUtil.map();
    store.addProductClass(pc1);
    store.addProduct(p1, 2L);
    store.addStock(p1, 4L);
    store.addProduct(p2, 0L);
    store.addStock(p2, 3L);
    products = store.getStoreProducts();
    for (Iterator iterator_40 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_40.hasNext();
        ) {
      Product p = (Product) iterator_40.next();
      if (Utils.equals(p.getName(), p1.getName())) {
        assertEqual(6L, ((Number) Utils.get(products, p)));
      }
    }
    for (Iterator iterator_41 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_41.hasNext();
        ) {
      Product p = (Product) iterator_41.next();
      if (Utils.equals(p.getName(), p2.getName())) {
        assertEqual(3L, ((Number) Utils.get(products, p)));
      }
    }
  }

  private void testBuyFromClient() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("toy");
    Product p1 = new Product(pc1, "doll", 0.1, 0.2);
    Product p2 = new Product(pc1, "cube", 0.2, 0.3);
    VDMMap bought = MapUtil.map(new Maplet(p1, 2L), new Maplet(p2, 3L));
    Client client = new Client("maria");
    Date date = new Date(30L, 12L, 2018L);
    PurchaseFromClient purchase = null;
    VDMMap products = MapUtil.map();
    Number cash = store.getCash();
    store.addProductClass(pc1);
    store.addProduct(p1, 1L);
    store.addProduct(p2, 0L);
    purchase = store.buy(Utils.copy(bought), client, date);
    assertEqual(purchase.getGrandTotal(), 0.8);
    assertEqual(store.getNumberOfPurchases(), 1L);
    Long exists1Counter_5 = 0L;
    VDMSet set_20 = store.getPurchases();
    for (Iterator iterator_20 = set_20.iterator();
        iterator_20.hasNext() && (exists1Counter_5.longValue() < 2L);
        ) {
      Purchase p = ((Purchase) iterator_20.next());
      Boolean orResult_5 = false;

      if (!(p instanceof PurchaseFromClient)) {
        orResult_5 = true;
      } else {
        orResult_5 = Utils.equals(((PurchaseFromClient) p).getClient().getName(), "maria");
      }

      if (orResult_5) {
        exists1Counter_5++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_5, 1L));

    Long exists1Counter_6 = 0L;
    VDMSet set_21 = store.getPurchases();
    for (Iterator iterator_21 = set_21.iterator();
        iterator_21.hasNext() && (exists1Counter_6.longValue() < 2L);
        ) {
      Purchase p = ((Purchase) iterator_21.next());
      if (Utils.equals(p.getDate().getDate(), "30/12/2018")) {
        exists1Counter_6++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_6, 1L));

    Long exists1Counter_7 = 0L;
    VDMSet set_22 = store.getPurchases();
    for (Iterator iterator_22 = set_22.iterator();
        iterator_22.hasNext() && (exists1Counter_7.longValue() < 2L);
        ) {
      Purchase p = ((Purchase) iterator_22.next());
      if (Utils.equals(
          SetUtil.intersect(SetUtil.set(p1, p2), MapUtil.dom(p.getProducts())).size(), 2L)) {
        exists1Counter_7++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_7, 1L));

    products = store.getStoreProducts();
    for (Iterator iterator_42 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_42.hasNext();
        ) {
      Product p = (Product) iterator_42.next();
      Boolean orResult_6 = false;

      if (Utils.equals(p.getName(), p1.getName())) {
        orResult_6 = true;
      } else {
        orResult_6 = Utils.equals(p.getName(), p2.getName());
      }

      if (orResult_6) {
        assertEqual(3L, ((Number) Utils.get(products, p)));
      }
    }
    assertEqual(store.getCash(), cash.doubleValue() - 0.8);
  }

  private void testBuyFromSupplier() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("toy");
    ProductClass pc2 = new ProductClass("food");
    Product p1 = new Product(pc1, "doll", 0.1, 0.2);
    Product p2 = new Product(pc2, "apple", 0.1, 0.2);
    VDMMap bought = MapUtil.map(new Maplet(p1, 2L), new Maplet(p2, 3L));
    Supplier supplier = new Supplier("joao");
    Date date = new Date(30L, 12L, 2018L);
    PurchaseFromSupplier purchase = null;
    VDMMap products = MapUtil.map();
    Number cash = store.getCash();
    store.addProductClass(pc1);
    store.addProductClass(pc2);
    store.addProduct(p1, 1L);
    store.addProduct(p2, 0L);
    products = store.getStoreProducts();
    assertTrue(Utils.equals(((Number) Utils.get(products, p1)), 1L));
    assertTrue(Utils.equals(((Number) Utils.get(products, p2)), 0L));
    purchase = store.buy(Utils.copy(bought), supplier, date);
    assertEqual(purchase.getGrandTotal(), 0.5);
    assertTrue(Utils.equals(store.getNumberOfPurchases(), 1L));
    Long exists1Counter_8 = 0L;
    VDMSet set_23 = store.getPurchases();
    for (Iterator iterator_23 = set_23.iterator();
        iterator_23.hasNext() && (exists1Counter_8.longValue() < 2L);
        ) {
      Purchase p = ((Purchase) iterator_23.next());
      Boolean orResult_7 = false;

      if (!(p instanceof PurchaseFromSupplier)) {
        orResult_7 = true;
      } else {
        orResult_7 = Utils.equals(((PurchaseFromSupplier) p).getSupplier().getName(), "joao");
      }

      if (orResult_7) {
        exists1Counter_8++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_8, 1L));

    Long exists1Counter_9 = 0L;
    VDMSet set_24 = store.getPurchases();
    for (Iterator iterator_24 = set_24.iterator();
        iterator_24.hasNext() && (exists1Counter_9.longValue() < 2L);
        ) {
      Purchase p = ((Purchase) iterator_24.next());
      if (Utils.equals(p.getDate().getDate(), "30/12/2018")) {
        exists1Counter_9++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_9, 1L));

    Long exists1Counter_10 = 0L;
    VDMSet set_25 = store.getPurchases();
    for (Iterator iterator_25 = set_25.iterator();
        iterator_25.hasNext() && (exists1Counter_10.longValue() < 2L);
        ) {
      Purchase p = ((Purchase) iterator_25.next());
      if (Utils.equals(
          SetUtil.intersect(SetUtil.set(p1, p2), MapUtil.dom(p.getProducts())).size(), 2L)) {
        exists1Counter_10++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_10, 1L));

    products = store.getStoreProducts();
    for (Iterator iterator_43 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_43.hasNext();
        ) {
      Product p = (Product) iterator_43.next();
      Boolean orResult_8 = false;

      if (Utils.equals(p.getName(), p1.getName())) {
        orResult_8 = true;
      } else {
        orResult_8 = Utils.equals(p.getName(), p2.getName());
      }

      if (orResult_8) {
        assertEqual(3L, ((Number) Utils.get(products, p)));
      }
    }
    assertEqual(store.getCash(), cash.doubleValue() - 0.5);
  }

  private void testSell() {

    Store store = new Store("Kids2Kids", "PT", 1000L);
    ProductClass pc1 = new ProductClass("toy");
    ProductClass pc2 = new ProductClass("food");
    Product p1 = new Product(pc1, "doll", 0.1, 0.2);
    Product p2 = new Product(pc2, "apple", 0.1, 0.2);
    VDMMap sold = MapUtil.map(new Maplet(p1, 1L), new Maplet(p2, 1L));
    Client client = new Client("joao");
    Date date = new Date(30L, 12L, 2018L);
    Sale sale = null;
    VDMMap products = MapUtil.map();
    Number cash = store.getCash();
    store.addProductClass(pc1);
    store.addProductClass(pc2);
    store.addProduct(p1, 5L);
    store.addProduct(p2, 5L);
    products = store.getStoreProducts();
    sale = store.sell(Utils.copy(sold), client, date);
    assertEqual(sale.getGrandTotal(), 0.4);
    assertTrue(Utils.equals(store.getNumberOfSales(), 1L));
    Long exists1Counter_11 = 0L;
    VDMSet set_26 = store.getSales();
    for (Iterator iterator_26 = set_26.iterator();
        iterator_26.hasNext() && (exists1Counter_11.longValue() < 2L);
        ) {
      Sale s = ((Sale) iterator_26.next());
      if (Utils.equals(s.getClient().getName(), "joao")) {
        exists1Counter_11++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_11, 1L));

    Long exists1Counter_12 = 0L;
    VDMSet set_27 = store.getSales();
    for (Iterator iterator_27 = set_27.iterator();
        iterator_27.hasNext() && (exists1Counter_12.longValue() < 2L);
        ) {
      Sale s = ((Sale) iterator_27.next());
      if (Utils.equals(s.getDate().getDate(), "30/12/2018")) {
        exists1Counter_12++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_12, 1L));

    Long exists1Counter_13 = 0L;
    VDMSet set_28 = store.getSales();
    for (Iterator iterator_28 = set_28.iterator();
        iterator_28.hasNext() && (exists1Counter_13.longValue() < 2L);
        ) {
      Sale s = ((Sale) iterator_28.next());
      if (Utils.equals(
          SetUtil.intersect(SetUtil.set(p1, p2), MapUtil.dom(s.getProducts())).size(), 2L)) {
        exists1Counter_13++;
      }
    }
    assertTrue(Utils.equals(exists1Counter_13, 1L));

    products = store.getStoreProducts();
    for (Iterator iterator_44 = MapUtil.dom(Utils.copy(products)).iterator();
        iterator_44.hasNext();
        ) {
      Product p = (Product) iterator_44.next();
      Boolean orResult_9 = false;

      if (Utils.equals(p.getName(), p1.getName())) {
        orResult_9 = true;
      } else {
        orResult_9 = Utils.equals(p.getName(), p2.getName());
      }

      if (orResult_9) {
        assertEqual(4L, ((Number) Utils.get(products, p)));
      }
    }
    assertEqual(store.getCash(), cash.doubleValue() + 0.4);
  }

  public static void main() {

    StoreTest testSuite = new StoreTest();
    testSuite.testDate();
    testSuite.testCreateStore();
    testSuite.testAddProductClass();
    testSuite.testRemoveProductClass();
    testSuite.testAddProduct();
    testSuite.testRemoveProduct();
    testSuite.testAddStock();
    testSuite.testBuyFromClient();
    testSuite.testBuyFromSupplier();
    testSuite.testSell();
  }

  public StoreTest() {}

  public String toString() {

    return "StoreTest{}";
  }
}
