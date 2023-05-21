package com.liwa.liwaso.service.impl;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liwa.liwaso.common.ErrorCode;
import com.liwa.liwaso.exception.BusinessException;
import com.liwa.liwaso.model.entity.Picture;
import com.liwa.liwaso.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 /**
  * @description 图片服务实现类
  * @author liwa 
  * @date 2023-05-21 00:26
  */

 @Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<Picture> searchPicture(String searchText, long pageNum, long pageSize) {
        long current = (pageNum - 1)*pageSize;
        String url =String.format("https://cn.bing.com/images/search?q=%s&first=%s", searchText, current);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "数据获取异常");
        }
        Elements elements = doc.select(".iuscp.isv"); // iuscp.isv是在网页检查元素找到的css选择器，刚好把我们需要的数据选择了。
        List<Picture> pictures = new ArrayList<>();
        for (Element element : elements) {
            if(pictures.size()>= pageSize) break;
            // 取图片地址（murl）
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
//            System.out.println(murl);
            // 取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
//            System.out.println(title);
            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictures.add(picture);
        }
        
        // 把list封装成page ， 保持和别的接口返回的数据结构一致
        Page<Picture> page = new Page<>(pageNum, pageSize);
        page.setRecords(pictures);

        return page;
    }
}
