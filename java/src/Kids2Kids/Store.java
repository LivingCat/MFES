package Kids2Kids;

import java.util.*;

import org.overture.codegen.runtime.*;

import Interface.utils;

@SuppressWarnings("all")
public class Store {
	private VDMSet sale;
	private VDMSet purchase;
	private VDMMap product;
	private String name;
	private String country;
	private VDMSet classes;
	private Number cash;

	public void cg_init_Store_2(final String n, final String c, final Number csh) {

		name = n;
		country = c;
		product = MapUtil.map();
		sale = SetUtil.set();
		purchase = SetUtil.set();
		classes = SetUtil.set();
		cash = csh;
		return;
	}

	public void cg_init_Store_1(final String n, final String c) {

		name = n;
		country = c;
		product = MapUtil.map();
		sale = SetUtil.set();
		purchase = SetUtil.set();
		classes = SetUtil.set();
		cash = 0L;
		return;
	}

	public Store(final String n, final String c) {

		cg_init_Store_1(n, c);
	}

	public Store(final String n, final String c, final Number csh) {

		cg_init_Store_2(n, c, csh);
	}

	private Number calculateGrandTotalPurchase(final VDMMap productsList) {

		Number sum = 0L;
		for (Iterator iterator_31 = MapUtil.dom(Utils.copy(productsList)).iterator(); iterator_31.hasNext();) {
			Product p = (Product) iterator_31.next();
			sum = sum.doubleValue() + p.getBuyPrice().doubleValue() * ((Number) Utils.get(productsList, p)).longValue();
		}
		return sum;
	}

	private Number calculateGrandTotalSale(final VDMMap productsList) {

		Number sum = 0L;
		for (Iterator iterator_32 = MapUtil.dom(Utils.copy(productsList)).iterator(); iterator_32.hasNext();) {
			Product p = (Product) iterator_32.next();
			sum = sum.doubleValue()
					+ p.getSellPrice().doubleValue() * ((Number) Utils.get(productsList, p)).longValue();
		}
		return sum;
	}

	public PurchaseFromClient buy(final VDMMap products, final Client client, final Date date) {

		Purchase newPurchase = new PurchaseFromClient(client, Utils.copy(products), date);
		VDMMap productsCpy = Utils.copy(products);
		purchase = SetUtil.union(Utils.copy(purchase), SetUtil.set(newPurchase));
		for (Iterator iterator_33 = MapUtil.dom(Utils.copy(products)).iterator(); iterator_33.hasNext();) {
			Product p = (Product) iterator_33.next();
			Utils.mapSeqUpdate(productsCpy, p,
					((Number) Utils.get(product, p)).longValue() + ((Number) Utils.get(products, p)).longValue());
		}
		product = MapUtil.override(Utils.copy(product), Utils.copy(productsCpy));
		cash = cash.doubleValue() - calculateGrandTotalPurchase(Utils.copy(products)).doubleValue();
		return ((PurchaseFromClient) newPurchase);
	}

	public PurchaseFromSupplier buy(final VDMMap products, final Supplier supplier, final Date date) {

		Purchase newPurchase = new PurchaseFromSupplier(supplier, Utils.copy(products), date);
		VDMMap productsCpy = Utils.copy(products);
		purchase = SetUtil.union(Utils.copy(purchase), SetUtil.set(newPurchase));
		for (Iterator iterator_34 = MapUtil.dom(Utils.copy(products)).iterator(); iterator_34.hasNext();) {
			Product p = (Product) iterator_34.next();
			Utils.mapSeqUpdate(productsCpy, p,
					((Number) Utils.get(product, p)).longValue() + ((Number) Utils.get(products, p)).longValue());
		}
		product = MapUtil.override(Utils.copy(product), Utils.copy(productsCpy));
		cash = cash.doubleValue() - calculateGrandTotalPurchase(Utils.copy(products)).doubleValue();
		return ((PurchaseFromSupplier) newPurchase);
	}

	public Sale sell(final VDMMap products, final Client client, final Date date) {

		Sale newSale = new Sale(client, Utils.copy(products), date);
		VDMMap productsCpy = Utils.copy(products);
		sale = SetUtil.union(Utils.copy(sale), SetUtil.set(newSale));
		for (Iterator iterator_35 = MapUtil.dom(Utils.copy(products)).iterator(); iterator_35.hasNext();) {
			Product p = (Product) iterator_35.next();
			Utils.mapSeqUpdate(productsCpy, p,
					((Number) Utils.get(product, p)).longValue() - ((Number) Utils.get(products, p)).longValue());
		}
		product = MapUtil.override(Utils.copy(product), Utils.copy(productsCpy));
		cash = cash.doubleValue() + calculateGrandTotalSale(Utils.copy(products)).doubleValue();
		return newSale;
	}

	public void addProduct(final Product p, final Number q) {

		VDMMap newProduct = MapUtil.map(new Maplet(p, q));
		product = MapUtil.override(Utils.copy(product), Utils.copy(newProduct));
	}

	public void removeProduct(final String pName) {

		Product productCopy = null;
		for (Iterator iterator_36 = MapUtil.dom(Utils.copy(product)).iterator(); iterator_36.hasNext();) {
			Product p = (Product) iterator_36.next();
			if (Utils.equals(p.getName(), pName)) {
				productCopy = p;
			}
		}
		product = MapUtil.domResBy(SetUtil.set(productCopy), Utils.copy(product));
	}

