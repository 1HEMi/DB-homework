package ru.knyazev.rgr.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.knyazev.rgr.models.Supplier;
import ru.knyazev.rgr.services.SuppliersService;

@Component
public class SupplierValidator implements Validator {

    private final SuppliersService suppliersService;

    @Autowired
    public SupplierValidator(SuppliersService suppliersService) {
        this.suppliersService = suppliersService;
    }

    @Override
    public boolean supports(Class<?> aClass) { return Supplier.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {

        Supplier supplier = (Supplier) target;
    }
}

