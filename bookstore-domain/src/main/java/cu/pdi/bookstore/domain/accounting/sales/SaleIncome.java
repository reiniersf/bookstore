package cu.pdi.bookstore.domain.accounting.sales;

import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.title.TitleAccountingInfo;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SaleIncome implements IncomeCalculator{

    private final TitleService titleService;

    @Autowired
    SaleIncome(TitleService titleService){
        this.titleService = titleService;
    }

    public double ofTransfer(TransferLog transferLog) {
        TitleAccountingInfo titleAccountingInfo = titleService.accountingInfoForTitle(transferLog.getTitle());
        return titleAccountingInfo.getPrice().getPrice()*transferLog.getStock().getStockAmount();
    }
}
