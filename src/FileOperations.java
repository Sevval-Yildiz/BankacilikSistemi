import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;

public class FileOperations {
    private static final String FILE_NAME = "bank_data.txt";

    public void saveData(ArrayList<Account> accounts) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {

            for (Account acc : accounts) {
                String line = "";

                // Hesap türü kontrolü :
                if (acc instanceof SavingsAccount){
                    SavingsAccount savings = (SavingsAccount) acc;
                    line = String.format("%s,%s,%s,Vadeli,%s",
                    acc.getAccountNumber(),
                    acc.getOwnerName(),
                    acc.getBalance(),
                    savings.getInterestRate());

                }else if (acc instanceof CheckingAccount){
                    CheckingAccount checking = (CheckingAccount) acc;
                    line = String.format("%s,%s,%s,Vadesiz,%s",
                            acc.getAccountNumber(),
                            acc.getOwnerName(),
                            acc.getBalance(),
                            checking.getOverdraftLimit());
                }

                writer.write(line);
                writer.newLine();

            }
            System.out.println("Veriler ve hesap türleri başarıyla kaydedildi ! ");
        } catch (IOException e){
            System.out.println("Dosya yazma hatası : " + e.getMessage());
        }
    }
    public ArrayList<Account> loadData(){
        ArrayList<Account> accountList = new ArrayList<> ();
        File file = new File(FILE_NAME);
        if(!file.exists()){
            return accountList;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))){
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
            System.out.println("Veriler dosyadan yüklendi ! ");
        } catch(IOException e){
            System.out.println("Dosya okuma hatası : " + e.getMessage());
        }
        return accountList;
    }
}