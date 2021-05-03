package cse312.demo.Dao;

import cse312.demo.Model.Chat;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatRepository extends MongoRepository<Chat, String> {
//    User findByUserName(String username);
}
