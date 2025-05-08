package org.example.fullbankingapplication.Service;

public class UserImpltTest  {
/*


    private UserRepository userRepository;
    private EmailService emailService;

    public UserImpltTest(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    // Save Account

    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        // Creat User
        User saveUser = User.builder()
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .email(userRequest.getEmail())
                .gender(userRequest.getGender())
                .adresse(userRequest.getAdresse())
                .StateOfOrigin(userRequest.getStateOfOrigin())
                .PhoneNumber(String.valueOf(userRequest.getPhoneNumber()))
                .balance(BigDecimal.ZERO)
                .accountNumber(AccountUtil.generateAccountNumber())
                .build();
        User SavedUser = userRepository.save(saveUser);

        // Send Email
        EmailDetails emailDetails = EmailDetails.builder()
                .ReciverEmail(SavedUser.getEmail())
                .EmailBody("Vous avez créé un compte bancaire avec les informations suivantes :\n"
                        + "Nom : " + SavedUser.getFirstName() + " " + SavedUser.getLastName() + "\n"
                        + "Numéro de compte : " + SavedUser.getAccountNumber() + "\n"
                        + "Adresse : " + SavedUser.getAdresse()
                )
                .EmailSubject("Création d'un compte bancaire")
                .build();

        emailService.sendEmail(emailDetails);
        // Message de return
        return BankResponse.builder()
                .ResponseCode(AccountUtil.ACCOUNT_CREATION_CODE)
                .ResponseMessage(AccountUtil.ACCOUNT_CREATION_MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(SavedUser.getFirstName() + " " + SavedUser.getLastName())
                        .AccountBalance(SavedUser.getBalance())
                        .AccountNumber(SavedUser.getAccountNumber())
                        .build())
                .build();
    }


    // Check Balance
    @Override
    public BankResponse BalanceEnquiry(CheckBalance checkBalance) {
        Boolean existByAccount = userRepository.existsByAccountNumber(checkBalance.getAccountNumber());
        if (!existByAccount) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
        User FindUser = userRepository.findByAccountNumber(checkBalance.getAccountNumber());
        return BankResponse.builder()
                .ResponseCode(AccountUtil.ACCOUNT_EXISTS_CODE)
                .ResponseMessage(AccountUtil.ACCOUNT_EXISTS_MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(FindUser.getFirstName() + " " + FindUser.getLastName())
                        .AccountBalance(FindUser.getBalance())
                        .AccountNumber(FindUser.getAccountNumber())
                        .build())
                .build();
    }

    @Override
    public String getNameInfo(CheckBalance checkBalance) {
        return "";
    }

    @Override
    public BankResponse CreditAccount(CreditDebitrequest creditDebitrequest) {
        Boolean existByAccount = userRepository.existsByAccountNumber(creditDebitrequest.getAccountNumber());
        if (!existByAccount) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }

        User findUser = userRepository.findByAccountNumber(creditDebitrequest.getAccountNumber());
        findUser.setBalance(findUser.getBalance().add(creditDebitrequest.getAmount()));
        User SaveUser = userRepository.save(findUser);

        // send email alerte

        EmailDetails emailDetails = EmailDetails.builder()
                .ReciverEmail(SaveUser.getEmail())
                .EmailBody("Vous avez ajouté une somme d'argent à votre compte bancaire :\n"
                        + "Nom : " + SaveUser.getFirstName() + " " + SaveUser.getLastName() + "\n"
                        + "Numéro de compte : " + SaveUser.getAccountNumber() + "\n"
                        + "Montant ajouté : " + creditDebitrequest.getAmount() + " MAD\n"
                        + "Nouveau solde : " + SaveUser.getBalance() + " MAD"
                )
                .EmailSubject("Crédit sur votre compte bancaire")
                .build();
                 emailService.sendEmail(emailDetails);


        return BankResponse.builder()
                .ResponseCode(AccountUtil.BALANCE_ADDED_CODE)
                .ResponseMessage(AccountUtil.BALANCE_ADDED__MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(SaveUser.getFirstName() + " " + findUser.getLastName())
                        .AccountBalance(SaveUser.getBalance())
                        .AccountNumber(SaveUser.getAccountNumber())
                        .build())
                .build();
    }


    @Override
    public BankResponse DebitAccount(CreditDebitrequest creditDebitrequest) {
        Boolean existByAccount = userRepository.existsByAccountNumber(creditDebitrequest.getAccountNumber());
        if (!existByAccount) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.ACCOUNT_NOT_EXISTS_CODE)
                    .ResponseMessage(AccountUtil.ACCOUNT_NOT_EXISTS_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
// verification if it is the balance in the account befor do the operation
        User findUser = userRepository.findByAccountNumber(creditDebitrequest.getAccountNumber());
        if (findUser.getBalance().compareTo(creditDebitrequest.getAmount()) < 0) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.BALANCE_INSUFISSANT_CODE)
                    .ResponseMessage(AccountUtil.BALANCE_INSUFISSANT_MESSAGE)
                    .accountinfo(null)
                    .build();
        } else
            findUser.setBalance(findUser.getBalance().subtract(creditDebitrequest.getAmount()));
            User SaveUser = userRepository.save(findUser);

        // send email alerte

        EmailDetails emailDetails = EmailDetails.builder()
                .ReciverEmail(SaveUser.getEmail())
                .EmailBody("Vous avez retiré une somme d'argent de votre compte bancaire :\n"
                        + "Nom : " + SaveUser.getFirstName() + " " + SaveUser.getLastName() + "\n"
                        + "Numéro de compte : " + SaveUser.getAccountNumber() + "\n"
                        + "Montant retiré : " + creditDebitrequest.getAmount() + " MAD\n"
                        + "Nouveau solde : " + SaveUser.getBalance() + " MAD"
                )
                .EmailSubject("Débit sur votre compte bancaire")
                .build();
        emailService.sendEmail(emailDetails);


        return BankResponse.builder()
                .ResponseCode(AccountUtil.BALANCE_ADDED_CODE)
                .ResponseMessage(AccountUtil.BALANCE_ADDED__MESSAGE)
                .accountinfo(AccountInfo.builder()
                        .AccountName(SaveUser.getFirstName() + " " + findUser.getLastName())
                        .AccountBalance(SaveUser.getBalance())
                        .AccountNumber(SaveUser.getAccountNumber())
                        .build())
                .build();

    }



    // faire un transfer d'argenet.
    @Override
    public BankResponse TransferAmount(TransferRequest transferRequest) {
        Boolean existAccount = userRepository.existsByAccountNumber(transferRequest.getDestinaterAccountNumber());
        if (!existAccount) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.BALANCE_INSUFISSANT_CODE)
                    .ResponseMessage(AccountUtil.BALANCE_INSUFISSANT_MESSAGE)
                    .accountinfo(null)
                    .build();
        }
        User SenderAccount = userRepository.findByAccountNumber(transferRequest.getSenderAccountNumber());
        User ReciverAccount = userRepository.findByAccountNumber(transferRequest.getDestinaterAccountNumber());
        if (SenderAccount.getBalance().compareTo(transferRequest.getAmount()) < 0) {
            return BankResponse.builder()
                    .ResponseCode(AccountUtil.BALANCE_INSUFISSANT_CODE)
                    .ResponseMessage(AccountUtil.BALANCE_INSUFISSANT_MESSAGE)
                    .accountinfo(null)
                    .build();
        }else

            // faire le transfert cote sender avec l'envoie d'un alerte

            SenderAccount.setBalance(SenderAccount.getBalance().subtract(transferRequest.getAmount()));
            User SaveSenderAccount =  userRepository.save(SenderAccount);
            EmailDetails senderEmail = EmailDetails.builder()
                .ReciverEmail(SenderAccount.getEmail())
                .EmailBody("Vous avez effectué un virement bancaire :\n"
                        + "Montant transféré : " + transferRequest.getAmount() + " MAD\n"
                        + "Destinataire : " + ReciverAccount.getFirstName() + " " + ReciverAccount.getLastName() + "\n"
                        + "Numéro de compte du destinataire : " + ReciverAccount.getAccountNumber() + "\n"
                        + "Votre nouveau solde : " + SaveSenderAccount.getBalance()  + " MAD"
                )
                .EmailSubject("Confirmation de virement")
                .build();
             emailService.sendEmail(senderEmail);

             ReciverAccount.setBalance(ReciverAccount.getBalance().add(transferRequest.getAmount()));
             User SaveReciverAccount =    userRepository.save(ReciverAccount);
             EmailDetails receiverEmail = EmailDetails.builder()
                .ReciverEmail(SaveReciverAccount.getEmail())
                .EmailBody("Vous avez reçu un virement bancaire :\n"
                        + "Montant reçu : " + transferRequest.getAmount() + " MAD\n"
                        + "Expéditeur : " + SenderAccount.getFirstName() + " " + SenderAccount.getLastName() + "\n"
                        + "Numéro de compte de l’expéditeur : " + SenderAccount.getAccountNumber() + "\n"
                        + "Votre nouveau solde : " + SaveReciverAccount.getBalance() + " MAD"
                )
                .EmailSubject("Notification de réception de virement")
                .build();

             emailService.sendEmail(receiverEmail);
             return BankResponse.builder()
                     .ResponseCode(AccountUtil.TRANSFER_OPERATION_CODE)
                     .ResponseMessage(AccountUtil.TRANSFEROPERATION_MESSAGE)
                     .accountinfo(null)
                     .build();
    }


    // trouver All acount
    @Override
    public List<UserRequest> findAllAccounts() {
      return null;
    }



*/

}