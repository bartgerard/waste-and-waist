package be.ww.user.repository;


import be.ww.user.service.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

public interface UserRepository extends ElasticsearchRepository<UserDocument, String> {
    Optional<UserDocument> findUserByUsername(String username);
}
