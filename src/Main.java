import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        FileOperations fileOps = new FileOperations();
        Scanner scanner = new Scanner(System.in);

        System.out.println("BANKA SİSTEMİ AÇILIYOR : ");

        ArrayList<Account> loadedAccounts = fileOps.loadData();
        for (Account acc : loadedAccounts) {
            bank.addAccount(acc);
        }
        System.out.println("Sistem hazır. " + loadedAccounts.size() + " hesap yüklendi.\n");

        while (true) {
            System.out.println("ANA MENÜ");
            System.out.println("1. Yeni Hesap Aç");
            System.out.println("2. Hesap Bilgilerini Gör");
            System.out.println("3. Para Yatır");
            System.out.println("4. Para Çek");
            System.out.println("5. Transfer Yap");
            System.out.println("6. Kredi Hesapla");
            System.out.println("7. Kaydet Ve Çıkış");
            System.out.println("Seçiminiz : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Hesap açma.
                    System.out.print("Hesap No Giriniz : ");
                    String accNum = scanner.nextLine();
                    System.out.print("Ad-Soyad Giriniz : ");
                    String name = scanner.nextLine();
                    System.out.print("İlk Bakiye : ");
                    double balance = scanner.nextDouble();

                    System.out.println("Hesap Türü ? (1 : Vadeli , 2 : Vadesiz");
                    int type = scanner.nextInt();

                    if (type == 1) {
                        System.out.print("Faiz Oranı : ");
                        double rate = scanner.nextDouble();
                        SavingsAccount sa = new SavingsAccount(accNum, name, balance, rate);
                        bank.addAccount(sa);
                    } else {
                        System.out.print("Kredi Limiti : ");
                        double limit = scanner.nextDouble();
                        CheckingAccount ca = new CheckingAccount(accNum, name, balance, limit);
                        bank.addAccount(ca);
                    }
                    break;

                case 2: // Bilgi görme.
                    System.out.print("Görüntülenecek Hesap No : ");
                    String searchNum = scanner.nextLine();
                    Account foundAcc = bank.getAccount(searchNum);

                    if (foundAcc != null) {
                        System.out.println(foundAcc.toString());
                    }
                    break;
                case 3: // Para yatırma.
                    System.out.print("Hesap No : ");
                    String depNum = scanner.nextLine();
                    Account depAcc = bank.getAccount(depNum);

                    if (depAcc != null) {
                        System.out.print("Yatırılacak Tutar : ");
                        double amount = scanner.nextDouble();
                        depAcc.deposit(amount);

                        if (depAcc instanceof SavingsAccount) {
                            System.out.print("Faiz uygulansın mı ? (1: Evet, 0: Hayır)");
                            int faizSecim = scanner.nextInt();
                            if (faizSecim == 1) ((SavingsAccount) depAcc).calculateInterest();
                        }
                    }
                    break;
                case 4: // Para çekme.
                    System.out.print("Hesap No : ");
                    String withNum = scanner.nextLine();
                    Account withAcc = bank.getAccount(withNum);

                    if (withAcc != null) {
                        System.out.print("Çekilecek Tutar : ");
                        double amount = scanner.nextDouble();
                        withAcc.withdraw(amount);
                    }
                    break;
                case 5: // Transfer
                    System.out.print("Gönderen Hesap No : ");
                    String fromNum = scanner.nextLine();
                    System.out.print("Alıcı Hesap No : ");
                    String toNum = scanner.nextLine();

                    Account fromAcc = bank.getAccount(fromNum);
                    Account toAcc = bank.getAccount(toNum);

                    if (fromAcc != null && toAcc != null) {
                        System.out.print("Transfer Tutarı : ");
                        double amount = scanner.nextDouble();
                        fromAcc.transfer(toAcc, amount);
                    }
                    break;
                case 6: // Kredi hesaplama.
                    System.out.print("Kredi Tutarı : ");
                    double loanAmt = scanner.nextDouble();
                    System.out.print("Vade : ");
                    int months = scanner.nextInt();

                    System.out.println("Kredi Türü Seçiniz : ");
                    System.out.println("1. İhtiyaç Kredisi (Faiz: 1.20");
                    System.out.println("2. Taşıt Kredisi (Faiz: 1.15)");
                    System.out.println("3.Konut Kredisi (Faiz: 1.10)");
                    System.out.print("Seçiminiz : ");
                    int loanType = scanner.nextInt();

                    double interestRate = 0.0;
                    String loanName = "";

                    if (loanType == 1) {
                        interestRate = 0.20;
                        loanName = "İhtiyaç Kredisi";
                    } else if (loanType == 2) {
                        interestRate = 0.15;
                        loanName = "Taşıt Kredisi";
                    } else if (loanType == 3) {
                        interestRate = 0.10;
                        loanName = "Konut Kredisi";
                    } else {
                        System.out.println("Hatalı seçim ! Varsayılan olarak 'İhtiyaç Kredisi' seçildi.");
                        interestRate = 0.20;
                        loanName = "İhtiyaç Kredisi";
                    }
                    Loan loan = new Loan(loanAmt, months, interestRate, loanName);
                    loan.printLoanDetails();
                    break;
                case 7: // Çıkış.
                    System.out.println("Veriler kaydediliyor...");
                    fileOps.saveData(bank.getAllAccounts());
                    System.out.println("Çıkış yapıldı. İyi günler ! ");
                    return;
                default :
                    System.out.println("Geçersiz İşlem !");
            }
        }
    }
}
