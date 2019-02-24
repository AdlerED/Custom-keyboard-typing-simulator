import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/*
Adler@QQ/WX1101635162
这原本是个给QQ自动登陆的程序
请先在RunQQ的第5行设置桌面上的一个程序名(比如下面写的1.txt打开的是记事本)
如果不设置,往哪儿打印?
字符处理的可能不全，可以自行理解并添加
无法处理的字符会在控制台报错,但你是可以自己进行修复的
感觉按出来的速度慢是因为特意的，让人觉得有逼格
可以在注释中找到修改延迟的地方
由于大写需要占用一个键位,所以占用了"`"这个不常用的符号,可自行修改
或希望大佬可以实现不占用键位实现功能...
18/10/03 修改了代码用于平衡大写字母需要自动按Caps键 但小写字母不需要按Caps键所造成的延迟差
*/

public class main {
    /*
    设置模拟按键速度
    */
    public static int Speed = 25;
    /*
    设置运行程序后等待多长时间开始输入
    一些软件开启的比较慢,可以把延迟稍微放高
     */
    public static int WaitTime = 350;

    public static void main(String[] args) throws AWTException {
        RunQQ();
        ExeQQ();
    }

    public static void RunQQ() {
        FileSystemView fsv = FileSystemView.getFileSystemView();
        File com = fsv.getHomeDirectory();  //读取桌面路径
        System.out.println(com.getPath());
        String filedir;
        filedir = com.getPath() + "\\1.txt"; //文件名 如果是快捷方式记得后缀名.lnk
        System.out.println(filedir);
        String cmd = "rundll32 url.dll FileProtocolHandler file://" + filedir;
        System.out.println(cmd);
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = null;
            process = runtime.exec(cmd);
        } catch (Exception e) {
            System.out.println("File opening error!");
        }
    }

    public static void ExeQQ() throws AWTException {
        Robot QQR = new Robot();
        /*try //延时 用来给程序时间开启
        {
            Thread.sleep(WaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        String Press = "!@#$%^&*()-=+[]\\|;:'\",<>?/_q w~~~ertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM_11016351624265090995120001030."; //密码(要模拟按键的内容)
        char[] PressArray = Press.toCharArray(); //转换String为char[]
        ArrayList list = new ArrayList();
        String caps = "`";
        char[] PressCaps = caps.toCharArray();
        for (int i = 0; i < PressArray.length; i++) { //大小写转换
            if (Character.isUpperCase(PressArray[i])) {
                list.add(PressCaps[0]);
                list.add(PressArray[i]);
                list.add(PressCaps[0]);
            } else {
                //PressArray[i] += 32;
                if (PressArray[i] >= 'a' && PressArray[i] <= 'z') {
                    PressArray[i] -= 32;
                    list.add(PressArray[i]);
                } else {
                    list.add(PressArray[i]);
                }
            }
            //System.out.println(PressArray[i]);
        }
        System.out.println("====待输入内容====");
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i)); //ArrayList用get(array)方法获取内容
        }
        System.out.println("\n===============");

       /* QQR.keyPress(KeyEvent.VK_A);
        QQR.keyRelease(KeyEvent.VK_A);
        QQR.keyPress(KeyEvent.VK_D);
        QQR.keyRelease(KeyEvent.VK_D);*/
        //Object obj = new Object();
        //arrayList转string
        try //延时 用来给程序时间开启
        {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //检测是否是win10 如果是 按shift切换输入法
        main haha = new main();
        boolean iswin = haha.isWindows10();
        if (iswin) {
            System.out.println("系统为Windows10,自动切换到英文输入法...");
            QQR.keyPress(KeyEvent.VK_SHIFT);
            QQR.keyRelease(KeyEvent.VK_SHIFT);
        }
        int status = 0;
        int errorint = 0;

        //确认数组
        ArrayList submit = new ArrayList();
        ArrayList submit2 = new ArrayList();
        //支持的字符列表
        String num = "_ADLER6`BCFGHIJKMNOPQSTUVWXYZ012345789. ~!@#$%^&*()-="; //0是下划线 英文必须大写
        String num2 = "+\\|;:'\",<>?/[]";
        char[] PressArr = num.toCharArray();
        char[] PressArr2 = num2.toCharArray();
        for (int i = 0; i < PressArr.length; i++) {
            submit.add(PressArr[i]);
        }
        for (int i = 0; i < PressArr2.length; i++) {
            submit2.add(PressArr2[i]);
        }


        System.out.println("====支持的字符====");
        System.out.println("支持库1:");
        for (int i = 0; i < submit.size(); i++) {
            System.out.print(submit.get(i)); //ArrayList用get(array)方法获取内容
        }
        System.out.println("\n支持库2:");
        for (int i = 0; i < submit2.size(); i++) {
            System.out.print(submit2.get(i)); //ArrayList用get(array)方法获取内容
        }
        System.out.println("\n===============");
        //System.out.println(list.get(0));

        System.out.print("等待程序启动");
        int FinalTime = WaitTime / 6;
        for (int i = 0; i < 6; i++) {
            try //延时
            {
                Thread.sleep(WaitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(".");
        }

        try //延时
        {
            Thread.sleep(WaitTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.print("\n开始输入");
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(submit.get(7))) { //大写(放在延迟之前,增加流畅度)
                QQR.keyPress(KeyEvent.VK_CAPS_LOCK);
                QQR.keyRelease(KeyEvent.VK_CAPS_LOCK);
                continue;
            }
            try //延时
            {
                Thread.sleep(Speed);//打字的延迟,越小模拟输入的越快
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(list.size()); //检测长度是否正常
            ////////////////////////////////////////////////////循环判断输入按键

           /*模板
            if (list.get(i).equals(submit.get()))  { //
               QQR.keyPress(KeyEvent.VK_);
               QQR.keyRelease(KeyEvent.VK_);
               status = 1; //设为1 不会输出报错
           }
            */

            //支持库1

            if (list.get(i).equals(submit.get(0))) { //下划线
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_MINUS);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                QQR.keyRelease(KeyEvent.VK_MINUS);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(1))) { //A
                QQR.keyPress(KeyEvent.VK_A);
                QQR.keyRelease(KeyEvent.VK_A);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(2))) { //D
                QQR.keyPress(KeyEvent.VK_D);
                QQR.keyRelease(KeyEvent.VK_D);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(3))) { //L
                QQR.keyPress(KeyEvent.VK_L);
                QQR.keyRelease(KeyEvent.VK_L);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(4))) { //E
                QQR.keyPress(KeyEvent.VK_E);
                QQR.keyRelease(KeyEvent.VK_E);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(5))) { //R
                QQR.keyPress(KeyEvent.VK_R);
                QQR.keyRelease(KeyEvent.VK_R);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(6))) { //6
                QQR.keyPress(KeyEvent.VK_6);
                QQR.keyRelease(KeyEvent.VK_6);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(8))) { //B
                QQR.keyPress(KeyEvent.VK_B);
                QQR.keyRelease(KeyEvent.VK_B);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(9))) { //C
                QQR.keyPress(KeyEvent.VK_C);
                QQR.keyRelease(KeyEvent.VK_C);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(10))) { //F
                QQR.keyPress(KeyEvent.VK_F);
                QQR.keyRelease(KeyEvent.VK_F);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(11))) { //G
                QQR.keyPress(KeyEvent.VK_G);
                QQR.keyRelease(KeyEvent.VK_G);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(12))) { //H
                QQR.keyPress(KeyEvent.VK_H);
                QQR.keyRelease(KeyEvent.VK_H);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(13))) { //I
                QQR.keyPress(KeyEvent.VK_I);
                QQR.keyRelease(KeyEvent.VK_I);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(14))) { //J
                QQR.keyPress(KeyEvent.VK_J);
                QQR.keyRelease(KeyEvent.VK_J);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(15))) { //K
                QQR.keyPress(KeyEvent.VK_K);
                QQR.keyRelease(KeyEvent.VK_K);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(16))) { //M
                QQR.keyPress(KeyEvent.VK_M);
                QQR.keyRelease(KeyEvent.VK_M);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(17))) { //N
                QQR.keyPress(KeyEvent.VK_N);
                QQR.keyRelease(KeyEvent.VK_N);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(18))) { //O
                QQR.keyPress(KeyEvent.VK_O);
                QQR.keyRelease(KeyEvent.VK_O);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(19))) { //P
                QQR.keyPress(KeyEvent.VK_P);
                QQR.keyRelease(KeyEvent.VK_P);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(20))) { //Q
                QQR.keyPress(KeyEvent.VK_Q);
                QQR.keyRelease(KeyEvent.VK_Q);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(21))) { //S
                QQR.keyPress(KeyEvent.VK_S);
                QQR.keyRelease(KeyEvent.VK_S);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(22))) { //T
                QQR.keyPress(KeyEvent.VK_T);
                QQR.keyRelease(KeyEvent.VK_T);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(23))) { //U
                QQR.keyPress(KeyEvent.VK_U);
                QQR.keyRelease(KeyEvent.VK_U);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(24))) { //V
                QQR.keyPress(KeyEvent.VK_V);
                QQR.keyRelease(KeyEvent.VK_V);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(25))) { //W
                QQR.keyPress(KeyEvent.VK_W);
                QQR.keyRelease(KeyEvent.VK_W);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(26))) { //X
                QQR.keyPress(KeyEvent.VK_X);
                QQR.keyRelease(KeyEvent.VK_X);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(27))) { //Y
                QQR.keyPress(KeyEvent.VK_Y);
                QQR.keyRelease(KeyEvent.VK_Y);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(28))) { //Z
                QQR.keyPress(KeyEvent.VK_Z);
                QQR.keyRelease(KeyEvent.VK_Z);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(29))) { //0
                QQR.keyPress(KeyEvent.VK_0);
                QQR.keyRelease(KeyEvent.VK_0);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(30))) { //1
                QQR.keyPress(KeyEvent.VK_1);
                QQR.keyRelease(KeyEvent.VK_1);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(31))) { //2
                QQR.keyPress(KeyEvent.VK_2);
                QQR.keyRelease(KeyEvent.VK_2);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(32))) { //3
                QQR.keyPress(KeyEvent.VK_3);
                QQR.keyRelease(KeyEvent.VK_3);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(33))) { //4
                QQR.keyPress(KeyEvent.VK_4);
                QQR.keyRelease(KeyEvent.VK_4);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(34))) { //5
                QQR.keyPress(KeyEvent.VK_5);
                QQR.keyRelease(KeyEvent.VK_5);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(35))) { //7
                QQR.keyPress(KeyEvent.VK_7);
                QQR.keyRelease(KeyEvent.VK_7);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(36))) { //8
                QQR.keyPress(KeyEvent.VK_8);
                QQR.keyRelease(KeyEvent.VK_8);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(37))) { //9
                QQR.keyPress(KeyEvent.VK_9);
                QQR.keyRelease(KeyEvent.VK_9);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(38))) { //.
                QQR.keyPress(KeyEvent.VK_PERIOD);
                QQR.keyRelease(KeyEvent.VK_PERIOD);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(39))) { //空格
                QQR.keyPress(KeyEvent.VK_SPACE);
                QQR.keyRelease(KeyEvent.VK_SPACE);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(40))) { //~
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_BACK_QUOTE);
                QQR.keyRelease(KeyEvent.VK_BACK_QUOTE);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(41))) { //!
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_1);
                QQR.keyRelease(KeyEvent.VK_1);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(42))) { //@
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_2);
                QQR.keyRelease(KeyEvent.VK_2);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(43))) { //#
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_3);
                QQR.keyRelease(KeyEvent.VK_3);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(44))) { //$
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_4);
                QQR.keyRelease(KeyEvent.VK_4);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(45))) { //%
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_5);
                QQR.keyRelease(KeyEvent.VK_5);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(46))) { //^
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_6);
                QQR.keyRelease(KeyEvent.VK_6);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(47))) { //&
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_7);
                QQR.keyRelease(KeyEvent.VK_7);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(48))) { //*
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_8);
                QQR.keyRelease(KeyEvent.VK_8);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(49))) { //(
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_9);
                QQR.keyRelease(KeyEvent.VK_9);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(50))) { //)
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_0);
                QQR.keyRelease(KeyEvent.VK_0);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(51))) { //-
                QQR.keyPress(KeyEvent.VK_MINUS);
                QQR.keyRelease(KeyEvent.VK_MINUS);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit.get(52))) { //=
                QQR.keyPress(KeyEvent.VK_EQUALS);
                QQR.keyRelease(KeyEvent.VK_EQUALS);
                status = 1; //设为1 不会输出报错
            }

            //支持库2

            if (list.get(i).equals(submit2.get(0))) { //+
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_EQUALS);
                QQR.keyRelease(KeyEvent.VK_EQUALS);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(1))) { //\
                QQR.keyPress(KeyEvent.VK_BACK_SLASH);
                QQR.keyRelease(KeyEvent.VK_BACK_SLASH);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(2))) { //|
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_BACK_SLASH);
                QQR.keyRelease(KeyEvent.VK_BACK_SLASH);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(3))) { //;
                QQR.keyPress(KeyEvent.VK_SEMICOLON);
                QQR.keyRelease(KeyEvent.VK_SEMICOLON);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(4))) { //:
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_SEMICOLON);
                QQR.keyRelease(KeyEvent.VK_SEMICOLON);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(5))) { //'
                QQR.keyPress(KeyEvent.VK_QUOTE);
                QQR.keyRelease(KeyEvent.VK_QUOTE);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(6))) { //"
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_QUOTE);
                QQR.keyRelease(KeyEvent.VK_QUOTE);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(7))) { //,
                QQR.keyPress(KeyEvent.VK_COMMA);
                QQR.keyRelease(KeyEvent.VK_COMMA);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(8))) { //<
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_COMMA);
                QQR.keyRelease(KeyEvent.VK_COMMA);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(9))) { //>
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_PERIOD);
                QQR.keyRelease(KeyEvent.VK_PERIOD);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(10))) { //?
                QQR.keyPress(KeyEvent.VK_SHIFT);
                QQR.keyPress(KeyEvent.VK_SLASH);
                QQR.keyRelease(KeyEvent.VK_SLASH);
                QQR.keyRelease(KeyEvent.VK_SHIFT);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(11))) { ///
                QQR.keyPress(KeyEvent.VK_SLASH);
                QQR.keyRelease(KeyEvent.VK_SLASH);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(12))) { //[
                QQR.keyPress(KeyEvent.VK_OPEN_BRACKET);
                QQR.keyRelease(KeyEvent.VK_OPEN_BRACKET);
                status = 1; //设为1 不会输出报错
            }

            if (list.get(i).equals(submit2.get(13))) { //]
                QQR.keyPress(KeyEvent.VK_CLOSE_BRACKET);
                QQR.keyRelease(KeyEvent.VK_CLOSE_BRACKET);
                status = 1; //设为1 不会输出报错
            }

            ////////////////////////////////////////////////////判断结束

            if (status == 0) {
                errorint += 1;
                System.out.println("\n不支持的字符! ( 在第 " + i + " 个 )");
            }
            status = 0;
            System.out.print(".");
           /*QQR.keyPress(6);
           QQR.keyRelease(6);*/
        }
        QQR.keyPress(KeyEvent.VK_ENTER);
        QQR.keyRelease(KeyEvent.VK_ENTER);
        System.out.println("\n输入完毕.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //用来测试的时候自动关闭记事本

        /*QQR.keyPress(KeyEvent.VK_ALT);
        QQR.keyPress(KeyEvent.VK_F4);
        QQR.keyRelease(KeyEvent.VK_F4);
        QQR.keyRelease(KeyEvent.VK_ALT);
        QQR.keyPress(KeyEvent.VK_N);
        QQR.keyRelease(KeyEvent.VK_N);*/
    }

    boolean isWindows10() {
        return System.getProperties().getProperty("os.name").toUpperCase().indexOf("10") != -1;
    }
}