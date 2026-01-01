public class SavingsAccount extends Account {
    private double interestRate; // Bu hesaba özel değişken.
    public SavingsAccount (String accountNumber, String ownerName, double initialBalance, double interestRate){
        super(accountNumber, ownerName, initialBalance);
        this.interestRate = interestRate;
    }
 @Override
 public boolean withdraw(double amount){
        if(this.balance >= amount){
            this.balance -= amount;
            addTransaction ("Çekme", amount, "Vadeli hesaptan para çekildi.");
            return true;
        }else{
            System.out.println("Hesapta yeterli bakiye yok !");
            return false;
        }
 }
 public double getInterestRate(){
        return interestRate; //Faiz oranını öğrenmek için getter.
 }
}