	public void addStock(final Product p, final Number q) {

		Number q1 = ((Number) Utils.get(product, p)).longValue() + q.longValue();
		VDMMap newProduct = MapUtil.map(new Maplet(p, q1));
		product = MapUtil.override(Utils.copy(product), Utils.copy(newProduct));
	}

	public void addProductClass(final ProductClass pc) {

		classes = SetUtil.union(Utils.copy(classes), SetUtil.set(pc));
	}

	public void removeProductClass(final String pcName) {

		ProductClass pcCpy = null;
		VDMSet productsToDelete = SetUtil.set();
		for (Iterator iterator_37 = classes.iterator(); iterator_37.hasNext();) {
			ProductClass pc = (ProductClass) iterator_37.next();
			if (Utils.equals(pc.getName(), pcName)) {
				pcCpy = pc;
			}
		}
		classes = SetUtil.diff(Utils.copy(classes), SetUtil.set(pcCpy));
		for (Iterator iterator_38 = MapUtil.dom(Utils.copy(product)).iterator(); iterator_38.hasNext();) {
			Product p = (Product) iterator_38.next();
			if (Utils.equals(p.cg_getClass().getName(), pcName)) {
				productsToDelete = SetUtil.union(Utils.copy(productsToDelete), SetUtil.set(p));
			}
		}
		product = MapUtil.domResBy(Utils.copy(productsToDelete), Utils.copy(product));
	}

	public Number getNumberOfPurchases() {

		return purchase.size();
	}

	public Number getNumberOfClientPurchases() {

		Number n = 0L;
		for (Iterator iterator_39 = purchase.iterator(); iterator_39.hasNext();) {
			Purchase p = (Purchase) iterator_39.next();
			if (p instanceof PurchaseFromClient) {
				n = n.longValue() + 1L;
			}
		}
		return n;
	}

	public Number getNumberOfSupplierPurchases() {

		Number n = 0L;
		for (Iterator iterator_40 = purchase.iterator(); iterator_40.hasNext();) {
			Purchase p = (Purchase) iterator_40.next();
			if (p instanceof PurchaseFromSupplier) {
				n = n.longValue() + 1L;
			}
		}
		return n;
	}

	public Number getNumberOfSales() {

		return sale.size();
	}

	public Number getNumberOfProductClasses() {

		return classes.size();
	}

	public Number getNumberOfProducts() {

		return MapUtil.dom(Utils.copy(product)).size();
	}

	public VDMMap getStoreProducts() {

		return Utils.copy(product);
	}

	public Number getCash() {

		return cash;
	}

	public VDMSet getSales() {

		return Utils.copy(sale);
	}

	public VDMSet getPurchases() {

		return Utils.copy(purchase);
	}

	public String getName() {

		return name;
	}

	public String getCountry() {

		return country;
	}

	public VDMSet getProductClasses() {

		return Utils.copy(classes);
	}

	public Number getAssets() {

		Number assets = 0L;
		for (Iterator iterator_41 = MapUtil.dom(Utils.copy(product)).iterator(); iterator_41.hasNext();) {
			Product p = (Product) iterator_41.next();
			assets = assets.doubleValue()
					+ p.getSellPrice().doubleValue() * ((Number) Utils.get(product, p)).longValue();
		}
		return assets;
	}

	public Number getNetSales() {

		Number netSales = 0L;
		for (Iterator iterator_42 = sale.iterator(); iterator_42.hasNext();) {
			Sale s = (Sale) iterator_42.next();
			netSales = netSales.doubleValue() + s.getGrandTotal().doubleValue();
		}
		return netSales;
	}

	public Number getCostOfGoodsSold() {

		Number costOfGoods = 0L;
		for (Iterator iterator_43 = sale.iterator(); iterator_43.hasNext();) {
			Sale s = (Sale) iterator_43.next();
			for (Iterator iterator_44 = MapUtil.dom(s.getProducts()).iterator(); iterator_44.hasNext();) {
				Product p = (Product) iterator_44.next();
				costOfGoods = costOfGoods.doubleValue()
						+ p.getBuyPrice().doubleValue() * ((Number) Utils.get(s.getProducts(), p)).longValue();
			}
		}
		return costOfGoods;
	}

	public Number getGrossProfit() {

		return getNetSales().doubleValue() - getCostOfGoodsSold().doubleValue();
	}

	public String generateReport() {

		throw new UnsupportedOperationException();
	}

	public Store() {
	}

	public String toString() {

		return "Store{" + "sale := " + Utils.toString(sale) + ", purchase := " + Utils.toString(purchase)
				+ ", product := " + Utils.toString(product) + ", name := " + Utils.toString(name) + ", country := "
				+ Utils.toString(country) + ", classes := " + Utils.toString(classes) + ", cash := "
				+ Utils.toString(cash) + "}";
	}

	public void seed() {
		ProductClass c = new ProductClass("food");
		Product p1 = new Product(c, "apple", 0.1, 0.2);
		Product p2 = new Product(c, "banana", 0.2, 0.3);
		addProductClass(c);
		addProduct(p1, 10);
		addProduct(p2, 5);

		Client client = new Client("Matilde Freilao");
		Supplier supplier = new Supplier("Catarina Ferreira");
		VDMMap productsToSell = new VDMMap();
		productsToSell.put(p1, 1);
		productsToSell.put(p2, 1);
		VDMMap productsToBuy = new VDMMap();
		productsToBuy.put(p1, 3);
		productsToBuy.put(p2, 4);
		Date date = utils.today();
		this.sell(productsToSell, client, date);
		this.buy(productsToBuy, supplier, date);
	}
}
