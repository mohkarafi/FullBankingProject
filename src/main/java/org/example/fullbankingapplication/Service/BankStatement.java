package org.example.fullbankingapplication.Service;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.extern.slf4j.Slf4j;
import org.example.fullbankingapplication.Dto.EmailDetails;
import org.example.fullbankingapplication.Entity.Transaction;
import org.example.fullbankingapplication.Entity.User;
import org.example.fullbankingapplication.Repository.TransactionRepository;
import org.example.fullbankingapplication.Repository.UserRepository;
import org.example.fullbankingapplication.Service.Implt.EmailService;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class BankStatement {
    private final static String File = "/Users/mac/Desktop/ReleveBanquaire/MyBankStatment.pdf";
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    public BankStatement(TransactionRepository transactionRepository, UserRepository userRepository, EmailService emailService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    public List<Transaction> getAllTransactions(String AccountNumber, String StartDate, String EndDate) throws FileNotFoundException, DocumentException {
        LocalDate startDate = LocalDate.parse(StartDate);
        LocalDate endDate = LocalDate.parse(EndDate);
        // filtrer juste les transactions qui ont la meme accountNumber et stratdate et finDate .
        List<Transaction> transactions = transactionRepository.findAll();
        transactions.stream()
                .filter(transaction -> transaction.getAccountNumber().equals(AccountNumber))
                .filter(transaction -> !transaction.getCreatedDate().isBefore(startDate) &&
                        !transaction.getCreatedDate().isAfter(endDate)).toList();

        User user = userRepository.findByAccountNumber(AccountNumber);
        String ClientAccountNumber = user.getAccountNumber();
        String ClientName = user.getFirstName() + " " + user.getLastName();


        Rectangle rectangle = PageSize.A4;
        Document document = new Document(rectangle);
        OutputStream filePdf = new FileOutputStream(File);
        PdfWriter.getInstance(document, filePdf);

        document.open();
        // ------------------------------------ Create Titre -----------------------------------

        PdfPTable tableTitre = new PdfPTable(1);
        PdfPCell StatmentTitre = new PdfPCell(new Phrase("Banking Statment"));
        StatmentTitre.setHorizontalAlignment(Element.ALIGN_CENTER);
        StatmentTitre.setBorder(0);
        StatmentTitre.setBackgroundColor(BaseColor.LIGHT_GRAY);
        StatmentTitre.setPadding(20F);
        StatmentTitre.setHorizontalAlignment(Element.ALIGN_CENTER);

        PdfPCell BankAdresse = new PdfPCell(new Phrase("Morroco , Casablanca-Zerktouni , 27500"));
        BankAdresse.setBorder(0);

        tableTitre.addCell(StatmentTitre);
        tableTitre.setSpacingAfter(16f);
        // ------------------------------------ Create Client info -----------------------------------


        PdfPTable statmentInfo = new PdfPTable(2);
        PdfPCell statmentTitre = new PdfPCell(new Phrase("BANKING STATMENT"));
        statmentTitre.setBorder(0);
        PdfPCell ClientAccount = new PdfPCell(new Phrase("Account Number : " + ClientAccountNumber));
        ClientAccount.setBorder(0);
        PdfPCell ClientFullName = new PdfPCell(new Phrase("Full Name : " + ClientName));
        ClientFullName.setBorder(0);
        PdfPCell TransactionStartDate = new PdfPCell(new Phrase("Start Date : " + startDate));
        TransactionStartDate.setBorder(0);
        PdfPCell TransactionLastDate = new PdfPCell(new Phrase("End Date : " + endDate));
        TransactionLastDate.setBorder(0);

        statmentInfo.addCell(statmentTitre);
        statmentInfo.addCell(ClientAccount);
        statmentInfo.addCell(ClientFullName);
        statmentInfo.addCell(TransactionStartDate);
        statmentInfo.addCell(TransactionLastDate);
        statmentInfo.addCell(BankAdresse);
        statmentInfo.setSpacingAfter(16F);

        // ------------------------------------ Create Client info -----------------------------------

        PdfPTable TransactionsTable = new PdfPTable(5);
        PdfPCell TransactionsID = new PdfPCell(new Phrase(" ID"));
        TransactionsID.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell Transactionstype = new PdfPCell(new Phrase(" Type"));
        Transactionstype.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell TransactionsStatus = new PdfPCell(new Phrase(" Status"));
        TransactionsStatus.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell TransactionsAmount = new PdfPCell(new Phrase("Amount"));
        TransactionsAmount.setBackgroundColor(BaseColor.LIGHT_GRAY);
        PdfPCell TransactionsStartdate = new PdfPCell(new Phrase("Start Date"));
        TransactionsStartdate.setBackgroundColor(BaseColor.LIGHT_GRAY);

        TransactionsTable.addCell(TransactionsID);
        TransactionsTable.addCell(Transactionstype);
        TransactionsTable.addCell(TransactionsStatus);
        TransactionsTable.addCell(TransactionsAmount);
        TransactionsTable.addCell(TransactionsStartdate);


        for (Transaction transaction : transactions) {
            TransactionsTable.addCell(new Phrase(String.valueOf(transaction.getTransactionId())));
            TransactionsTable.addCell(new Phrase(transaction.getTransactionType()));
            TransactionsTable.addCell(new Phrase(transaction.getTransactionStatus()));
            TransactionsTable.addCell(new Phrase(String.valueOf(transaction.getAmount())));
            TransactionsTable.addCell(new Phrase(String.valueOf(transaction.getCreatedDate())));
        }
        document.add(tableTitre);
        document.add(statmentInfo);
        document.add(TransactionsTable);
        document.close();

        EmailDetails emailDetails = EmailDetails.builder()
                .EmailSubject("Bank Statment")
                .EmailBody("this is The Total Transactions for Your Account ")
                .ReciverEmail(user.getEmail())
                .attachment(File)
                .build();
        emailService.sendEmailWithAttachment(emailDetails);

        return transactions;
    }
}
