package com.dynaccurate.microblog.resource.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StandardError {

    private Integer status;

    private String msg;

    private Long timestamp;

}
