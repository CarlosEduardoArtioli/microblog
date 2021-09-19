package com.dynaccurate.microblog.dto;

import com.dynaccurate.microblog.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewNewsDTO {

    private String title;

    private String content;

    private User user;

    private List<NewTagDTO> tags;
}
