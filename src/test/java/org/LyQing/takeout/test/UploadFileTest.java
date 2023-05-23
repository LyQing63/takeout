package org.LyQing.takeout.test;

import org.junit.jupiter.api.Test;

/**
 * 上传文件测试
 *
 * @author yjxx_2022
 * @date 2023/05/23
 */
public class UploadFileTest {

    @Test
    public void test1() {
        String fileName = "asd.jpg";
        String suffix = fileName.substring(fileName.lastIndexOf("."));
        System.out.println(suffix);
    }

}
