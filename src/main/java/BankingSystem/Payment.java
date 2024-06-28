package BankingSystem;

import java.time.LocalDate;


public class Payment {
    private LocalDate PaymentDate;
    private double PrincipalAmount;
    private double InterestAmount;
    private double totalPaymentAmount;
    private PaymentStatus paymentStatus;
    
    public enum PaymentStatus {
        PENDING, PAID, MISSED, LATE, PARTIAL, DEFAULT
    }

    public Payment(LocalDate paymentDate, double principalAmount, double interestAmount) {
        PaymentDate = paymentDate;
        PrincipalAmount = principalAmount;
        InterestAmount = interestAmount;
    }

    public LocalDate getPaymentDate() {
        return PaymentDate;
    }

    public double getPrincipalAmount() {
        return PrincipalAmount;
    }

    public double getInterestAmount() {
        return InterestAmount;
    }

    public double getTotalPaymentAmount() {
        return PrincipalAmount + InterestAmount;
    }
    
    public PaymentStatus getPaymentStatus() {
    	return paymentStatus;
    }
    
    public void applyPayment(Loan loan) {
    	Payment payment = new Payment(PaymentDate, PrincipalAmount, InterestAmount);
        if (loan == null) {
            System.out.println("Invalid Loan");
            return;
        }

        if (paymentStatus == PaymentStatus.PAID || paymentStatus == PaymentStatus.MISSED) {
            System.out.println("Payment has already been processed or missed.");
            return;
        }

        if (PaymentDate.isAfter(LocalDate.now())) {
            paymentStatus = PaymentStatus.PENDING;
        } else if (PaymentDate.isBefore(LocalDate.now()) && paymentStatus != PaymentStatus.PAID) {
            paymentStatus = PaymentStatus.LATE;
        }
        
        loan.makePayment(payment);

        if (loan.getLoanStatus() == Loan.LoanStatus.PAID_OFF) {
            paymentStatus = PaymentStatus.PAID;
        } else if (loan.getLoanStatus() == Loan.LoanStatus.DEFAULT) {
            paymentStatus = PaymentStatus.DEFAULT;
        }
    }

    public String PrintData() {
        return "Payment{" +
                "paymentDate=" + PaymentDate +
                ", principalAmount=" + PrincipalAmount +
                ", interestAmount=" + InterestAmount +
                ", totalPaymentAmount=" + totalPaymentAmount +
                '}';
    }
}
