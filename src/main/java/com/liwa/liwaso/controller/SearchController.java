package com.liwa.liwaso.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwa.liwaso.common.BaseResponse;
import com.liwa.liwaso.common.ResultUtils;
import com.liwa.liwaso.model.dto.post.PostQueryRequest;
import com.liwa.liwaso.model.dto.search.SearchRequestDTO;
import com.liwa.liwaso.model.dto.user.UserQueryRequest;
import com.liwa.liwaso.model.entity.Picture;
import com.liwa.liwaso.model.vo.PostVO;
import com.liwa.liwaso.model.vo.SearchVO;
import com.liwa.liwaso.model.vo.UserVO;
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

//    @Resource
//    private SearchFacade searchFacade;

    @PostMapping("/all")
    public BaseResponse<SearchVO> searchAll(@RequestBody SearchRequestDTO searchRequestDTO, HttpServletRequest request) {
        String searchText = searchRequestDTO.getSearchText();
        Page<Picture> picturePage = pictureService.searchPicture(searchText, 1, 10);

        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);

        PostQueryRequest postQueryRequest = new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest , request);

        SearchVO searchVO = new SearchVO();
        searchVO.setUserList(userVOPage.getRecords());
        searchVO.setPostList(postVOPage.getRecords());
        searchVO.setPictureList(picturePage.getRecords());
        return  ResultUtils.success(searchVO);
    }
    


}
