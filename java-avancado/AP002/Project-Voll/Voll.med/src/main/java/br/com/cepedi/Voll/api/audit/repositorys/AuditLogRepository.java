package br.com.cepedi.Voll.api.audit.repositorys;

import br.com.cepedi.Voll.api.audit.entitys.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {
}