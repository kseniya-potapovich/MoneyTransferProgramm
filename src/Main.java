import Models.Account;
import Models.Transfer;
import Service.AccountReader;
import Service.Parser;
import Service.TransferReader;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        TransferReader transferReader = new TransferReader();
        List<Transfer> transfer = transferReader.parseTransfersFromDirectory("D:\\Courses\\MoneyTransferProgramm\\src\\input");
        System.out.println(transfer);
    }
}
