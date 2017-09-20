package cu.pdi.bookstore.infrastructure.inventory.service;

import cu.pdi.bookstore.domain.inventory.title.Title;
import cu.pdi.bookstore.domain.inventory.title.TitleRepository;
import cu.pdi.bookstore.domain.inventory.title.TitleService;
import cu.pdi.bookstore.domain.kernel.ISBN;
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
    public void registerNewTitle(Title title) {
        titleRepository.saveTitle(title);
    }

    @Override
    public List<ISBN> getRegisteredTitlesIn(Set<ISBN> supplyISBNList) {
        return titleRepository.findRegisteredTitlesIn(supplyISBNList)
                .stream().map(Title::getIsbn)
                .collect(Collectors.toList());
    }

    @Override
    public List<Title> getTitlesInfo(List<ISBN> isbnList) {
        return titleRepository.findRegisteredTitlesIn(new HashSet<>(isbnList));
    }
}
