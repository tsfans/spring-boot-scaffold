package cn.swift.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import cn.swift.model.document.UserDocument;

@Repository
public interface UserRepository extends ElasticsearchRepository<UserDocument, String> {

}
