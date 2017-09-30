package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document")
@NoArgsConstructor
public class AccountingDocument {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "id_document")
    private Long idDocument;
    @Getter
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Getter
    private String consecutive;
    @Getter
    @Column(name = "document_type")
    private AccountingDocumentType accountingDocumentType;

    @OneToMany(mappedBy = "accountingDocument",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<TransferLog> associatedTransfers;

    AccountingDocument(AccountingDocumentType accountingDocumentType) {
        this.accountingDocumentType = accountingDocumentType;
        this.createdAt = LocalDateTime.now();
    }

    AccountingDocument(AccountingDocumentType accountingDocumentType, LocalDateTime localDateTime, String consecutive) {
        this.accountingDocumentType = accountingDocumentType;
        this.createdAt = localDateTime;
        this.consecutive = consecutive;
    }

    public List<TransferLog> associatedTransferLogs() {
        return associatedTransfers;
    }

    public void includeTransferLogs(List<TransferLog> transferLogs){
        transferLogs.forEach(transferLog -> transferLog.setAccountingDocument(this));
        this.associatedTransfers = transferLogs;
    }
}
