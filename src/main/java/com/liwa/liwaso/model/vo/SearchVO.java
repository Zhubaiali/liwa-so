package com.liwa.liwaso.model.vo;

import com.liwa.liwaso.model.entity.Picture;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @description 聚合搜索
 * @author liwa
 * @date 2023-05-21 13:28
 */
@Data
public class SearchVO implements Serializable {

    private List<UserVO> userList;

    private List<PostVO> postList;

    private List<Picture> pictureList;

    private static final long serialVersionUID = 1L;

}
