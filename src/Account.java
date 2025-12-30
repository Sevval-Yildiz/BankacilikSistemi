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
