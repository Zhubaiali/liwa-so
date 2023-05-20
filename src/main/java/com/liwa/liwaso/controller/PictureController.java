package com.liwa.liwaso.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.liwa.liwaso.common.BaseResponse;
import com.liwa.liwaso.common.ErrorCode;
import com.liwa.liwaso.common.ResultUtils;
import com.liwa.liwaso.exception.ThrowUtils;
import com.liwa.liwaso.model.dto.picture.PictureQueryRequest;
import com.liwa.liwaso.model.entity.Picture;
import com.liwa.liwaso.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description 图片接口
 * @author liwa
 * @date 2023-05-20 23:44
 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    private final static Gson GSON = new Gson();

    /**
     * 分页获取图片列表
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/search/page/vo")
    public BaseResponse<Page<Picture>> listPictureByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                         HttpServletRequest request) {

        long current = pictureQueryRequest.getCurrent();
        long size = pictureQueryRequest.getPageSize();

        // 限制爬虫
        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
        String searchText = pictureQueryRequest.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, current, size);
        return ResultUtils.success(picturePage);

    }

}
