package com.epicode.specifications;

import java.time.LocalDate;

import org.springframework.data.jpa.domain.Specification;

import com.epicode.models.Customer;

public class CustomerSpecifications {

    public static Specification<Customer> legalNameContaining(String name) {
        return (root, query, builder) ->
                builder.like(builder.lower(root.get("legalName")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Customer> annualIncomeBetween(Double minIncome, Double maxIncome) {
        return (root, query, builder) ->
                builder.between(root.get("annualIncome"), minIncome, maxIncome);
    }

    public static Specification<Customer> registrationDateBetween(LocalDate minDate, LocalDate maxDate) {
        return (root, query, builder) ->
                builder.between(root.get("registrationDate"), minDate, maxDate);
    }

    public static Specification<Customer> lastContactDateBetween(LocalDate minDate, LocalDate maxDate) {
        return (root, query, builder) ->
                builder.between(root.get("lastContactDate"), minDate, maxDate);
    }
}
