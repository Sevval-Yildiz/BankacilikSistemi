import java.util.ArrayList;
// SavingsAccount ve CheckingAccount sınıflarımız bu Account sınıfından miras alacak.
public abstract class Account implements Transferable {
    private String accountNumber; // Hesap numarası.
    private String ownerName; // Hesap sahibi adı.
    protected double balance; // Bakiye.
    private ArrayList<Transaction> history; // İşlem geçmişi listesi.

/**
 * Hesap oluşturan constructor u yazıyoruz.
 * @param accountNumber Hesap numarası.
 * @param ownerName Hesap sahibi adı.
 * @param initialBalance Açılış bakiyesi.
 */
public Account (String accountNumber, String ownerName, double initialBalance){
    this.accountNumber = accountNumber;
    this.ownerName = ownerName;
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

/**
 * Transfer metodu transferable interface inden gelir.Başka bir hesaba para transferi yapar.
 * @param toAccount Paranın gideceği hesap.
 * @param amount Gönderilecek miktar.
 * @return İşlem başarılıysa true başarısızsa false döner.
 */
@Override
public boolean transfer (Account toAccount, double amount) {
    if (toAccount == this) {
        System.out.println("Kendinize para transferi yapamazsınız !");
        return false;
    }
    if (this.withdraw(amount)){
        toAccount.deposit(amount);
        this.addTransaction("Giden Transfer", amount, toAccount.getOwnerName() + "kişisine transfer");
        toAccount.addTransaction("Gelen Transfer", amount, this.ownerName + "kişisinden transfer");
        System.out.println("Başarılı : " + toAccount.getOwnerName() + "hesabına " + amount + " TL gönderildi.");
        return true;
    }else{
        System.out.println("Para transferi için bakiye yetersiz, işlem başarısız !");
        return false;
    }
}
//Getter metotları :
public String getAccountNumber(){
    return accountNumber;
}
public double getBalance(){
    return balance;
}
public ArrayList<Transaction> getHistory(){
    return history;
}
public String getOwnerName(){
    return ownerName;
}
}
