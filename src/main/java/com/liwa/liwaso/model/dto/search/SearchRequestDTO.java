package com.liwa.liwaso.model.dto.search;

import com.liwa.liwaso.common.PageRequest;
import lombok.Data;

import java.io.Serializable;

@Data
public class SearchRequestDTO extends PageRequest implements Serializable {

    /**
     * 搜索词
     */
    private String searchText;

    private static final long serialVersionUID = 1L;
}