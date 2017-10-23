package cu.pdi.bookstore.domain.accounting.document.events;

import cu.pdi.bookstore.domain.accounting.document.AccountingDocumentService;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLogService;
import cu.pdi.bookstore.domain.kernel.event.ReceivedTitleSupplyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountingEventHandler {
    private final TransferLogService transferEntryService;

    private final AccountingDocumentService accountingDocumentService;

    @Autowired
    public AccountingEventHandler(TransferLogService transferEntryService, AccountingDocumentService accountingDocumentService) {
        this.transferEntryService = transferEntryService;
        this.accountingDocumentService = accountingDocumentService;
    }

    @EventListener
    public void handleBookstoreEvent(ReceivedTitleSupplyEvent receivedTitleSupplyEvent) {
        List<TransferLog> transferEntries = transferEntryService.logTransfers(receivedTitleSupplyEvent.getFrom(),
                receivedTitleSupplyEvent.getTo(),
                receivedTitleSupplyEvent.getTitleSupply());

        accountingDocumentService.registerAccountingDocumentForTransferLogs(transferEntries);
    }
}
