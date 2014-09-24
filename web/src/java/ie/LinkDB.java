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
public class LinkDB {
        //已访问URL集合
    private static Set<String> visitedUrl =new HashSet<String>();
    //待访问URL集合
    private static Queue<String> unVisitedUrl =new Queue<String>();
   // TreeSet ts=new TreeSet(visitedUrl);
   public static Set getvisitedUrl(){
               return visitedUrl;
    }
    public static Queue<String> getUnVisitedUrl(){
               return unVisitedUrl;
    }
    public static void addVisitedUrl(String url){
        visitedUrl.add(url);
    }
    public static void removeVisitedUrl(String url){
        visitedUrl.remove(url);
    }
    public static String unVisitedUrlDeQueue(){
        return unVisitedUrl.deQueue();
    }
    //保证每个url只访问一次
    public static void addUnvisitedUrl(String url){
        if(url !=null&&!url.trim().equals("")&&!visitedUrl.contains(url)
                &&!unVisitedUrl.contains(url))
                unVisitedUrl.enQueue(url);
    }
    public static int getVisitedUrlNum(){
          return visitedUrl.size();         
    }
   public static boolean unVisitedUrlsEmpty(){
       return unVisitedUrl.empty();
   }
}
