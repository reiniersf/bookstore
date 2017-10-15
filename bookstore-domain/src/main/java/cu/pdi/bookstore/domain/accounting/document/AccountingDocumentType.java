package cu.pdi.bookstore.domain.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.reception.ReceptionReport;
import cu.pdi.bookstore.domain.accounting.document.specs.DeliveryVoucherSpecification;
import cu.pdi.bookstore.domain.accounting.document.specs.ReceptionReportSpecification;
import cu.pdi.bookstore.domain.accounting.document.specs.SalesSummarySpecification;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.transfer.DeliveryVoucher;
import cu.pdi.bookstore.domain.kernel.specification.Specification;
import cu.pdi.bookstore.infrastructure.exceptions.InvalidTransferException;

import java.util.stream.Stream;

public enum AccountingDocumentType {
    DELIVERY_VOUCHER {
        @Override
        protected Specification<TransferLog> specification() {
            return DeliveryVoucherSpecification.instance();
        }

        @Override
        public DeliveryVoucher createAccountingDocument() {
            return null;
        }
    },
    SALES_SUMMARY {
        @Override
        protected Specification<TransferLog> specification() {
            return SalesSummarySpecification.instance();
        }

        @Override
        public DeliveryVoucher createAccountingDocument() {
            return null;
        }
    },
    RECEPTION_REPORT {
        @Override
        protected Specification<TransferLog> specification() {
            return ReceptionReportSpecification.instance();
        }

        @Override
        public DeliveryVoucher createAccountingDocument() {
            return new ReceptionReport();
        }
    };

    public static AccountingDocumentType forTransfersLike(TransferLog transferLog) {
        return Stream.of(values())
                .filter(accountingDocumentType -> accountingDocumentType.specification().isSatisfiedBy(transferLog))
                .findFirst()
                .orElseThrow(() -> new InvalidTransferException("Non valid accounting document type exist for given transfers"));
    }

    protected abstract Specification<TransferLog> specification();

    public abstract DeliveryVoucher createAccountingDocument();
}
