import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
public class FileOperations {
    private static final String FILE_NAME = "bank_data.txt";
    public void saveData (ArrayList<Account> accounts){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))){
            for (Account acc : accounts){
                String line = acc.getAccountNumber() + ","  + acc.getOwnerName() + "," + acc.getBalance();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Veriler başarıyla dosyaya kaydedildi ! ");
        } catch (IOException e){
            System.out.println("Dosya yazma hatası : " + e.getMessage());
        }
    }
}
