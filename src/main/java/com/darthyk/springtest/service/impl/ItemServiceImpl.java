package com.darthyk.springtest.service.impl;

import com.darthyk.springtest.dto.ItemDto;
import com.darthyk.springtest.model.Item;
import com.darthyk.springtest.repository.ItemRepository;
import com.darthyk.springtest.service.ItemService;
import com.darthyk.springtest.service.ItemSpecification;
import com.darthyk.springtest.service.SearchCriteria;
import com.darthyk.springtest.utils.ItemParams;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import static com.darthyk.springtest.utils.FilterConstants.AMOUNT;
import static com.darthyk.springtest.utils.FilterConstants.NAME;
import static com.darthyk.springtest.utils.FilterConstants.PRICE;

public class ItemServiceImpl implements ItemService {

    private EntityManager entityManager;
    private ItemRepository itemRepository;

    public ItemServiceImpl(EntityManager entityManager, ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
        this.entityManager = entityManager;
    }

    @Override public void save(ItemDto dto) {
        Item item = new Item();
        item.setName(dto.getName());
        item.setAmount(dto.getAmount());
        item.setPrice(dto.getPrice());

        itemRepository.save(item);
    }

    @Override public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

    @Override public List<ItemDto> findAllByParams(ItemParams itemParams) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Item> criteriaQuery = criteriaBuilder.createQuery(Item.class);
        Root<Item> itemRoot = criteriaQuery.from(Item.class);

        List<Predicate> predicates = new ArrayList<>();
        if (itemParams != null && itemParams.getName() != null) {
            predicates.add(criteriaBuilder.equal(itemRoot.get(NAME), itemParams.getName()));
        }
        if (itemParams != null && itemParams.getPrice() != null) {
            predicates.add(criteriaBuilder.equal(itemRoot.get(PRICE), itemParams.getPrice()));
        }
        if (itemParams != null && itemParams.getAmount() != null) {
            predicates.add(criteriaBuilder.equal(itemRoot.get(AMOUNT), itemParams.getAmount()));
        }


        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        List<Item> resultList = entityManager.createQuery(criteriaQuery).getResultList();

        return resultList.stream().map(item -> {
            ItemDto dto = new ItemDto();
            dto.setName(item.getName());
            dto.setAmount(item.getAmount());
            dto.setPrice(item.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }

    @Override public List<ItemDto> findByParams(ItemParams itemParams) {
        List<Specification> specifications = new ArrayList<>();
        if (itemParams.getAmount() != null) {
            specifications.add(new ItemSpecification(new SearchCriteria<>(AMOUNT, itemParams.getAmount())));
        }
        if (itemParams.getName() != null) {
            specifications.add(new ItemSpecification(new SearchCriteria<>(NAME, itemParams.getName())));
        }
        if (itemParams.getPrice() != null) {
            specifications.add(new ItemSpecification(new SearchCriteria<>(PRICE, itemParams.getPrice())));
        }

        Specification<Item> itemSpecification = specifications.stream()
                .reduce(null, (specificationsResult, specification) -> {
                    if (specificationsResult == null) {
                        return specification;
                    }
                    return specificationsResult.and(specification);
                });
        List<Item> items;
        if (!specifications.isEmpty()) {
             items = itemRepository.findAll(itemSpecification);
        } else {
            items = itemRepository.findAll();
        }
        return items.stream().map(item -> {
            ItemDto dto = new ItemDto();
            dto.setName(item.getName());
            dto.setAmount(item.getAmount());
            dto.setPrice(item.getPrice());
            return dto;
        }).collect(Collectors.toList());
    }
}
