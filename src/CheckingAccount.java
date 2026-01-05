public class CheckingAccount extends Account {
    private double overdraftLimit; // Kredi limiti. Eksiye düşme hakkı.

    public CheckingAccount(String accountNumber, String ownerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, ownerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        /* Vadesiz hesaplarda insanlar mevcut parası + kredi limiti kadar para çekebilir yani eksiye düşebilir.
        Ancak para + kredi limitinden fazlasını çekemezler.
         */
        if (this.balance + this.overdraftLimit >= amount) {
            this.balance -= amount;
            addTransaction("Çekme", amount, "Vadesiz hesaptan çekildi.");
            return true;
        } else {
            System.out.println("Yetersiz bakiye ve kredi limiti aşımı !");
            return false;
        }
    }

    public double getOverdraftLimit() { // Limiti öğrenmek için getter.
        return overdraftLimit;
    }

    @Override
    public String toString() {
        return "\nHESAP BİLGİ KARTI\n" +
                "Hesap Türü : Vadesiz Hesap\n" +
                "Ad Soyad : " + getOwnerName() + "\n" +
                "Hesap No : " + getAccountNumber() + "\n" +
                "Bakiye : " + getBalance() + " TL\n" +
                "Kredi Limiti : " + this.overdraftLimit + " TL\n" ;
    }
}