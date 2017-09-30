package cu.pdi.bookstore.domain.accounting.document;

import org.springframework.stereotype.Component;

@Component
public class AccountingDocumentFactory {
    public static AccountingDocument createAccountingDocument(AccountingDocumentType accountingDocumentType) {

        return new AccountingDocument(accountingDocumentType);
    }
}
