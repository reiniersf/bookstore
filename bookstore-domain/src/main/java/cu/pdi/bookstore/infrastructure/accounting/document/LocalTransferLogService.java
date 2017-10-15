package cu.pdi.bookstore.infrastructure.accounting.document;

import cu.pdi.bookstore.domain.accounting.document.logs.TransferLog;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLogRepository;
import cu.pdi.bookstore.domain.kernel.DepartmentCode;
import cu.pdi.bookstore.domain.inventory.supply.TitleSupply;
import cu.pdi.bookstore.domain.accounting.document.logs.TransferLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Service
public class LocalTransferLogService implements TransferLogService {
    private final TransferLogRepository transferLogRepository;

    @Autowired
    public LocalTransferLogService(TransferLogRepository transferLogRepository) {
        this.transferLogRepository = transferLogRepository;
    }

    @Transactional
    @Override
    public List<TransferLog> logTransfers(DepartmentCode from, DepartmentCode to, TitleSupply titleSupply) {

        return titleSupply.titlesISBN()
                .stream()
                .map(isbn -> transferLogRepository.logTransfer(from, to, isbn, titleSupply.getStockForTitle(isbn)))
                .collect(Collectors.toList());
    }
}
