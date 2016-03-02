package webspider;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Set;


public class MyCrawler extends JFrame{
 /**
  * 使用种子初始化 URL 队列
  * 
  * @return
  * @param seeds
  *            种子URL
  */
	
	
	JFrame table =new JFrame();
	JPanel pane =new JPanel();
	JTextField input =new JTextField("http://zhidao.baidu.com/");
	JButton OK=new JButton("确定");
	MyCrawler(){
		table.setTitle("随便看看");
		table.setVisible(true);
		table.setSize(400,400);
		table.setResizable(false);
		table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		table.add(pane);
		pane.add(input);
		pane.add(OK);
		String[] seeds={"http://zhidao.baidu.com/"};
		
		
		
		OK.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				
				seeds[0]=input.getText();
				crawling(seeds);
				
			}
		});
	}
	
	
	
	
 private void initCrawlerWithSeeds(String[] seeds) {
  for (int i = 0; i < seeds.length; i++)
   LinkQueue.addUnvisitedUrl(seeds[i]);
 }
 /**
  * 抓取过程
  * 
  * @return
  * @param seeds
  */
 public void crawling(String[] seeds) { // 定义过滤器，提取以http://www.lietu.com开头的链接
  LinkFilter filter = new LinkFilter() {
   public boolean accept(String url) {
    if (url.startsWith("http://zhidao.baidu.com/"))
     return true;
    else
     return false;
   }
  };
  // 初始化 URL 队列
  initCrawlerWithSeeds(seeds);
  // 循环条件：待抓取的链接不空且抓取的网页不多于1000
  while (!LinkQueue.unVisitedUrlsEmpty()
    && LinkQueue.getVisitedUrlNum() <= 1000) {
   // 队头URL出队列
   String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
   if (visitUrl == null)
    continue;
   DownLoadFile downLoader = new DownLoadFile();
   // 下载网页
   downLoader.downloadFile(visitUrl);
   // 该 url 放入到已访问的 URL 中
   LinkQueue.addVisitedUrl(visitUrl);
   // 提取出下载网页中的 URL
   Set<String> links = HtmlParserTool.extracLinks(visitUrl, filter);
   // 新的未访问的 URL 入队
   for (String link : links) {
    LinkQueue.addUnvisitedUrl(link);
   }
  }
 }
 public void show(){
		table.show();
	}
 // main 方法入口
 public static void main(String[] args) {
	 MyCrawler a=new MyCrawler();
	 a.show();
  
 }
}