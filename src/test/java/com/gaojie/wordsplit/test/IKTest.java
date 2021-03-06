 /** 
 * Project Name:wordsplit 
 * File Name:IKTest.java 
 * Package Name:com.gaojie.wordsplit.test 
 * Date:2018年1月12日下午4:11:43 
 * Copyright (c) 2018, taoge@tmd.me All Rights Reserved. 
 * 
 */  
  
package com.gaojie.wordsplit.test;

import java.io.IOException;  
import java.io.StringReader;  
  
import org.apache.lucene.analysis.TokenStream;  
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;  
import org.wltea.analyzer.core.IKSegmenter;  
import org.wltea.analyzer.core.Lexeme;  
import org.wltea.analyzer.lucene.IKAnalyzer;  
  
@SuppressWarnings("resource")  
public class IKTest {  
  
    public static void main(String[] args) throws Exception {  
        demo1();  
    }  
  
    // 分词测试  //不能用
    public static void demo() throws Exception {  
        IKAnalyzer analyzer = new IKAnalyzer(true);  
        System.out.println("当前使用的分词器：" + analyzer.getClass().getSimpleName());  
        TokenStream tokenStream = analyzer.tokenStream("content", "王五好吃懒做，溜须拍马，跟着李四，也过着小康的日子");  
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);  
        tokenStream.reset();// 必须先调用reset方法  
        while (tokenStream.incrementToken()) {  
            System.out.println(new String(charTermAttribute.buffer()));  
        }  
        tokenStream.close();  
    }  
  
    // 单独的使用IKAnalyzer，可以直接使用IK分词器的核心类，真正分词的实现类IKSegmenter分词器进行分词  
    public static void demo1() throws Exception {  
        StringReader reader = new StringReader("王五好吃懒做溜须拍马跟着李四也过着小康的日子");  
        IKSegmenter ik = new IKSegmenter(reader, true);// 当为true时，分词器进行最大词长切分  
        Lexeme lexeme = null;  
        try {  
            while ((lexeme = ik.next()) != null)  
                System.out.println(lexeme.getLexemeText());  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            reader.close();  
        }  
    }  
}  

  