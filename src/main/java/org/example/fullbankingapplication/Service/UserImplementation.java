package org.example.fullbankingapplication.Service;

import lombok.AllArgsConstructor;
import org.example.fullbankingapplication.Dto.*;
import org.example.fullbankingapplication.Entity.User;
import org.example.fullbankingapplication.Repository.UserRepository;
import org.example.fullbankingapplication.Service.Implt.TransactionService;
import org.example.fullbankingapplication.Service.Implt.UserService;
import org.example.fullbankingapplication.Utilis.AccountUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@AllArgsConstructor
public class UserImplementation implements UserService {

    private UserRepository userRepository;
    private TransactionService transactionService;
    private EmailServiceImplt emailService;

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            BankResponse bankResponse = BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
        User user = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .PhoneNumber(String.valueOf(userRequest.getPhoneNumber()))
                .gender(userRequest.getGender())
                .adresse(userRequest.getAdresse())
                .accountNumber(AccountUtil.generateAccountNumber())
                .StateOfOrigin(userRequest.getStateOfOrigin())
                .balance(BigDecimal.ZERO)
                .email(userRequest.getEmail())
                .build();
        User savedUser = userRepository.save(user);


        String welcomeMessage = "Welcome " + savedUser.getFirstName() + " " + savedUser.getLastName()
                + ",\n\n"
                + "You have successfully created an account. We are excited to have you on board and look forward to your journey with us.\n\n"
                + "If you have any questions or need assistance, feel free to contact our support team.\n\n"
                + "Best regards,\nThe Team";
        EmailDetails emailDetails = EmailDetails.builder()
                .ReciverEmail(savedUser.getEmail())
                .EmailSubject("User Created")
                .EmailBody(welcomeMessage)
                .build();
        emailService.sendEmail(emailDetails);


        BankResponse responseCreate = BankResponse.builder()
                .ResponseCode(AccountUtil.ACCOUNT_CREATION_CODE)
                .ResponseMessage(AccountUtil.ACCOUNT_CREATION_MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(savedUser.getFirstName() + " " + savedUser.getLastName())
                        .AccountBalance(savedUser.getBalance())
                        .AccountNumber(AccountUtil.generateAccountNumber())
                        .build())
                .build();

