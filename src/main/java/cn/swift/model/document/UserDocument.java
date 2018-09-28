package cn.swift.model.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(indexName = "user", type = "userDocument")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDocument {

  @Id
  private String id;

  private String username;
}
