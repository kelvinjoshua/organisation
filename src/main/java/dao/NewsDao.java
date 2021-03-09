package dao;
import  models.*;

import java.util.List;

public interface NewsDao {
    void add (News news);

    News findByiId(int id);
    List<News> getAllNews();
    /*update*/
    void updateNews(int id, String news_name, String news_content, int dpt_id);
    /*delete*/
    void deleteById(int id);
    void clearAll();

}
