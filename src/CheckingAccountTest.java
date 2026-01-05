import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {

    @Test
    void testLimitliParaCekme() {
        // 1000 TL Bakiye, 500 TL  Kredi Limiti
        CheckingAccount hesap = new CheckingAccount("7", "Ali Yünçek", 1000.0, 500.0);

        // Durum 1: Normal Para Çekme (Bakiye yetiyor)
        hesap.withdraw(800.0);
        assertEquals(200.0, hesap.getBalance()); // 1000 - 800 = 200 kalmalı
    }

    @Test
    void testEksiBakiyeKullanimi() {
        // 1000 TL Bakiye, 500 TL Kredi Limiti
        CheckingAccount hesap = new CheckingAccount("8", "Veli Aydın", 1000.0, 500.0);

        // Durum 2: Bakiyeden fazla ama limit dahilinde çekme
        // 1200 çekmek istiyoruz. Bakiye yetmiyor ama limit (1000+500=1500) yetiyor.
        boolean sonuc = hesap.withdraw(1200.0);

        assertTrue(sonuc); // İşlem başarılı olmalı
        assertEquals(-200.0, hesap.getBalance()); // Bakiye -200'e düşmeli
    }

    @Test
    void testLimitAsimi() {
        // 1000 TL Bakiye, 500 TL Limit
        CheckingAccount hesap = new CheckingAccount("9", "Ayşe Ersoy", 1000.0, 500.0);

        // Durum 3: Her şeyi aşan tutar
        // Toplam çekilebilir para: 1500 TL. Biz 2000 istiyoruz.
        boolean sonuc = hesap.withdraw(2000.0);

        assertFalse(sonuc); // İşlem BAŞARISIZ olmalı
        assertEquals(1000.0, hesap.getBalance()); // Para çekilmemeli, bakiye aynı kalmalı
    }
}