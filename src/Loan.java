public class Loan {
    private double amount; // Kredi miktarı. (Ana para.)
    private int duration; // Vade. (Ay sayısı.)
    private double interestRate; // Faiz oranı.
    private String loanType; // Kredi türü.

    public Loan (double amount, int duration, double interestRate, String loanType) {
        this.amount = amount;
        this.duration = duration;
        this.interestRate = interestRate;
        this.loanType = loanType;
    }
    public double calculateMonthlyPayment() {
        // Formül : (Ana Para * (1 + Faiz * Vade)) / Vade
        double totalPayment = amount * (1 + (interestRate * duration));
        return totalPayment / duration;
    }
    public double getTotalPayment(){
        return amount * (1 + (interestRate * duration));
    }
    public void printLoanDetails() {
        System.out.println("KREDİ DETAYLARI : ");
        System.out.println("Tür :" + loanType);
        System.out.println("Ana Para : " + amount + "TL");
        System.out.println("Vade : " + duration + "Ay");
        System.out.println("Faiz Oranı : " + interestRate);
        System.out.println("Aylık Taksit : " + calculateMonthlyPayment() + "TL");
        System.out.println("Toplam Geri Ödeme : " + getTotalPayment() + "TL");
    }

    public static void calculateLoan(int krediTuru, double anaPara, int vade) {
        double oran = 0;
        String tur = "";

        if (krediTuru == 1) {
            oran = 0.20; // İhtiyaç kredisi.
            tur = "İhtiyaç Kredisi";
        } else if (krediTuru == 2) {
            oran = 0.15; // Taşıt kredisi.
            tur = "Taşıt Kredisi";
        } else if (krediTuru == 3) {
            oran = 0.10; // Konut kredisi.
            tur = "Konut Kredisi";
        } else {
            System.out.println("Hatalı seçim yaptınız!");
            return;
        }

        Loan yeniKredi = new Loan(anaPara, vade, oran, tur);
        yeniKredi.printLoanDetails();
    }
}
