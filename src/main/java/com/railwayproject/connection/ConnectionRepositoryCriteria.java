package com.railwayproject.connection;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ConnectionRepositoryCriteria {

    private final EntityManager entityManager;
    private final CriteriaBuilder criteriaBuilder;

    public ConnectionRepositoryCriteria(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.criteriaBuilder = entityManager.getCriteriaBuilder();
    }

    public Page<Connection> findAllWithFilters(ConnectionPage connectionPage,
                                               ConnectionSearchCriteria connectionSearchCriteria) {
        CriteriaQuery<Connection> criteriaQuery = criteriaBuilder.createQuery(Connection.class);
        Root<Connection> connectionRoot = criteriaQuery.from(Connection.class);
        Predicate predicate = getPredicate(connectionSearchCriteria, connectionRoot);
        criteriaQuery.where(predicate);

        TypedQuery<Connection> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setFirstResult(connectionPage.getPageNumber() * connectionPage.getPageSize());
        typedQuery.setMaxResults(connectionPage.getPageSize());

        Pageable pageable = getPageable(connectionPage);

        long connectionsCount = getConnectionsCount(predicate);

        return new PageImpl<>(typedQuery.getResultList(), pageable, connectionsCount);
    }

    private Predicate getPredicate(ConnectionSearchCriteria connectionSearchCriteria,
                                   Root<Connection> connectionRoot) {
        List<Predicate> predicates = new ArrayList<>();
        if(Objects.nonNull(connectionSearchCriteria.getFrom())) {
            predicates.add(
                    criteriaBuilder.like(connectionRoot.get("from"),
                            "%" + connectionSearchCriteria.getFrom() + "%")
            );
        }
        if(Objects.nonNull(connectionSearchCriteria.getTo())) {
            predicates.add(
                    criteriaBuilder.like(connectionRoot.get("to"),
                            "%" + connectionSearchCriteria.getTo() + "%")
            );
        }
        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private Pageable getPageable(ConnectionPage connectionPage) {
        Sort sort = Sort.by(connectionPage.getSortDirection(), connectionPage.getSortBy());
        return PageRequest.of(connectionPage.getPageNumber(), connectionPage.getPageSize(), sort);
    }

    private long getConnectionsCount(Predicate predicate) {
        CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
        Root<Connection> countRoot = countQuery.from(Connection.class);
        countQuery.select(criteriaBuilder.count(countRoot)).where(predicate);
        return entityManager.createQuery(countQuery).getSingleResult();
    }
}
