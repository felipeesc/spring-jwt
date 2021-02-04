package br.com.suritec.desafio.repository;

import br.com.suritec.desafio.domain.OperationControl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface OperationRepository extends JpaRepository<OperationControl, Long>, JpaSpecificationExecutor<OperationControl> {

}
