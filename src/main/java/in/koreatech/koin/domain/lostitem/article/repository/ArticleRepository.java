package in.koreatech.koin.domain.lostitem.article.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import in.koreatech.koin.domain.lostitem.article.model.Article;

public interface ArticleRepository extends Repository<Article, Integer> {

    @Query("SELECT a.title FROM Article a WHERE a.id = :id")
    String getTitleById(@Param("id") Integer id);
}
