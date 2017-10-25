package cu.pdi.bookstore.infrastructure.inventory.service;

import cu.pdi.bookstore.domain.accounting.title.TitleAccountingInfo;
import cu.pdi.bookstore.domain.inventory.title.TitleInventoryInfo;
import cu.pdi.bookstore.domain.inventory.title.TitleRepository;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.kernel.ISBN;
import cu.pdi.bookstore.domain.kernel.title.TitleInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by taiyou
 * on 9/3/17.
 */
@Service
public class LocalTitleService implements TitleService {

    private TitleRepository titleRepository;

    @Autowired
    public LocalTitleService(TitleRepository titleRepository){
        this.titleRepository = titleRepository;
    }

    @Transactional
    @Override
    public void registerNewTitle(TitleInfo titleInfo) {
        titleRepository.saveInventoryInfo(titleInfo.forInventoryPurpose());
        titleRepository.saveAccountingInfo(titleInfo.forAccountingPurpose());
    }

    @Override
    public List<ISBN> getRegisteredTitlesIn(Set<ISBN> supplyISBNList) {
        return titleRepository.findRegisteredTitlesIn(supplyISBNList)
                .stream().map(TitleInventoryInfo::getIsbn)
                .collect(Collectors.toList());
    }

    @Override
    public List<TitleInventoryInfo> getTitlesInfo(List<ISBN> isbnList) {
        return titleRepository.findRegisteredTitlesIn(new HashSet<>(isbnList));
    }

    @Override
    public TitleAccountingInfo accountingInfoForTitle(ISBN isbn) {
        return titleRepository.getAccountingInfoByIsbn(isbn);
    }
}
