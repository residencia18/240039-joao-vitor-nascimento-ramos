package br.com.cepedi.Business.api.service.supplier;


import br.com.cepedi.Business.api.model.entitys.Supplier;
import br.com.cepedi.Business.api.model.records.supplier.details.DataDetailsSupplier;
import br.com.cepedi.Business.api.model.records.supplier.input.DataRegisterSupplier;
import br.com.cepedi.Business.api.repository.SupplierRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

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


}
