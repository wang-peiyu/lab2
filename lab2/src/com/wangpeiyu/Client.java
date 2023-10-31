package com.wangpeiyu;

import com.wangpeiyu.main.MainKWIC;
import com.wangpeiyu.object.ObjectKWIC;
import com.wangpeiyu.event.EventKWIC;
import com.wangpeiyu.pipe.PipeKWIC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static String readFileContent(File file) throws IOException {
        StringBuilder content = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        reader.close();
        return content.toString();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("经典软件体系结构教学软件");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int windowWidth = 400;
        int windowHeight = 700;
        frame.setSize(windowWidth, windowHeight);
        // 获取屏幕尺寸
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // 计算窗口在屏幕中央的位置
        int x = (screenSize.width - windowWidth) / 2;
        int y = (screenSize.height - windowHeight) / 2;

        // 设置窗口位置
        frame.setLocation(x, y);
        JPanel panel = new JPanel();
        frame.add(panel);

        JLabel label = new JLabel("请选择要使用的软件体系结构:");
        panel.add(label);

        JComboBox<String> architectureComboBox = new JComboBox<>();
        architectureComboBox.addItem("主程序-子程序软件体系");
        architectureComboBox.addItem("面向对象软件体系");
        architectureComboBox.addItem("事件系统软件体系");
        architectureComboBox.addItem("管道-过滤软件体系");
        panel.add(architectureComboBox);

        JButton runButton = new JButton("运行");
        panel.add(runButton);

        // 添加文本框以显示input.txt的内容
        JTextArea inputTextArea = new JTextArea(17, 35);
        inputTextArea.setEditable(false);
        panel.add(new JScrollPane(inputTextArea));

        JTextArea resultTextArea = new JTextArea(17, 35);
        resultTextArea.setEditable(false);
        panel.add(new JScrollPane(resultTextArea));

        runButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resultTextArea.setText(""); // 清空文本区域

                File inFile = new File("D:\\test\\input.txt");
                File outFile = new File("D:\\test\\output.txt");
                String selectedArchitecture = (String) architectureComboBox.getSelectedItem();

                try {
                    // 读取input.txt的内容并显示在文本框中
                    String inputContent = readFileContent(inFile);
                    inputTextArea.setText("输入内容为：\n" + inputContent);

                    String result = runSelectedArchitecture(selectedArchitecture, inFile, outFile);
                    resultTextArea.setText(result);

                    if (outFile.exists()) {
                        String fileContent = readFileContent(outFile);
                        resultTextArea.append("\n\n输出结果为：\n" + fileContent);
                    }
                } catch (IOException ex) {
                    resultTextArea.setText("发生错误：" + ex.getMessage());
                }
            }
        });

        frame.setVisible(true);
    }

    private static String runSelectedArchitecture(String architecture, File inFile, File outFile) throws IOException {
        switch (architecture) {
            case "主程序-子程序软件体系":
                MainKWIC.run(inFile, outFile);
                break;
            case "面向对象软件体系":
                ObjectKWIC.run(inFile, outFile);
                break;
            case "事件系统软件体系":
                EventKWIC.run(inFile, outFile);
                break;
            case "管道-过滤软件体系":
                PipeKWIC.run(inFile, outFile);
                break;
            default:
                return "无效的软件体系结构选择。";
        }

        return "运行完成。采用的是"+architecture+"\n"+"结果已保存到 " + outFile.getPath();
    }
}
