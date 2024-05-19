package tr.com.nemesis.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tr.com.nemesis.core.ObjectHelper;
import tr.com.nemesis.interfaces.IDataAccessLayer;
import tr.com.nemesis.type.CustomerContract;

// CustomerDal sınıfı, CustomerContract türündeki nesnelerin veritabanı işlemlerini gerçekleştiren bir veri erişim katmanı (DAL) sınıfıdır.
public class CustomerDal extends ObjectHelper implements IDataAccessLayer<CustomerContract>{

	// Veritabanı işlemlerini IDataAccessLayer arayüzünden gelen metotların uygulamaları:

	// Yeni bir müşteri eklemek için
	@Override
	public void insert(CustomerContract contract) {
		// Veritabanı bağlantısını alır.
		Connection connection = getConnection();
		try {
			// Bağlantı üzerinde bir ifade oluşturur.
			Statement statement = connection.createStatement();
			// SQL INSERT ifadesini kullanarak veritabanına müşteri ekler.
			statement.executeUpdate("INSERT INTO customer (adi, soyadi) VALUES ('" + contract.getName() + "','" + contract.getSurname() + "')");
			// Kullanılan kaynakları serbest bırakır.
			statement.close();
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	// Müşteri güncellemek için
	@Override
	public void update(CustomerContract contract) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("UPDATE customer SET adi='"+contract.getName()+"' , soyadi='"+contract.getSurname()+"' WHERE id="+contract.getId());
			
			statement.close();
			connection.close();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
		
		
	}

	// Müşteri silmek için
	@Override
	public void delete(CustomerContract contract) {
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("DELETE FROM customer WHERE id="+contract.getId());
			
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	// Müşteri listesi almak için
	@Override
	public List<CustomerContract> getList() {
		List<CustomerContract> dataContract = new ArrayList<CustomerContract>();
		
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer");
			
			
			while (rs.next()) {
				CustomerContract contract = new CustomerContract();
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("adi"));
				contract.setSurname(rs.getString("soyadi"));
				
				dataContract.add(contract);
			}
			statement.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return dataContract;
	}

	// Belirli bir müşteriye ait bilgileri getirmek için
	@Override
	public CustomerContract getById(int id) {
		
		CustomerContract contract = new CustomerContract();
		Connection connection = getConnection();
		
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer WHERE id="+id);
			
			while (rs.next()) {
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("adi"));
				contract.setSurname(rs.getString("soyadi"));
				
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return contract;
	}
	
	// İsim araması yapmak için
	public List<CustomerContract> getSearch(String name){
		List<CustomerContract> dataContract = new ArrayList<CustomerContract>();
		Connection connection = getConnection();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("SELECT * FROM customer where adi LIKE '%"+name+"%'");
			while (rs.next()) {
				CustomerContract contract = new CustomerContract();
				
				contract.setId(rs.getInt("id"));
				contract.setName(rs.getString("adi"));
				contract.setSurname(rs.getString("soyadi")); 
				dataContract.add(contract);
				
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return dataContract;
	}
}
