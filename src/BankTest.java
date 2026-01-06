import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {

    @Test
    void testHesapEklemeVeBulma() {
        Bank banka = new Bank();
        // Test için sanal bir hesap oluşturuyoruz.
        SavingsAccount hesap = new SavingsAccount("10", "Test Kullanıcısı", 5000.0, 10.0);

        // Hesabı bankaya ekliyoruz.
        banka.addAccount(hesap);

        // Bakalım bankadan geri isteyince doğru geliyor mu?
        Account bulunan = banka.getAccount("10");

        assertNotNull(bulunan); // "Null gelmesin, dolu gelsin" demek.
        assertEquals("Test Kullanıcısı", bulunan.getOwnerName()); // İsim doğru mu?
    }

    @Test
    void testOlmayanHesap() {
        Bank banka = new Bank();

        // Banka boş, ama biz "999" nolu hesabı istiyoruz.
        Account sonuc = banka.getAccount("11");

        // Beklentimiz: Böyle bir hesap olmadığı için NULL gelmesi.
        assertNull(sonuc);
    }
}