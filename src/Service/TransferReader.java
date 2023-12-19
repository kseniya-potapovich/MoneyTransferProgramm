package Service;

import Models.Transfer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TransferReader {
    public List<Transfer> parseTransfersFromDirectory(String directoryPath) {
        List<Transfer> transfers = new ArrayList<>();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && file.getName().endsWith(".txt")) {
                    List<Transfer> fileTransfers = parseTransfersFromFile(file.getAbsolutePath());
                    transfers.addAll(fileTransfers);
                }
            }
        }

        return transfers;
    }

    private List<Transfer> parseTransfersFromFile(String fileName) {
        List<Transfer> transfers = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Transfer transfer = parseTransferFromLine(line);
                if (transfer != null) {
                    transfers.add(transfer);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return transfers;
    }

    private Transfer parseTransferFromLine(String line) {
        String[] transferData = line.split("\\|");
        if (transferData.length == 3) {
            String sourceAccountNumber = transferData[0].trim();
            String destinationAccountNumber = transferData[1].trim();
            int amount = Integer.parseInt(transferData[2].trim());
            return new Transfer(sourceAccountNumber, destinationAccountNumber, amount);
        }
        return null;
    }
}
