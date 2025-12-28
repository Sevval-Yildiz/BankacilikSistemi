public interface Transferable {
    /**
     * Başka bir hesaba para transferi yapmamızı sağlayan interface.
     *  @param toAccount Paranın gönderileceği hesap.
     * @param amount Gönderilecek para miktarı.
     * @return İşlem başaralıysa true işlem başarısızsa false döner.
     */
    boolean transfer (Account toAccount , double amount);
}
