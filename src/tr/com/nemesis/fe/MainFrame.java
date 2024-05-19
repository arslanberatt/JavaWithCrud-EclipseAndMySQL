package tr.com.nemesis.fe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import tr.com.nemesis.abs.AFrame;
import tr.com.nemesis.dal.CustomerDal;
import tr.com.nemesis.type.CustomerContract;

// MainFrame sınıfı, kullanıcı arayüzünü oluşturan ana pencere sınıfıdır.
public class MainFrame extends AFrame {

	// Kurucu metod, pencere başlatılır ve içerik paneli oluşturulur.
	public MainFrame() {
		initFrame("Müşteri Ekle", initPanel());
	}

	// initPanel() metodu, pencerenin içeriğini oluşturur ve bir JPanel döndürür.
	@Override
	public JPanel initPanel() {
		// GridLayout kullanılarak panel oluşturulur (5 satır, 2 sütun).
		JPanel panel = new JPanel(new BorderLayout());
		JPanel buttonsJPanel = new JPanel(new GridLayout(5,2));
		JLabel searchJLabel = new JLabel("Kişi Ara:",JLabel.RIGHT);
		buttonsJPanel.add(searchJLabel);
		JTextField searchField = new JTextField(10);
		buttonsJPanel.add(searchField);
		
		
		// Adı için etiket ve metin alanı oluşturulur ve panele eklenir.
		JLabel nameJLabel = new JLabel("Adı: ", JLabel.RIGHT);
		buttonsJPanel.add(nameJLabel);
		JTextField nameField = new JTextField(10);
		buttonsJPanel.add(nameField);
		
		// Soyadı için etiket ve metin alanı oluşturulur ve panele eklenir.
		JLabel surnameJLabel = new JLabel("Soyadı: ",JLabel.RIGHT);
		buttonsJPanel.add(surnameJLabel);
		JTextField surnameField = new JTextField(10);
		buttonsJPanel.add(surnameField);
		
		// Kaydet, Güncelle ve Sil düğmeleri oluşturulur ve panele eklenir.
		JButton saveButton = new JButton("Kaydet");
		buttonsJPanel.add(saveButton);
		JButton updateButton = new JButton("Güncelle");
		buttonsJPanel.add(updateButton);
		JButton deleteButton = new JButton("Sil");
		buttonsJPanel.add(deleteButton);
		
		
		JList list = new JList(new CustomerDal().getList().toArray());
		JScrollPane pane = new JScrollPane(list);
		// Kaydet düğmesine tıklanınca gerçekleşecek işlemler tanımlanır.
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// Müşteri bilgileri için bir CustomerContract nesnesi oluşturulur.
				CustomerContract contract = new CustomerContract();
				
				// Ad ve soyad alanlarından bilgiler alınır ve CustomerContract nesnesine atanır.
				contract.setName(nameField.getText());
				contract.setSurname(surnameField.getText());
				
				// Bilgi mesajı penceresi gösterilir.
				JOptionPane.showMessageDialog(nameField, contract.getName() +" "+ contract.getSurname()+ " başarılı bir şekilde kaydedildi.");
				// Yeni bir CustomerDal nesnesi oluşturulur ve müşteri bilgileri veritabanına eklenir.
				new CustomerDal().insert(contract);
				
				list.setListData(new CustomerDal().getList().toArray());
				list.setSelectedIndex(0);
				
				
			}
		});
		
		// Listeden bir müşteri seçildiğinde, ilgili bilgilerin ilgili alanlara yerleştirilmesi için olay dinleyicisi tanımlanır.
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				CustomerContract contract = (CustomerContract) list.getSelectedValue();
				
				
				if(contract !=null) {
					CustomerContract contDal = new CustomerDal().getById(contract.getId());
					nameField.setText(contDal.getName());
					surnameField.setText(contDal.getSurname());
				}
				
			}
		});
		
		// Arama alanına her tuşa basıldığında, aramayı güncellemek için olay dinleyicisi tanımlanır.
		searchField.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				list.setListData(new CustomerDal().getSearch(searchField.getText()).toArray());
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		// Güncelle düğmesine tıklanınca gerçekleşecek işlemler tanımlanır.
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerContract cast = (CustomerContract) list.getSelectedValue();
				CustomerContract contract = new CustomerContract();
				contract.setId(cast.getId());
				contract.setName(nameField.getText());
				contract.setSurname(surnameField.getText());
				
				new CustomerDal().update(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()+ " "+contract.getSurname()+ " kişisi güncellendi");
				list.setListData(new CustomerDal().getList().toArray());
			}
		});
		
		// Sil düğmesine tıklanınca gerçekleşecek işlemler tanımlanır.
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CustomerContract contract = (CustomerContract) list.getSelectedValue();
				new CustomerDal().delete(contract);
				JOptionPane.showMessageDialog(nameField, contract.getName()+ " "+contract.getSurname()+ " kişisi silindi");
				list.setListData(new CustomerDal().getList().toArray());
			}
		});
		
		panel.add(buttonsJPanel, BorderLayout.NORTH);
		panel.add(pane, BorderLayout.CENTER);
		
		
		return panel; // Oluşturulan panel döndürülür.
	}	
	
	
}
