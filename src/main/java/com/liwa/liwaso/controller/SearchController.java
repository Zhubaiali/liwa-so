package com.liwa.liwaso.controller;

import com.liwa.liwaso.common.BaseResponse;
import com.liwa.liwaso.common.ResultUtils;
import com.liwa.liwaso.mapper.SearchFacade;
import com.liwa.liwaso.model.dto.search.SearchRequestDTO;
import com.liwa.liwaso.model.vo.SearchVO;
import com.liwa.liwaso.service.PictureService;
import com.liwa.liwaso.service.PostService;
import com.liwa.liwaso.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description
 * @author liwa
 * @date 2023-05-21 13:10
 */

@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private UserService userService;

    @Resource
    private PostService postService;

    @Resource
    private PictureService pictureService;

    @Resource
    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequestDTO searchRequestDTO, HttpServletRequest request) {

        return ResultUtils.success(searchFacade.searchAll(searchRequestDTO, request));

    }
}
