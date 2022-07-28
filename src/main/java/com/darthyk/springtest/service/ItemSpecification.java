package com.darthyk.springtest.service;

import com.darthyk.springtest.model.Item;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

public class ItemSpecification implements Specification<Item> {

    private SearchCriteria searchCriteria;

    public ItemSpecification(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    @Override public Predicate toPredicate(Root<Item> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        return criteriaBuilder.equal(root.get(searchCriteria.getKey()), searchCriteria.getValue());
    }
}
