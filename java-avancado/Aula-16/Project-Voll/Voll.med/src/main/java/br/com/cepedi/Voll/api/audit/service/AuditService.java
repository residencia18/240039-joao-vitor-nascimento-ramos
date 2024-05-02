package br.com.cepedi.Voll.api.audit.service;

import br.com.cepedi.Voll.api.audit.entitys.AuditLog;
import br.com.cepedi.Voll.api.audit.record.DataRegisterAudit;
import br.com.cepedi.Voll.api.audit.repositorys.AuditLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditService {
    @Autowired
    private AuditLogRepository auditLogRepository;

    public void logEvent(DataRegisterAudit data) {
        AuditLog log = new AuditLog(data);
        auditLogRepository.save(log);
    }
}