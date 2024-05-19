package tr.com.nemesis.abs;

import javax.swing.JFrame;
import javax.swing.JPanel;

// AFrame sınıfı, JFrame sınıfından türetilmiş soyut bir sınıftır.
public abstract class AFrame extends JFrame {
	
	// initFrame() metodu, pencereyi başlatmak için kullanılır.
	public void initFrame(String title, JPanel panel) {
		// paneli pencereye ekler
		add(panel);
		// pencere başlığını belirler
		setTitle(title);
		// pencerenin boyutunu bileşenlerin boyutuna göre ayarlar
		pack();
		// pencereyi ekranın ortasına yerleştirir
		setLocationRelativeTo(null);
		// pencereyi görünür hale getirir
		setVisible(true);
		// pencereyi kapatma düğmesine basıldığında uygulamayı sonlandırır
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	// initPanel() soyut metodu, alt sınıflar tarafından uygulanmalıdır.
	public abstract JPanel initPanel();
}
