package com.worksap.stm2016.specification;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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

}