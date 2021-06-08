package evaluation.candidate.restapi2.data.persistance;

import evaluation.candidate.restapi2.data.dao.ClientDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ClientRepository extends JpaRepository<ClientDao, Integer> {

    @Query(value = "FROM ClientDao i where i.id = :id")
    ClientDao findByIdString(@Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM ClientDao i where i.id = :id")
    void deleteById(@Param("id") int id);



}
