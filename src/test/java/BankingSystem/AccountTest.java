package BankingSystem;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

class AccountTest {
	
	private Customer Ali;
	
	@BeforeEach
	public void CreateAccount() {
		Ali = new Customer("Ali Refaat", "Ali_Refaat", "Fifth settlement", "30311270100058", "01024640120", "21p0105@eng.asu.edu.eg", "123456");
	}
	
	@Test
	@DisplayName("Create Account")
    void testCreateAccount() {
		Account Ali_Account = Account.createAccount(Ali, "A453", "Savings", 1000);
        assertNotNull(Ali_Account);
        assertEquals("A453", Ali_Account.getAccountId());
        assertEquals("Savings", Ali_Account.getAccountType());
        assertEquals(1000, Ali_Account.getBalance());
        assertTrue(Ali.getAccounts().contains(Ali_Account));
    }

    
	
	@Test
	@DisplayName("Test Add Transaction")
    void testAddTransaction() {
		Account Ali_Account = Account.createAccount(Ali, "A453", "Savings", 1000);
        Ali_Account.addTransaction("Deposit", 500);
        List<Transaction> transactions = Ali_Account.getTransactions();
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
    }
	
	@Test
	@DisplayName("Test GetNextTransaction")
    void testGetNextTransactionId() {
		Account Ali_Account = Account.createAccount(Ali, "A453", "Savings", 1000);
        assertEquals(1, Ali_Account.getNextTransactionId());
        Ali_Account.addTransaction("Deposit", 500.0);
        assertEquals(2, Ali_Account.getNextTransactionId());
    }
	
	@Test
	@DisplayName("Test Deposit")
    void testDeposit() {
        Account account = new Account("A123", "Checking", 1000);
        account.deposit(500.0);
        assertEquals(1500.0, account.getBalance());
	}
	
	@Test
	@DisplayName("Test Invalid Deposit Amount")
    void testDepositInvalidAmount() {
        Account account = new Account("A897", "Savings", 1000);
        account.deposit(5100);
        assertEquals(1000, account.getBalance());
	}
	
	@Test
	@DisplayName("Test Withdraw")
    void testWithdraw() {
        Account account = new Account("A784", "Savings", 1000);
        account.withdraw(500);
        assertEquals(500, account.getBalance(), 0.01);
	}
	
	@Test
	@DisplayName("Test Invalid Withdraw Amount")
    void testWithdrawInvalidAmount() {
        Account account = new Account("A123", "Savings", 1000.0);
        account.withdraw(-500.0);
        assertEquals(1000.0, account.getBalance(), 0.01);
    }
	
	@Test
	@DisplayName("Test Transfer Funds")
    void testTransferFunds() {
        Account senderAccount = new Account("A342", "Savings", 1000);
        Account receiverAccount = new Account("A409", "Savings", 500);
        assertTrue(senderAccount.TransferFunds(senderAccount, receiverAccount, 500));
        assertEquals(500, senderAccount.getBalance());
        assertEquals(1000, receiverAccount.getBalance());
    }
	
	@Test
	@DisplayName("Test Invalid Transfer Funds Amount")
    void testTransferFundsInvalidAmount() {
        Account senderAccount = new Account("A123", "Savings", 1000.0);
        Account receiverAccount = new Account("A456", "Savings", 500.0);
        assertFalse(senderAccount.TransferFunds(senderAccount, receiverAccount, -500));
	}
	
	@Test
	@DisplayName("Test string representation of Account")
	void testPrintData() {
	    Account account = new Account("A198", "Checking", 190000);
	    String Expected = "Account{" +
                "AccountId=" + account.getAccountId() +
                ", AccountType='" + account.getAccountType() + '\'' +
                ", balance=" + account.getBalance() +
                '}';
	    assertEquals(Expected, account.toString());
	}
	
	@Test
	@DisplayName("Transfer to the same Account")
	void testTransferFundsSameAccount() {
	    Account account = new Account("A845", "Savings", 1000);
	    assertFalse(account.TransferFunds(account, account, 500));
	    assertEquals(1000, account.getBalance());
	}
	
	@Test
	@DisplayName("Create Account with negative Balance")
	public void testCreateNegBalanceAcc() {
		Account account = Account.createAccount(Ali, "A845", "Checking", -10000);
		assertFalse(Ali.getAccounts().contains(account));
	}
	
	
//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
