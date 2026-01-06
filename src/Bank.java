import java.util.ArrayList;
public class Bank {
    private ArrayList<Account> accounts; // Bankadaki tüm hesapların listesi.
    public Bank() {
        this.accounts = new ArrayList<> (); // Banka kurulduğunda liste hazırlanır.
    }
    public void addAccount (Account account){ // Bankaya yeni bir hesap ekleme metodu.
        this.accounts.add(account);
        System.out.println("Başarılı : " + account.getAccountNumber() + " numaralı hesap bankaya eklendi.");
    }
    public Account getAccount (String accountNumber){ // Hesap numarası ile hesap bulma metodu.
        for (Account acc : this.accounts){
            // Listeyi tek tek geziyoruz.
            if (acc.getAccountNumber().equals(accountNumber)){
                return acc; // Hesabı bulursak geri döndürüyoruz.
            }
        }
    System.out.println("Hata ! " + accountNumber + "numaralı hesap bulunamadı ! ");
        return null; // Hesabı bulamazsak boş dönüyoruz.
    }

    public ArrayList<Account> getAllAccounts() {
        return this.accounts;
    }
}
