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

}