        return responseCreate;
    }


    // Methode pour aficher la balnce de compte
    @Override
    public BankResponse BalanceEnquiry(CheckBalance checkBalance) {
        boolean ifAccountExists = userRepository.existsByAccountNumber(checkBalance.getAccountNumber());
        if (!ifAccountExists) {
            BankResponse bankResponse = BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
            return bankResponse;
        }
        User FoundAccount = userRepository.findByAccountNumber(checkBalance.getAccountNumber());
        BankResponse reponse = BankResponse.builder()
                .ResponseCode(AccountUtil.ACCOUNT_FOUND_CODE)
                .ResponseMessage(AccountUtil.ACCOUNT_FOUND_MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountBalance(FoundAccount.getBalance())
                        .AccountName(FoundAccount.getFirstName() + " " + FoundAccount.getLastName())
                        .AccountNumber(FoundAccount.getAccountNumber())
                        .build())
                .build();
        return reponse;
    }


    @Override
    public String getNameInfo(CheckBalance checkBalance) {
        boolean ifAccountExists = userRepository.existsByAccountNumber(checkBalance.getAccountNumber());
        if (!ifAccountExists) {
            return AccountUtil.ACCOUNT_NOT_EXISTS_CODE;
        }
        User FindUSer = userRepository.findByAccountNumber(checkBalance.getAccountNumber());
        return (FindUSer.getFirstName() + " " + FindUSer.getLastName());
    }


    @Override
    public BankResponse CreditAccount(CreditDebitRequest creditDebitrequest) {
        Boolean AccountExist = userRepository.existsByAccountNumber(creditDebitrequest.getAccountNumber());
        if (!AccountExist) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
        User userAccount = userRepository.findByAccountNumber(creditDebitrequest.getAccountNumber());
        userAccount.setBalance(userAccount.getBalance().add(creditDebitrequest.getAmount()));
        userRepository.save(userAccount);

        // save Transaction

        TransactionRequest transactionDto = TransactionRequest.builder()
                .transactionType("Credit")
                .accountNumber(creditDebitrequest.getAccountNumber())
                .amount(creditDebitrequest.getAmount())
                .transactionStatus("SUCCES")
                .build();
        transactionService.saveTransaction(transactionDto);


        BankResponse Balnceresponse = BankResponse.builder()
                .ResponseCode(AccountUtil.BALANCE_ADDED_CODE)
                .ResponseMessage(AccountUtil.BALANCE_ADDED__MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(userAccount.getFirstName() + " " + userAccount.getLastName())
                        .AccountNumber(userAccount.getAccountNumber())
                        .AccountBalance(userAccount.getBalance())
                        .build())
                .build();
        return Balnceresponse;
    }


    @Override
    public BankResponse DebitAccount(CreditDebitRequest creditDebitrequest) {
        Boolean ExistAccount = userRepository.existsByAccountNumber(creditDebitrequest.getAccountNumber());
        if (!ExistAccount) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
        User userAccount = userRepository.findByAccountNumber(creditDebitrequest.getAccountNumber());
        if (userAccount.getBalance().compareTo(creditDebitrequest.getAmount()) > 0) {
            userAccount.setBalance(userAccount.getBalance().subtract(creditDebitrequest.getAmount()));
            userRepository.save(userAccount);


            TransactionRequest transactionDto = TransactionRequest.builder()
                    .transactionType("Debit")
                    .accountNumber(creditDebitrequest.getAccountNumber())
                    .amount(creditDebitrequest.getAmount())
                    .transactionStatus("SUCCES")
                    .build();
            transactionService.saveTransaction(transactionDto);


            return BankResponse.builder()
                    .ResponseCode(AccountUtil.BALANCE_ADDED_CODE)
                    .ResponseMessage("Operation debit Successful")
                    .accountinfo(AccountInfo.builder()
                            .AccountName(userAccount.getFirstName() + " " + userAccount.getLastName())
                            .AccountNumber(userAccount.getAccountNumber())
                            .AccountBalance(userAccount.getBalance())
                            .build())
                    .build();
        } else {
            return BankResponse.builder().
                    ResponseCode(AccountUtil.BALANCE_INSUFISSANT_CODE)
                    .ResponseMessage(AccountUtil.BALANCE_INSUFISSANT_MESSAGE)
                    .accountinfo(null)
                    .build();

        }
    }

    @Override
    public BankResponse TransferAmount(TransferRequest transferRequest) {
        // boolean CheckDestinaterExistance = userRepository.existsByAccountNumber(transferRequest.getDestinaterAccountNumber());
        boolean CheckSenderExistance = userRepository.existsByAccountNumber(transferRequest.getSenderAccountNumber());

        if (!(CheckSenderExistance)) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }

        User SenderUser = userRepository.findByAccountNumber(transferRequest.getSenderAccountNumber());
        if (SenderUser.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.BALANCE_INSUFISSANT_CODE)
                    .ResponseMessage(AccountUtil.BALANCE_INSUFISSANT_MESSAGE)
                    .accountinfo(null)
                    .build();
        } else {


            SenderUser.setBalance(SenderUser.getBalance().subtract(transferRequest.getAmount()));
            userRepository.save(SenderUser);
            EmailDetails debitAlert = EmailDetails.builder()
                    .EmailSubject("Debit Operation")
                    .ReciverEmail(SenderUser.getEmail())
                    .EmailBody("vous avez effectuer un transfer d'un montant de " + transferRequest.getAmount())
                    .build();
            emailService.sendEmail(debitAlert);


            User DestinaterUser = userRepository.findByAccountNumber(transferRequest.getDestinaterAccountNumber());
            DestinaterUser.setBalance(DestinaterUser.getBalance().add(transferRequest.getAmount()));
            userRepository.save(DestinaterUser);
            EmailDetails CreditAlert = EmailDetails.builder()
                    .EmailSubject("Credit Operation")
                    .ReciverEmail(DestinaterUser.getEmail())
                    .EmailBody("vous avez recu d'un montant de " + transferRequest.getAmount() +
                            "lors de " + SenderUser.getAccountNumber() +
                            " " + SenderUser.getFirstName() + " " + SenderUser.getLastName())
                    .build();
            emailService.sendEmail(CreditAlert);

            TransactionRequest transactionDto = TransactionRequest.builder()
                    .transactionType("Credit")
                    .accountNumber(transferRequest.getSenderAccountNumber())
                    .amount(transferRequest.getAmount())
                    .transactionStatus("SUCCES")
                    .build();
            transactionService.saveTransaction(transactionDto);


            return BankResponse.builder()
                    .ResponseCode(AccountUtil.TRANSFER_OPERATION_CODE)
                    .ResponseMessage(AccountUtil.TRANSFEROPERATION_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
    }

    @Override
    public List<User> findAllAccounts() {
        List<User> users = userRepository.findAll();
        return users;
    }
}



