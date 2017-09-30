package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.specs.InternalTransferSpecification;
import cu.pdi.bookstore.domain.accounting.document.specs.ReceptionInformSpecification;
import cu.pdi.bookstore.domain.accounting.document.specs.SalesSummarySpecification;
import cu.pdi.bookstore.domain.accounting.document.transfer.TransferLog;
import cu.pdi.bookstore.infrastructure.exceptions.InvalidTransferException;

public enum AccountingDocumentType {
    INTERNAL_TRANSFER,
    SALES_SUMMARY,
    RECEPTION_INFORM;

    public static AccountingDocumentType forTransfersLike(TransferLog transferLog) {
        if (InternalTransferSpecification.instance().isSatisfiedBy(transferLog)) {
            return INTERNAL_TRANSFER;
        } else if (ReceptionInformSpecification.instance().isSatisfiedBy(transferLog)) {
            return RECEPTION_INFORM;
        } else if(SalesSummarySpecification.instance().isSatisfiedBy(transferLog)){
            return SALES_SUMMARY;
        }else{
            throw new InvalidTransferException("Non valid accounting document type exist for given transfers");
        }
    }
}
