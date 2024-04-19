package br.com.cepedi.Business.api.service.supplier;


import br.com.cepedi.Business.api.model.entitys.Client;
import br.com.cepedi.Business.api.model.entitys.Supplier;
import br.com.cepedi.Business.api.model.records.client.details.DataDetailsClient;
import br.com.cepedi.Business.api.model.records.client.input.DataUpdateClient;
import br.com.cepedi.Business.api.model.records.supplier.details.DataDetailsSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataRegisterSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataUpdateSupplier;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import br.com.cepedi.Business.api.service.supplier.validations.disabled.ValidateDisabledSupplier;
import br.com.cepedi.Business.api.service.supplier.validations.update.ValidateUpdateSupplier;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private List<ValidateDisabledSupplier> validationsDisabled;

    @Autowired
    private List<ValidateUpdateSupplier> validationsUpdate;

    public DataDetailsSupplier register(@Valid DataRegisterSupplier data) {
        Supplier supplier = new Supplier(data);
        supplierRepository.save(supplier);
        return new DataDetailsSupplier(supplier);
    }

    public Page<DataDetailsSupplier> list(Pageable pageable) {
        return supplierRepository.findAllByActivatedTrue(pageable).map(DataDetailsSupplier::new);
    }

    public DataDetailsSupplier findById(Long id) {
        Supplier supplier = supplierRepository.getReferenceById(id);
        return new DataDetailsSupplier(supplier);
    }

    public DataDetailsSupplier update(Long id , DataUpdateSupplier data) {
        validationsUpdate.forEach(v -> v.validation(id,data));
        Supplier supplier = supplierRepository.getReferenceById(id);
        supplier.updateData(data);
        return new DataDetailsSupplier(supplier);
    }

    public void disabled(Long id){
        validationsDisabled.forEach(v -> v.validation(id));
        Supplier supplier = supplierRepository.getReferenceById(id);
        supplier.logicalDelete();
    }


}
