# MoneyTransferProgram

### Description of the task
This program performs a money transfer from one account to another.
Previously, the program contains a file with account numbers and amounts
on them. At startup, the program should wait for information to be entered from the console. When selecting the parsing function, the program must parse all suitable files from the ‘input’ directory and transfer the parsed files to another ‘archive’ directory. As a result of file parsing, the program must generate/update a report file and update the information in the file with account numbers and amounts to the latest ones.

### Technical implementation details
At startup, the program waits for information input:
1 is entered into the console - calling the operation of parsing translation files from input,
2 is entered into the console - calling the operation of displaying a list of all translations from the report file.

### Implementation details of classes:
A Parser is a class with a method that parses files from "input" to "archive".
This class has a private constructor implemented so that we can create only one instance of this class.
The parse() method is the main method in this class, it implements all file parsing. It searches the "input" folder for all "txt" files and processes the information inside these files.

Example:
`11111-11111|60000-00001|3000`

The line is separated with "|" and the first part of the line contains the sender's account number, the second part contains the recipient's account number, and the third part contains the transfer amount.
The parser updates the file with all the numbers: it searches for the account number and adds the transfer amount to the recipient's balance and subtracts it from the sender's balance. In parallel, the Parser creates a report file using another ReportCreator class.  And then transfers the paired files to the "archive". 
The parser has private helper methods such as:

- `readAccount()` which reads the file with the account numbers
-  `moveFileToArchive(File file)` which transfers files to the archive
-  `isValidAccountNumber(String accountNumber)` which checks the account number for validity
-  `validate(String fromAccountNumber, String toAccountNumber, Double amount)` handles transfer exceptions

The util folder contains an enum with messages for exceptions. 
this is necessary so that, if necessary, we can change messages in only one place. Then we will have fewer errors in the code.

A class ReportCreator has only one method, which creates report. 

And a class ReportPrinter print report to the console.

If you want to download this project for yourself, do not forget to specify your file paths. :blush:

### A class diagram describing the dependencies of some classes on others

![diagram](https://github.com/kseniya-potapovich/MoneyTransferProgramm/assets/108470997/e25eb3dd-f1da-46e2-80dd-9cece0271242)



