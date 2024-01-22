package mtiotest1;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
    public static void main(String[] args) throws IOException {
        //1.定义变量记录网址
        String familyNameNet="https://hanyu.baidu.com/shici/detail?pid=0b2f26d4c0ddb3ee693fdb1137ee1b0d&from=kg0";
        String boyNameNet="http://www.haoming8.cn/baobao/10881.html";
        String girlNameNet="http://www.haoming8.cn/baobao/7641.html";

        //2.爬取数据，把网址上的所有数据拼接成一个字符串
        String familyNameStr = webCrawler(familyNameNet);
        String boyNameStr = webCrawler(boyNameNet);
        String girlNameStr = webCrawler(girlNameNet);

        //System.out.println(familyNameStr);
        //System.out.println(boyNameStr);
        //System.out.println(girlNameStr);

        //3.通过正则表达式，把其中符合要求的数据获取出来
        ArrayList<String> familyNameTempList= getData(familyNameStr,"(.{4})(，|。)",1);
        System.out.println(familyNameTempList);
    }


//    作用：根据正则表达式获取字符串中的数据
//    参数一：完整的字符串
//    参数二：正则表达式
//    参数三
//    返回值：真正想要的数据
//
    private static ArrayList<String> getData(String str, String regex,int index) {
        //1.创建集合存放数据
        ArrayList<String>list= new ArrayList<String>();
        //2.按照正则表达式的规则，去获取数据
        Pattern pattern= Pattern.compile(regex);
        //3.按照pattern的规则，到str当中获取数据
        Matcher matcher = pattern.matcher(str);
        while(matcher.find()){
            list.add(matcher.group(index));
        }
        return list;
    }


    //作用：从网络中爬取数据，把数据拼接成字符串返回
    public static String webCrawler(String net) throws IOException {
        //1.定义StringBuilder拼接爬取到的数据
        StringBuilder sb=new StringBuilder();
        //2.创建一个URL对象
        URL url =new URL(net);
        //3.链接上这个网址
        URLConnection con=url.openConnection();
        //4.读取数据
        InputStreamReader isr=new InputStreamReader((con.getInputStream()),"UTF-8");
        int ch;
        while((ch=isr.read())!=-1){
            sb.append((char)ch);
        }
        //5.释放资源
        isr.close();
        //6.把读取到的数据返回
        return sb.toString();
    }

}
