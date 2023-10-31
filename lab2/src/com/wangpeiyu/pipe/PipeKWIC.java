package com.wangpeiyu.pipe;

import java.io.File;
import java.io.IOException;

public class PipeKWIC {
    public static void run(File inFile, File outFile) throws IOException {
        System.out.println("采用管道-过滤软件体系结构");
        Pipe pipe1 = new Pipe();
        Pipe pipe2 = new Pipe();
        Pipe pipe3 = new Pipe();
        Input input = new Input(inFile, pipe1);
        Shift shift = new Shift(pipe1, pipe2);
        Alphabetizer alphabetizer = new Alphabetizer(pipe2, pipe3);
        Output output = new Output(outFile, pipe3);

        input.transform();
        shift.transform();
        alphabetizer.transform();
        output.transform();
    }
}
