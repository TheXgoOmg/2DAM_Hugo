import pojo.BankAccount;
import pojo.BankThreadSave;
import pojo.BankThreadSpend;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BankAccount bankAccount = new BankAccount(0);

        List<Thread> bankThreadSaveSpendList = new ArrayList<>();

        for  (int i = 0; i < 20; i++) {
            bankThreadSaveSpendList.add(new Thread(new BankThreadSave(bankAccount)));
            bankThreadSaveSpendList.add(new Thread(new BankThreadSpend(bankAccount)));
        }

        for (Thread th: bankThreadSaveSpendList) {
            th.start();
        }
    }
}