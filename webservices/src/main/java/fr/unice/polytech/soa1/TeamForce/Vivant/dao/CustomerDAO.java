package fr.unice.polytech.soa1.TeamForce.Vivant.dao;

import fr.unice.polytech.soa1.TeamForce.Vivant.business.*;

import javax.ejb.Singleton;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Singleton(name = "Customer-DAO-Mock")
public class CustomerDAO {

	private List<Customer> customers;
	private List<Order> orders;
	private List<Catalogue> catalogs;
	private List<Good> goods;
	
	public List<Customer> getCustomers() { return customers; }
	public void setCustomers(List<Customer> Customers) { this.customers = Customers; }
	public List<Order> getOrders() { return orders; }
	public void setOrders(List<Order> orders) {	this.orders = orders; }

	public CustomerDAO() { init(); }

	public Optional<Customer> findCustomerById(String id) {
		return getCustomers().stream().filter(c -> c.getId().equals(id)).findFirst();
	}
	
	public Optional<Catalogue> findCatalogueById(String id) {
		return getCatalogs().stream().filter(c -> c.getId().equals(id)).findFirst();
	}
	
	public Optional<Order> findOrderById(String id) {
		return getOrders().stream().filter(o -> o.getId().equals(id)).findFirst();
	}

	public Optional<Good> findGoodById(String id) {
		return getGoods().stream().filter(g -> g.getId().equals(id)).findFirst();
	}

	public String toString() {
		StringBuilder builder = new StringBuilder("Database: \n");
		for(Customer c: customers) {
			builder.append(
                String.format(
                        "[%s] %s - %s \n%s\n\n",
                        c.getId(),
                        c.getName(),
                        c.getDeliveryAddresses().toString(),
                        c.getInvoiceAddresses().toString()
                )
            );
		}
		return builder.toString();
	}

	private void init() {
		customers = new ArrayList<Customer>();
		orders = new ArrayList<Order>();
		catalogs = new ArrayList<Catalogue>();
		goods = new ArrayList<Good>();
		
		try(
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(".\\data\\customers.csv")
                    )
            );
            BufferedReader brO = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(".\\data\\orders.csv")
                    )
            );
            BufferedReader brA = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(".\\data\\addresses.csv")
                    )
            );
            BufferedReader brC = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(".\\data\\catalogs.csv")
                    )
            );
        ){
            String str;
			String line;
			String lineO;
			String lineA;
			String lineC;
			br.readLine();
			brA.readLine();
			brO.readLine();
			brC.readLine();
			while ((line=br.readLine())!=null && (lineA=brA.readLine())!=null && (lineO=brO.readLine())!=null && (lineC=brC.readLine())!=null){
				str = line+"|"+lineA;
				String[] tab = str.split("\\|");
				String[] tabOrders = lineO.split("\\|");
				String[] tabCata = lineC.split("\\|");
				
				if(catalogs.isEmpty()){
					ArrayList<String> goodsC = new ArrayList<String>();
					goodsC.add(tabCata[0]);
					catalogs.add(new Catalogue(tabCata[1], goodsC));
				}else{
					boolean b = false;
					for(int i = 0;i< catalogs.size();i++){
						if(catalogs.get(i).getId().equals(tabCata[1])){
							catalogs.get(i).addGoods(tabCata[0]);
							b = true;
						}
					}
					if(!b){
						ArrayList<String> goodsC = new ArrayList<String>();
						goodsC.add(tabCata[0]);
						catalogs.add(new Catalogue(tabCata[1], goodsC));
					}
				}
				
				Customer customer = new Customer(
                    tab[0],
                    tab[1],
                    tab[2],
                    tab[3],
                    tab[4],
                    new Address(
                        tab[5],
                        tab[6],
                        tab[7],
                        tab[8]
                    ),
                    new Address(
                        tab[5],
                        tab[6],
                        tab[7],
                        tab[8]
                    ),
                    new ArrayList<Order>()
                );

				customers.add(customer);
				Optional<Order> exOrder = findOrderById(tabOrders[0]);

				if(exOrder.isPresent()){
					Good good = new Good(tabOrders[1],tabOrders[3], "None");
					goods.add(good);
					exOrder.get().getGoods().add(new GoodOrder(Integer.valueOf(tabOrders[2]), good));
				}else{
					List<GoodOrder> goodsOrder = new ArrayList<GoodOrder>();
					Good goodTemp = new Good(tabOrders[1],tabOrders[3], "None");
					goods.add(goodTemp);
					goodsOrder.add(new GoodOrder(Integer.valueOf(tabOrders[2]), goodTemp));
					int pick = new Random().nextInt(Status.values().length);
				    Status status =  Status.values()[pick];
					Order newOrder = new Order(tabOrders[0],goodsOrder, status);
					orders.add(newOrder);
					if( customer.getOrders() != null){
						customer.getOrders().add(newOrder);
					}
				}
			}
		}		
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public List<Catalogue> getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(List<Catalogue> catalogs) {
		this.catalogs = catalogs;
	}
	public List<Good> getGoods() {
		return goods;
	}
	public void setGoods(List<Good> goods) {
		this.goods = goods;
	}
}
