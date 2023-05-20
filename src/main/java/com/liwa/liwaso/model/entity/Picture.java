package com.liwa.liwaso.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @description  图片
 * @author liwa
 * @date 2023-05-20 23:37
 */
@Data
public class Picture implements Serializable {

    private String title;

    private String url;

    private static final long serialVersionUID = 1L;

}
