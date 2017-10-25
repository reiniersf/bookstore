package cu.pdi.bookstore.domain.accounting.document.events;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentService;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.logs.BookstoreLogService;
import cu.pdi.bookstore.domain.kernel.event.ReceivedTitleSupplyEvent;
import cu.pdi.bookstore.domain.kernel.event.SoldTitleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountingEventHandler {
    private final BookstoreLogService bookstoreLogService;

    private final AccountingDocumentService accountingDocumentService;

    @Autowired
    public AccountingEventHandler(BookstoreLogService bookstoreLogService, AccountingDocumentService accountingDocumentService) {
        this.bookstoreLogService = bookstoreLogService;
        this.accountingDocumentService = accountingDocumentService;
    }

    @EventListener
    public void handleBookstoreEvent(ReceivedTitleSupplyEvent receivedTitleSupplyEvent) {
        List<TransferLog> transferEntries = bookstoreLogService.logTransfers(receivedTitleSupplyEvent.getFrom(),
                receivedTitleSupplyEvent.getTo(),
                receivedTitleSupplyEvent.getTitleSupply());

        accountingDocumentService.registerAccountingDocumentForTransferLogs(transferEntries);
    }

    @EventListener
    public void handleBookstoreEvent(SoldTitleEvent soldTitleEvent) {
        List<TransferLog> salesEntries = bookstoreLogService.logSales(soldTitleEvent.getFrom(),
                soldTitleEvent.getTitleSale(),
                soldTitleEvent.getSoldAt());

        accountingDocumentService.registerAccountingDocumentForTransferLogs(salesEntries);

    }
}
