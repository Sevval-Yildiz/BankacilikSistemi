import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {

    @Test
    void testHesapOlusturma() {
        // 1. Senaryo: Yeni bir vadeli hesap açıyoruz
        // Hesap No : 5, İsim: Ahmet Yılmaz, Bakiye: 5000, Faiz: 10.0
        SavingsAccount hesap = new SavingsAccount("5", "Ahmet Yılmaz", 5000.0, 10.0);

        // Beklentimiz: Bakiyenin 5000 olması
        assertEquals(5000.0, hesap.getBalance());

        // Beklentimiz: Hesap sahibinin "Ahmet Yılmaz" olması
        assertEquals("Ahmet Yılmaz", hesap.getOwnerName());
    }

    @Test
    void testParaYatirma() {
        SavingsAccount hesap = new SavingsAccount("5", "Ahmet Yılmaz", 2000.0, 10.0);

        // 500 TL yatırıyoruz
        hesap.deposit(500.0);

        // Beklentimiz: 2000 + 500 = 2500 olması lazım
        // assertEquals(BEKLENEN, GERÇEKLEŞEN);
        assertEquals(2500.0, hesap.getBalance());
    }

    @Test
    void testFaizHesaplama() {
        // 1000 TL bakiye, %50 Faiz Oranı (Kolay hesaplansın diye 50 verdim)
        SavingsAccount hesap = new SavingsAccount("6", "Mehmet Erdem", 1000.0, 50.0);

        // Faizi hesapla metodunu çalıştırıyoruz
        // Mantığın: Bakiye * (Faiz / 100) -> 1000 * 0.50 = 500 TL faiz gelmeli.
        // Yeni bakiye 1500 olmalı.
        hesap.calculateInterest();

        assertEquals(1500.0, hesap.getBalance());
    }
}