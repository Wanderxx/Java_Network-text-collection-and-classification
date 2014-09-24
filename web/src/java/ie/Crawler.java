package ie;


import java.util.HashSet;
import java.util.Set;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Administrator
 */
public class Crawler {
    //使用种子URL初始化URL队列
    /**
     * 
     * 
     * @param seeds 
     */
     private static Set<String> visitedUrl =new HashSet<String>();
   private void initCrawlerWithSeeds(String[] seeds){
      
      for(int i=0;i<seeds.length;i++)
                 LinkDB.addUnvisitedUrl(seeds[i]);
       // System.out.println(LinkDB.getUnVisitedUrl());
    }
    //爬取方法
    public void crawling(String[] seeds){
        
           LinkFilter filter=new LinkFilter(){
               //提取网页
           
         
            @Override
               public boolean accept(String url){
                   if(url.startsWith("http://news.qq.com"))
                           return true;
                   else
                            return false;
               }
           };
           //初始化URL队列
           initCrawlerWithSeeds(seeds);
    
           //循环条件：带抓取的链接不空且抓取网页不多于1000
           while(!LinkDB.unVisitedUrlsEmpty()&&LinkDB.getVisitedUrlNum()<=1000
                   )
           {
                String visitUrl;
               //对头URL出对
            //   do{
               visitUrl=LinkDB.unVisitedUrlDeQueue();
            //   }while(!visitUrl.startsWith("http://news.qq.com"));
               if(visitUrl==null)continue;
               
               FileDownLoader downloader=new FileDownLoader();
               //下载网页
               downloader.downloadfile(visitUrl);
               //将URL放入到已访问Url中
               LinkDB.addVisitedUrl(visitUrl);
               //提取出下载网页
               Set<String> links=HtmlParserTool.extracLinks(visitUrl, filter);

               for(String link:links)
               { 
                   //  for( int i=0;i<visitedUrl.size();i++){
                      //   if(link!=LinkDB.getvisitedUrl())
                        LinkDB.addUnvisitedUrl(link);
               
               }
               
           }
       
    }
public static void main(String[] args){
         Crawler crawler= new Crawler();
         crawler.crawling(new String[]{"http://news.qq.com"}); 
}   
   
}
