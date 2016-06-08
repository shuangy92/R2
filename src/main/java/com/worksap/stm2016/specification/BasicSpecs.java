package com.worksap.stm2016.specification;

import com.worksap.stm2016.api.util.JsonArrayResponse;
import org.json.simple.JSONObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static org.springframework.data.jpa.domain.Specifications.where;

/**
 * Created by Shuang on 4/19/2016.
 */
public class BasicSpecs {

    public static <S, T> Specification<T> isValue(String c_name, S value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.equal(root.get(c_name), value);
            }
        };
    }

    public static <S, T> Specification<T> isValue(String c_name, String cc_name, S value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.equal(root.get(c_name).get(cc_name), value);
            }
        };
    }

    public static <S, T> Specification<T> isValue(String c_name, String cc_name, String ccc_name, S value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.equal(root.get(c_name).get(cc_name).get(ccc_name), value);
            }
        };
    }

    public static <T> Specification<T> hasValue(String c_name, String value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.like(
                        builder.lower(
                                root.get(c_name)
                        ), "%" + value.toLowerCase() + "%"
                );
            }
        };
    }

    public static <T> Specification<T> hasValue(String c_name, String cc_name, String value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.like(
                        builder.lower(
                                root.get(c_name).get(cc_name)
                        ), "%" + value.toLowerCase() + "%"
                );
            }
        };
    }

    public static <S, T> Specification<T> isNotValue(String c_name, S value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.notEqual(root.get(c_name), value);
            }
        };
    }

    public static <S, T> Specification<T> inValue(String c_name, Collection<S> list) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return root.get(c_name).in(list);
            }
        };
    }
    public static <T> Specification<T> gtDate(String c_name, Date value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThan(root.get(c_name), value);
            }
        };
    }
    public static <T> Specification<T> ltDate(String c_name, Date value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.lessThan(root.get(c_name), value);
            }
        };
    }
    public static <T> Specification<T> geDate(String c_name, Date value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.greaterThanOrEqualTo(root.get(c_name), value);
            }
        };
    }
    public static <T> Specification<T> leDate(String c_name, Date value) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.lessThanOrEqualTo(root.get(c_name), value);
            }
        };
    }

    public static <T> Specification<T> betweenDates(String c_name, Date from, Date to) {
        return new Specification<T>() {
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query,
                                         CriteriaBuilder builder) {
                return builder.between(root.get(c_name), from, to);
            }
        };
    }

    public static <T extends PagingAndSortingRepository & JpaSpecificationExecutor> JsonArrayResponse andFilter(
            String sort, String order, Integer limit, Integer offset, String filter, ArrayList<Specification> specs, T repository) {
        Integer page = offset / limit;
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, limit, direction, sort);

        List rows;
        Long count;

        if (filter != null) {
            Specifications andSpecs = null;
            for (int i = 0; i < specs.size(); i++) {
                if (i == 0) {
                    andSpecs = where(specs.get(i));
                } else {
                    andSpecs = andSpecs.and(specs.get(i));
                }
            }
            rows = repository.findAll(andSpecs, pageable).getContent();
            count = repository.count(andSpecs);

        } else {
            rows = repository.findAll(pageable).getContent();
            count = repository.count();
        }

        return new JsonArrayResponse(rows, count);
    }

    public static <T extends PagingAndSortingRepository & JpaSpecificationExecutor> JsonArrayResponse orFilter(
            String sort, String order, Integer limit, Integer offset, String filter, ArrayList<Specification> specs, T repository) {
        Integer page = offset / limit;
        Sort.Direction direction = Sort.Direction.ASC;
        if (order.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = new PageRequest(page, limit, direction, sort);

        List rows;
        Long count;

        if (filter != null) {
            Specifications orSpecs = null;
            for (int i = 0; i < specs.size(); i++) {
                if (i == 0) {
                    orSpecs = where(specs.get(i));
                } else {
                    orSpecs = orSpecs.or(specs.get(i));
                }
            }
            rows = repository.findAll(orSpecs, pageable).getContent();
            count = repository.count(orSpecs);

        } else {
            rows = repository.findAll(pageable).getContent();
            count = repository.count();
        }

        return new JsonArrayResponse(rows, count);
    }

    public static <T extends PagingAndSortingRepository & JpaSpecificationExecutor> List andFilterRows(
            ArrayList<Specification> specs, T repository) {

        Specifications andSpecs = null;
        for (int i = 0; i < specs.size(); i++) {
            if (i == 0) {
                andSpecs = where(specs.get(i));
            } else {
                andSpecs = andSpecs.or(specs.get(i));
            }
        }
        return repository.findAll(andSpecs);
    }

    public static <T extends PagingAndSortingRepository & JpaSpecificationExecutor> Long andFilterCount(
            ArrayList<Specification> specs, T repository) {

        Specifications andSpecs = null;
        for (int i = 0; i < specs.size(); i++) {
            if (i == 0) {
                andSpecs = where(specs.get(i));
            } else {
                andSpecs = andSpecs.or(specs.get(i));
            }
        }
        return repository.count(andSpecs);
    }
}