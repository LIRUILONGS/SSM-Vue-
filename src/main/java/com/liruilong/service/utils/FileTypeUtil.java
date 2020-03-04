package com.liruilong.service.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description :
 * 1：图片
 * 2：文档 可进行细分
 * 3：视频
 * 4：音频
 * @Author: Liruilong
 * @Date: 2020/3/2 20:23
 */
public class FileTypeUtil {
    public final static Map<String, Integer> FILE_TYPE_MAP = new HashMap<String, Integer>();

    private FileTypeUtil() {}

    public static String getType(String url) {
        url = url.substring(url.indexOf(".")+1);
        Integer i = FILE_TYPE_MAP.get(url.toLowerCase()) == null ? 0 :FILE_TYPE_MAP.get(url.toLowerCase());

        switch (i) {
            case 1:
                return "图片";
            case 2:
                return "文档";
            case 3:
                return "视频";
            case 4:
                return "音频";
            default: {
                return "未知类型";
            }
        }
    }
    static
    {
        FILE_TYPE_MAP.put("bmp", 1);
        FILE_TYPE_MAP.put("jpg", 1);
        FILE_TYPE_MAP.put("jpeg", 1);
        FILE_TYPE_MAP.put("png", 1);
        FILE_TYPE_MAP.put("tiff", 1);
        FILE_TYPE_MAP.put("gif", 1);
        FILE_TYPE_MAP.put("pcx", 1);
        FILE_TYPE_MAP.put("tga", 1);
        FILE_TYPE_MAP.put("exif", 1);
        FILE_TYPE_MAP.put("fpx", 1);
        FILE_TYPE_MAP.put("svg", 1);
        FILE_TYPE_MAP.put("psd", 1);
        FILE_TYPE_MAP.put("cdr", 1);
        FILE_TYPE_MAP.put("pcd", 1);
        FILE_TYPE_MAP.put("dxf", 1);
        FILE_TYPE_MAP.put("ufo", 1);
        FILE_TYPE_MAP.put("eps", 1);
        FILE_TYPE_MAP.put("ai", 1);
        FILE_TYPE_MAP.put("raw", 1);
        FILE_TYPE_MAP.put("wmf", 1);
        FILE_TYPE_MAP.put("txt", 2);
        FILE_TYPE_MAP.put("doc", 2);
        FILE_TYPE_MAP.put("docx", 2);
        FILE_TYPE_MAP.put("xls", 2);
        FILE_TYPE_MAP.put("htm", 2);
        FILE_TYPE_MAP.put("html", 2);
        FILE_TYPE_MAP.put("jsp", 2);
        FILE_TYPE_MAP.put("rtf", 2);
        FILE_TYPE_MAP.put("wpd", 2);
        FILE_TYPE_MAP.put("pdf", 2);
        FILE_TYPE_MAP.put("ppt", 2);
        FILE_TYPE_MAP.put("mp4", 3);
        FILE_TYPE_MAP.put("avi", 3);
        FILE_TYPE_MAP.put("mov", 3);
        FILE_TYPE_MAP.put("wmv", 3);
        FILE_TYPE_MAP.put("asf", 3);
        FILE_TYPE_MAP.put("navi", 3);
        FILE_TYPE_MAP.put("3gp", 3);
        FILE_TYPE_MAP.put("mkv", 3);
        FILE_TYPE_MAP.put("f4v", 3);
        FILE_TYPE_MAP.put("rmvb", 3);
        FILE_TYPE_MAP.put("webm", 3);
        FILE_TYPE_MAP.put("mp3", 4);
        FILE_TYPE_MAP.put("wma", 4);
        FILE_TYPE_MAP.put("wav", 4);
        FILE_TYPE_MAP.put("mod", 4);
        FILE_TYPE_MAP.put("ra", 4);
        FILE_TYPE_MAP.put("cd", 4);
        FILE_TYPE_MAP.put("md", 4);
        FILE_TYPE_MAP.put("asf", 4);
        FILE_TYPE_MAP.put("aac", 4);
        FILE_TYPE_MAP.put("vqf", 4);
        FILE_TYPE_MAP.put("ape", 4);
        FILE_TYPE_MAP.put("mid", 4);
        FILE_TYPE_MAP.put("ogg", 4);
        FILE_TYPE_MAP.put("m4a", 4);
        FILE_TYPE_MAP.put("vqf", 4);
    }
}
