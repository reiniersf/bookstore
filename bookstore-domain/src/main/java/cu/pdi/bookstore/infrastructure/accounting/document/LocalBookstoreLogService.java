package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.logs.BookstoreLogRepository;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.kernel.title.TitleSale;
import cu.pdi.bookstore.domain.kernel.title.TitleSupply;
import cu.pdi.bookstore.domain.accounting.document.logs.BookstoreLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Service
public class LocalBookstoreLogService implements BookstoreLogService {
    private final BookstoreLogRepository bookstoreLogRepository;

    @Autowired
    public LocalBookstoreLogService(BookstoreLogRepository bookstoreLogRepository) {
        this.bookstoreLogRepository = bookstoreLogRepository;
    }

    @Transactional
    @Override
    public List<TransferLog> logTransfers(DepartmentCode from, DepartmentCode to, TitleSupply titleSupply) {

        return titleSupply.titlesISBN()
                .stream()
                .map(isbn -> bookstoreLogRepository.logTransfer(from, to, isbn, titleSupply.getStockForTitle(isbn)))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public List<TransferLog> logSales(DepartmentCode from, TitleSale titleSale, LocalDateTime soldAt) {
        return titleSale.titlesISBN()
                .stream()
                .map(isbn -> bookstoreLogRepository.logTransfer(from, DepartmentCode.SOLD, isbn, titleSale.getStockForTitle(isbn)))
                .collect(Collectors.toList());
    }
}
