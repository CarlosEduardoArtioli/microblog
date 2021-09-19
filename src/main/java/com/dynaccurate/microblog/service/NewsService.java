package com.dynaccurate.microblog.service;

import com.dynaccurate.microblog.dto.NewNewsDTO;
import com.dynaccurate.microblog.model.News;
import com.dynaccurate.microblog.repository.NewsRepository;
import com.dynaccurate.microblog.repository.TagRepository;
import com.dynaccurate.microblog.repository.UserRepository;
import com.dynaccurate.microblog.service.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TagRepository tagRepository;

    public List<News> ListAll() {
        return newsRepository.findAll();
    }

    public News findById(Integer id) {
        Optional<News> news = newsRepository.findById(id);
        return news.orElseThrow(() -> new ObjectNotFoundException("News not found! Id: " + id + " Type: " + News.class.getName()));
    }

    public News insert(News news) {
        news.setId(null);
        news.setUser(userRepository.getById(news.getUser().getId()));
        tagRepository.saveAll(news.getTags());
        return newsRepository.save(news);
    }

    public News update(int id, News news) {
        News newsSave = findById(id);
        BeanUtils.copyProperties(news, newsSave, "id");
        return newsRepository.save(news);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            newsRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir pois há comentários relacionados");
        }
    }

    public News fromDTO(NewNewsDTO newNewsDTO) {
        return new News(newNewsDTO.getTitle(), newNewsDTO.getContent(), newNewsDTO.getUser());
    }

}
