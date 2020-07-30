package cu.pdi.bookstore.domain.accounting.transfer;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentType;
import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentInfo;
import cu.pdi.bookstore.domain.accounting.document.AccountingInfoHolder;
import cu.pdi.bookstore.domain.accounting.document.Consecutive;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import lombok.Getter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "document")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("0")
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
    @AttributeOverride(name = "consecutiveValue", column = @Column(name = "consecutive",unique = true))
    protected Consecutive consecutive;
    @Getter
    @Column(name = "accounting_document_type")
    protected AccountingDocumentType accountingDocumentType;

    @OneToMany(mappedBy = "accountingDocument",
            fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private List<TransferLog> associatedTransfers;


    public DeliveryVoucher() {
        this(AccountingDocumentType.DELIVERY_VOUCHER);
    }

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
    public void includeAccountingInfo(AccountingDocumentInfo accountingDocumentInfo) {
        DeliveryVoucherInfo deliveryVoucherInfo = (DeliveryVoucherInfo) accountingDocumentInfo;
        this.consecutive = deliveryVoucherInfo.getConsecutive();
    }
}
