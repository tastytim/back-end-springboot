package javatestweabapp.backendspringboot.repo;

import javatestweabapp.backendspringboot.entity.Stat;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StatRepository extends CrudRepository<Stat, Long> {

}
