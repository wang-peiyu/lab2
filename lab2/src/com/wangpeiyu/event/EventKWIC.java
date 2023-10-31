package com.wangpeiyu.event;

import java.io.File;
import java.io.IOException;

public class EventKWIC {
    public static void run(File inFile, File outFile) throws IOException {
        System.out.println("采用事件系统软件体系结构");
        KWICSubject kwicSubject = new KWICSubject();
        Input input = new Input(inFile.getPath());
        Shift shift = new Shift(input.getLineTxt());
        Alphabetizer alphabetizer = new Alphabetizer(shift.getKwicList());
        Output output = new Output(alphabetizer.getKwicList(), outFile.getPath());

        kwicSubject.addObserver(input);
        kwicSubject.addObserver(shift);
        kwicSubject.addObserver(alphabetizer);
        kwicSubject.addObserver(output);

        kwicSubject.startKWIC();
    }
}
