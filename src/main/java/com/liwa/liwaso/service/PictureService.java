package com.liwa.liwaso.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwa.liwaso.model.entity.Picture;

public interface PictureService {

    Page<Picture> searchPicture(String searchText, long pageNum, long pageSize);

}
