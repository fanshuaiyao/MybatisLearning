package org.fan.utils;

import java.io.InputStream;

/**
 * 工具类  专门加载类路径的资源
 *
 * @author fanshuaiyao
 * @version 1.0
 */
public class Resources {

    /**
     * 工具类的构造方法一般都是私有化，因为我们的方法都是通过静态调用 不需要去创建实例
     */
    private Resources() {
    }


    /**
     * 通过文件路径获取文件的输入流（从类路径加载）
     *
     * @param resource 资源路径
     * @return 指向输入文件的输入流
     */
    public static InputStream getResourceAsStream(String resource) {
        return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
    }


}
