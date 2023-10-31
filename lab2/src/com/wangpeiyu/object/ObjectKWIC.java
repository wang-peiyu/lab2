package com.wangpeiyu.object;

import java.io.File;
import java.io.IOException;

public class ObjectKWIC {
    public static void run(File inFile, File outFile) throws IOException {
        System.out.println("采用面向对象软件体系结构");
        Input input = new Input();
        input.input(inFile.getPath());
        Shift shift = new Shift(input.getLineTxt());
        shift.shift();
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        alphabetizer.sort();
        Output output = new Output(alphabetizer.getKwicList());
        output.output(outFile.getPath());
    }
}
