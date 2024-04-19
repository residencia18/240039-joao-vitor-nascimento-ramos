package br.com.cepedi.Business.api.service.employee;

import br.com.cepedi.Business.api.model.entitys.*;
import br.com.cepedi.Business.api.model.records.employee.details.DataDetailsEmployee;
import br.com.cepedi.Business.api.model.records.employee.input.DataRegisterEmployee;
import br.com.cepedi.Business.api.model.records.employee.input.DataUpdateEmployee;
import br.com.cepedi.Business.api.repository.EmployeeRepository;
import br.com.cepedi.Business.api.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    public DataDetailsEmployee register(@Valid DataRegisterEmployee data) {

        Role role = roleRepository.getReferenceById(data.id_role());
        Employee employee = new Employee(data,role);
        employeeRepository.save(employee);
        return new DataDetailsEmployee(employee);
    }

    public Page<DataDetailsEmployee> list(Pageable pageable) {
        return employeeRepository.findAllByActivatedTrue(pageable).map(DataDetailsEmployee::new);
    }

    public DataDetailsEmployee findById(Long id) {
        Employee employee  = employeeRepository.getReferenceById(id);
        return new DataDetailsEmployee(employee);
    }

    public DataDetailsEmployee update(Long id , DataUpdateEmployee data) {


        Role role = null;

        Employee employee  = employeeRepository.getReferenceById(id);

        if(data.idRole() != null){
            role = roleRepository.getReferenceById(data.idRole());
        }

        employee.updateData(data,role);
        return new DataDetailsEmployee(employee);
    }

    public void disabled(Long id){
        Employee employee = employeeRepository.getReferenceById(id);
        employee.logicalDelete();
    }

}
