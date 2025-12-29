import java.util.Date;
/**
 * Banka hesabında gerçekleşen işlemlerin kaydını tuttuğumuz sınıftır.
 * Örneğin para çekme,para yatırma gibi işlemler.
 */
public class Transaction {
    private Date date; // Bu date değişkeni işlem tarihini tutmamızı sağlar.
    private String type; // Bu type değişkeni işlemin tipini (para çekme,para yatırma...) tutmamızı sağlar.
    private double amount; // Bu amount değişkeni yapılan işlemdeki para miktarını tutmamızı sağlar.
    private String description; // Bu description değişkeni yapılan işleme açıklama yazmamızı sağlar.
/**
 * Yeni bir işlem oluşturur.
 * @param type İşlemin türü.
 * @param amount İşlem miktarı.
 * @param description İşlemle ilgili açıklama.
 */
public Transaction(String type, double amount, String description){
    this.date = new Date (); // İşlemin yapıldığı tarihi otomatik olarak alır.
    this.type = type ;
    this.amount = amount;
    this.description = description;
}
@Override
    public String toString(){
    return date + " " + type + " " + amount + "TL " + description ;
}
}
