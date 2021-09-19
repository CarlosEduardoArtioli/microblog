package com.dynaccurate.microblog.service;

import com.dynaccurate.microblog.dto.NewTagDTO;
import com.dynaccurate.microblog.model.Tag;
import com.dynaccurate.microblog.repository.TagRepository;
import com.dynaccurate.microblog.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public Tag findById(Integer id) {
        Optional<Tag> tag = tagRepository.findById(id);
        return tag.orElseThrow(() -> new ObjectNotFoundException("Tag not found! Id: " + id + " Type: " + Tag.class.getName()));
    }

    public Tag insert(NewTagDTO newTagDTO) {
        Tag tag = new Tag();
        tag.setName(newTagDTO.getName());
        return tagRepository.save(tag);
    }

    public void delete(Integer id) {
        findById(id);
        try {
            tagRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Não é possível excluir pois há itens relacionados");
        }
    }
}
