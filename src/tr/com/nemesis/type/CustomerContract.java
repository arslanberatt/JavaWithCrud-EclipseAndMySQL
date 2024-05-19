package tr.com.nemesis.type;

// CustomerContract sınıfı, müşteri bilgilerini tutmak için kullanılan bir veri taşıyıcı (DTO) sınıfıdır.
public class CustomerContract {
	// Müşteri kimliği
	private int id;
	// Müşterinin adı
	private String name;
	// Müşterinin soyadı
	private String surname;
	
	// Getter ve setter metotları:
	
	// Müşteri kimliğini getiren metot
	public int getId() {
		return id;
	}
	// Müşteri kimliğini atan metot
	public void setId(int id) {
		this.id = id;
	}
	
	// Müşteri adını getiren metot
	public String getName() {
		return name;
	}
	// Müşteri adını atan metot
	public void setName(String name) {
		this.name = name;
	}
	
	// Müşteri soyadını getiren metot
	public String getSurname() {
		return surname;
	}
	// Müşteri soyadını atan metot
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	@Override
	public String toString() {
		
		return name + " " + surname;
	}
}
