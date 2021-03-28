package javatestweabapp.backendspringboot.repo;

import javatestweabapp.backendspringboot.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    List<Priority> findAllByOrderByIdAsc();
}
