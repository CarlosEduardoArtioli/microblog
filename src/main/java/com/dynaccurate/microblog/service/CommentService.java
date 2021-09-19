package com.dynaccurate.microblog.service;


import com.dynaccurate.microblog.dto.NewCommentDTO;
import com.dynaccurate.microblog.model.Comment;
import com.dynaccurate.microblog.model.News;
import com.dynaccurate.microblog.model.User;
import com.dynaccurate.microblog.repository.CommentRepository;
import com.dynaccurate.microblog.repository.NewsRepository;
import com.dynaccurate.microblog.repository.UserRepository;
import com.dynaccurate.microblog.service.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private NewsRepository newsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    public Comment findById(Integer id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.orElseThrow(() -> new ObjectNotFoundException("Comment not found! Id: " + id + " Type: " + Comment.class.getName()));
    }

    public Comment insert(Comment comment) {
        comment.setId(null);
        comment.setUser(userRepository.getById(comment.getUser().getId()));
        comment.setNews(newsRepository.getById(comment.getNews().getId()));
        return commentRepository.save(comment);
    }

    public Comment update(int id, Comment comment) {
        Comment commentSave = findById(id);
        BeanUtils.copyProperties(comment, commentSave, "id");
        return commentRepository.save(comment);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            commentRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir pois há itens relacionados");
        }
    }

    public Comment fromDTO(NewCommentDTO newCommentDTO) {
        News news = newsRepository.getById(newCommentDTO.getIdNews());
        User user = userRepository.getById(newCommentDTO.getIdUser());
        return new Comment(newCommentDTO.getContent(), user, news);
    }
}
