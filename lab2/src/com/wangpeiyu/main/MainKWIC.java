package com.wangpeiyu.main;

import java.io.File;
import java.io.IOException;

public class MainKWIC {
    public static void run(File inFile, File outFile) throws IOException {
        System.out.println("采用主程序-子程序软件体系结构");
        demo1 kwic = new demo1();
        kwic.input(inFile.getPath());
        kwic.shift();
        kwic.alphabetizer();
        kwic.output(outFile.getPath());
    }
}

