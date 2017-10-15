package cu.pdi.bookstore.domain.accounting.document.transfer;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentType;
import cu.pdi.bookstore.domain.accounting.document.AccountingInfo;
import cu.pdi.bookstore.domain.accounting.document.AccountingInfoHolder;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "accountingDocumentType", discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("0")
@NoArgsConstructor
public class DeliveryVoucher implements Serializable, AccountingInfoHolder {

    @Getter
    @Id
    @GeneratedValue
    @Column(name = "id_document")
    protected Long idDocument;
    @Getter
    @Column(name = "created_at")
    protected LocalDateTime createdAt;
    @Getter
    @AttributeOverride(name = "consecutiveValue", column = @Column(name = "consecutive"))
    protected Consecutive consecutive;
    @Getter
    @Column(name = "document_type")
    protected AccountingDocumentType accountingDocumentType;

    @OneToMany(mappedBy = "accountingDocument",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    List<TransferLog> associatedTransfers;


    protected DeliveryVoucher(AccountingDocumentType accountingDocumentType) {
        this.accountingDocumentType = accountingDocumentType;
        this.createdAt = LocalDateTime.now();
    }

    protected DeliveryVoucher(AccountingDocumentType accountingDocumentType, LocalDateTime localDateTime, Consecutive consecutive) {
        this.accountingDocumentType = accountingDocumentType;
        this.createdAt = localDateTime;
        this.consecutive = consecutive;
    }

    public List<TransferLog> associatedTransferLogs() {
        return associatedTransfers;
    }

    public void includeTransferLogs(List<TransferLog> transferLogs) {
        transferLogs.forEach(transferLog -> transferLog.setAccountingDocument(this));
        this.associatedTransfers = transferLogs;
    }

    @Override
    public void includeAccountingInfo(AccountingInfo accountingInfo) {

    }
}
