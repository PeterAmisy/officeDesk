package net.wevii.officeDesk.repository;

import net.wevii.officeDesk.domain.Office;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends CrudRepository<Office, Long> {
}
