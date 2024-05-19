package tr.com.nemesis.interfaces;

import java.util.List;

// IDataAccessLayer arayüzü, genel bir veri erişim katmanı arayüzünü tanımlar.
// T, genel olarak işlenecek veri türünü temsil eder.
public interface IDataAccessLayer<T> {

	// Belirtilen türdeki bir nesneyi veritabanına eklemek için kullanılan metot.
	public void insert(T contract);
	
	// Belirtilen türdeki bir nesneyi güncellemek için kullanılan metot.
	public void update(T contract);
	
	// Belirtilen türdeki bir nesneyi veritabanından silmek için kullanılan metot.
	public void delete(T contract);
	
	// Belirtilen türdeki nesnelerin listesini almak için kullanılan metot.
	// Genellikle bir filtre parametresiyle birlikte kullanılır.
	public List<T> getList();
	
	// Belirtilen türdeki bir nesneyi, belirli bir kimlik (id) ile almak için kullanılan metot.
	public T getById(int id);
}
