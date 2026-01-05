import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.time.LocalDateTime; // Tarih ve saat için
import java.time.format.DateTimeFormatter; // Saat formatı için

public class FileOperations {
    private static final String DATA_FILE = "bank_data.txt";
    private static final String LOG_FILE = "islemler_gecmisi.txt"; // İşlem geçmişi dosyası

    // 1. Hesap verilerini (Bakiye vs) kaydeden metod
    public static void saveData(ArrayList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE))) {
            for (Account acc : accounts) {
                String line = "";
                if (acc instanceof SavingsAccount){
                    SavingsAccount savings = (SavingsAccount) acc;
                    line = String.format("%s,%s,%s,Vadeli,%s",
                            acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance(), savings.getInterestRate());
                } else if (acc instanceof CheckingAccount){
                    CheckingAccount checking = (CheckingAccount) acc;
                    line = String.format("%s,%s,%s,Vadesiz,%s",
                            acc.getAccountNumber(), acc.getOwnerName(), acc.getBalance(), checking.getOverdraftLimit());
                }
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e){
            System.out.println("Veri kayıt hatası : " + e.getMessage());
        }
    }

    // 2. [YENİ EKLENEN] Yapılan işlemleri metin olarak kaydeden metod
    public static void logTransaction(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) { // 'true' parametresi dosyanın sonuna ekleme yapar

            // O anki tarih ve saati al
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            // Dosyaya yaz: [Tarih] İşlem Mesajı
            writer.write("[" + dtf.format(now) + "] " + message);
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Log yazma hatası: " + e.getMessage());
        }
    }

    // 3. Verileri okuyan metod
    public ArrayList<Account> loadData(){
        ArrayList<Account> accountList = new ArrayList<> ();
        File file = new File(DATA_FILE);
        if(!file.exists()){
            return accountList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))){
            String line;
            while ((line=reader.readLine()) != null){
                String[] parts = line.split(",");
                if (parts.length == 5){
                    String accNum = parts[0];
                    String owner = parts[1];
                    double balance = Double.parseDouble(parts[2]);
                    String type = parts[3];
                    double specialValue = Double.parseDouble(parts[4]);

                    if (type.equals("Vadeli")){
                        accountList.add(new SavingsAccount(accNum,owner,balance,specialValue));
                    } else if (type.equals("Vadesiz")){
                        accountList.add(new CheckingAccount(accNum, owner, balance, specialValue));
                    }
                }
            }
            System.out.println("Veriler yüklendi.");
        } catch(IOException e){
            System.out.println("Dosya okuma hatası : " + e.getMessage());
        }
        return accountList;
    }
}