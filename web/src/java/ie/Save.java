/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ie;


import java.io.*;
/**
 *
 * @author Administrator
 */
public class Save {
           public  static void  saveLocal(String tt,String text_temp,
                   String linkUrl){           
        try {
            FileOutputStream out = null ;
            OutputStreamWriter out1 = null ;
            BufferedWriter bw=null;
            
            if("else".equals(DivideText.divide(text_temp))){
                out = new FileOutputStream("else\\"+tt+".txt");       
              out1=new OutputStreamWriter(out);
              bw=new BufferedWriter(out1);
              bw.write(text_temp+"\r\n"+"原文引用自: "+linkUrl);   
              bw.close();
            }
            if("Society".equals(DivideText.divide(text_temp))){
                out = new FileOutputStream("Society\\"+tt+".txt");       
              out1=new OutputStreamWriter(out);
              bw=new BufferedWriter(out1);
              bw.write(text_temp+"\r\n"+"原文引用自: "+linkUrl); 
              bw.close();
            }
            if("Nation".equals(DivideText.divide(text_temp))){
                out = new FileOutputStream("Nation\\"+tt+".txt");       
              out1=new OutputStreamWriter(out);
              bw=new BufferedWriter(out1);
              bw.write(text_temp+"\r\n"+"原文引用自: "+linkUrl);  
              bw.close();
            }
            if("Inter".equals(DivideText.divide(text_temp))){
                out = new FileOutputStream("Inter\\"+tt+".txt");       
              out1=new OutputStreamWriter(out);
              bw=new BufferedWriter(out1);
              bw.write(text_temp+"\r\n"+"原文引用自: "+linkUrl);   
              bw.close();
            }         
           }
    catch(Exception e)
    {       
    }

           }
}
