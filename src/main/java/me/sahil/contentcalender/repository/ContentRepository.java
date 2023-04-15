package me.sahil.contentcalender.repository;

import me.sahil.contentcalender.model.Content;
import me.sahil.contentcalender.model.Status;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContentRepository extends ListCrudRepository<Content,Integer> {
 List<Content> findAllByTitleContains(String keyword);

 @Query(
         """       
         select * from content where status = :status
         """
 )
 List<Content> listByStatus(@Param("status") Status status);
}
