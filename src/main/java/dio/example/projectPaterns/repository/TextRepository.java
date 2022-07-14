package dio.example.projectPaterns.repository;

import dio.example.projectPaterns.model.TextEntity;
import org.springframework.data.repository.CrudRepository;

public interface TextRepository extends CrudRepository<TextEntity, Long> {
}
