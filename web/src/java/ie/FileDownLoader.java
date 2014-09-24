package ie;

import java.io.*;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class FileDownLoader {
            
    public String getFileNameByUrl(String url,String contenttype){
        url=url.substring(7);
        if(contenttype.indexOf("html")!=-1){
          url=url.replaceAll("[\\?/*|<>\"]", "_")+".html";
        return url;
    }
    else
        {
    return url.replaceAll("[\\?/*|<>\"]", "_")+"."+
         contenttype.substring(contenttype.lastIndexOf("/")+1);
        }
    }       
        private void saveToLocal(InputStream data,String filePath){
            try{
             
             //   System.out.println(data);             
                FileOutputStream out=  new FileOutputStream(new File(filePath));
                OutputStreamWriter out2=new OutputStreamWriter(out);
                BufferedWriter bw1=new BufferedWriter(out2);
                bw1.write(data.toString());
                bw1.close();
            /*     PrintWriter out1 = null ;
                out1 = new PrintWriter(out);
                     out1.println(data);
                     out1.close();
                   
                    
                    out.close();*/
            }catch(IOException e){
            }
        }
        //下载url指向的网页
        public String downloadfile(String url){
            String filePath=null;
            //1,生成HttpClient对象并设置参数
            HttpClient httpClient=new HttpClient();
            //设置Http链接超市5S
            httpClient.getHttpConnectionManager().getParams().
                    setConnectionTimeout(5000);
            //2,生成getmethod 对象并设置子参数
            GetMethod getMethod=new GetMethod(url);
            getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT,5000);
            getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                    new DefaultHttpMethodRetryHandler());
            
            try{
                int statusCode=httpClient.executeMethod(getMethod);
                if(statusCode!=HttpStatus.SC_OK)
                {
                System.err.println("Method failed:"+getMethod.getStatusLine());
                filePath=null;               
                }
         InputStream responseBody=getMethod.getResponseBodyAsStream();
         
         
         
         filePath="temp\\"+getFileNameByUrl(url,
                 getMethod.getResponseHeader("Content-Type").getValue());
                   saveToLocal(responseBody,filePath);                   
            }catch(HttpException e){
                System.out.println("Please check out your provide http address!");
            }catch (IOException e){
            }finally{
                getMethod.releaseConnection();
            }
            return filePath;
        }
   /*   public static void main(String[] args){
            FileDownLoader downLoader =new FileDownLoader();
            downLoader.downloadfile("http://www.hust.edu.cn");
        }*/
       
}
