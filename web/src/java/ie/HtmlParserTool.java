package ie;



import java.io.*;
import java.util.HashSet;
import java.util.Set;
import org.htmlparser.Parser;
import org.htmlparser.NodeFilter;
import org.htmlparser.Node;
import org.htmlparser.filters.OrFilter;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.beans.StringBean;
import org.htmlparser.tags.*;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class HtmlParserTool {
    
    
    public static Set<String> extracLinks(String url,LinkFilter filter){
            Set<String> links=new HashSet<String>();
   
    try{
          FileOutputStream out = null ;
          OutputStreamWriter out1 = null ;
          BufferedWriter bw=null;
          Parser parser= new Parser(url);
       
          
          parser.setEncoding("UTF-8");
          
          NodeFilter frameFilter=new NodeFilter(){
             
                @Override
              public boolean accept(Node node){
                  if(node.getText().startsWith("frame src=")){
                      return true ;
                  }else{
                      return false; 
                  }
              }
              };
          //p，div，table标签过滤器
       
           NodeFilter LinkFilter=new NodeClassFilter(LinkTag.class);
             
              //设置过滤《a》标签和frame标签
           OrFilter orFilter = new OrFilter(new NodeClassFilter(ImageTag.class), new 
NodeClassFilter(LinkTag.class));
	 OrFilter linkFilter = new OrFilter(orFilter, frameFilter);
              //得到所有过滤标签
             NodeList list=parser.extractAllNodesThatMatch(linkFilter);
            //  NodeList p=parser.extractAllNodesThatMatch(pFilter);
             for(int i=0; i< list.size(); i++){
                Node tag =list.elementAt(i);
                if(tag instanceof LinkTag ){               
                    LinkTag link=(LinkTag)tag;
                    String linkUrl=link.getLink();
                    if(linkUrl.startsWith("http://news.qq.com/")){
                    String text=link.getLinkText();  
                  //  System.out.println(linkUrl+"****"+text);              
                  StringBean sb = new StringBean();
                  sb.setLinks(false);//设置结果中去点链接
                  sb.setURL(linkUrl);//设置你所需要滤掉网页标签的页面 url
             String text_temp = sb.getStrings();
            int index1 = text_temp.indexOf("SOSO");
            int index2 = text_temp.lastIndexOf("分享到");
            if(index1!=-1&&index2!=-1)
             {
               
                // 获取正文
                 text_temp =text_temp.substring(0, index2);
                 text_temp = text_temp.substring(index1+6);
               
                 //获取标题
                 
           //      int index3=text_temp.indexOf(!" ");
                 int index4=text_temp.indexOf("\r\n");
                 String tt= text_temp.substring(0,index4);
                 System.out.println(linkUrl+"****"+tt); 
                 if(filter.accept(linkUrl))
                        links.add(linkUrl);                                 
                 //写入文件
                 Save.saveLocal(tt, text_temp, linkUrl);             
             }
                  }
                  }
                }               
    }catch(ParserException e){
            }
    catch(Exception e)
    {
        
    }
             return links;
}

}
