import java.util.ArrayList;
// SavingsAccount ve CheckingAccount sınıflarımız bu Account sınıfından miras alacak.
public abstract class Account implements Transferable {
    private String accountNumber; // Hesap numarası.
    private String ownername; // Hesap sahibi adı.
    protected double balance; // Bakiye.
    private ArrayList<Transaction> history; // İşlem geçmişi listesi.

/**
 * Hesap oluşturan constructor u yazıyoruz.
 * @param accountNumber Hesap numarası.
 * @param ownername Hesap sahibi adı.
 * @param initialBalance Açılış bakiyesi.
 */
public Account (String accountNumber, String ownername, double initialBalance){
    this.accountNumber = accountNumber;
    this.ownername = ownername;
    this.balance = initialBalance;
    this.history = new ArrayList<>();
    addTransaction("Açılış", initialBalance, "Hesap açılışı");
}

public void addTransaction (String type, double amount, String description){
    Transaction t = new Transaction (type, amount, description);
    this.history.add(t);
}

/**
 * Para yatırma metodu hesaba para yatırır ve işlemi kaydeder.
 * @param amount Yatırılacak tutar.
 */
public void deposit(double amount){
    if(amount > 0){
        this.balance += amount;
        addTransaction("Para yatırma", amount, "Hesaba para yatırma.");
    }
}

/**
 * Para çekme metodu abstractır.Vadeli ve vadesiz hesaplar bu kuralı kendileri belirleyecekler.
 * @param amount Çekilecek tutar.
 * @return İşlem başarılıysa true, başarısız ise false döner.
 */
public abstract boolean withdraw(double amount);

//Getter metotları :
public String getAccountNumber(){
    return accountNumber;
}
public String getBalance(){
    return balance;
}
public ArrayList<Transaction> getHistory(){
    return history;
}
}
