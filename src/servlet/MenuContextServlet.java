package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import JDBC.DBManager;
import mode.DropDownBox;
import mode.menuItem;
import mode.responsePack;



/**
 * Servlet implementation class MenuContextServlet
 */
@WebServlet("/MenuContextServlet")
public class MenuContextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	
	private  List<menuItem> list = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MenuContextServlet() {
        super();
   
    }
    public void init(ServletConfig config) 
    {
    	 loadXML(config);
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		PrintWriter write = response.getWriter();
		String ClientVersion =  request.getParameter("versions");
		if(ClientVersion == null) 
		{
			write.append("versions parameter do not exsis");
			return ;
		}
		
		int serverVersion = DBManager.getInstance().getVersion();
		if(ClientVersion.equals(serverVersion+"")) 
		{
			write.append("1");
			return ;
		}
		responsePack rp = new responsePack();
		rp.setVersion(serverVersion);
		rp.setList(list);
		Gson gson = new Gson();
		String json = gson.toJson(rp);
		write.append(json);
		//for(menuItem item:list ) 
		//{
		//	String json = gson.toJson(item);
			//write.append(json);
			//write.append("------------");
		//}
		
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void loadXML(ServletConfig config) 
	{
		try {
			 XMLReader parser = XMLReaderFactory.createXMLReader();
			 UserHandler userHandler = new UserHandler();
	         parser.setContentHandler(userHandler);
	         String path = config.getServletContext().getRealPath("/WEB-INF");
	         //String temp=s1.getRealPath("/WEB-INF");
	         System.out.println(path);
	         parser.parse(path+"/Content.xml");
	         list = userHandler.getTabList();
	         
		} catch (SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	   
	}
	static class UserHandler extends DefaultHandler {
       

        private List<menuItem> list = null;
        
        public List<menuItem> getTabList()
        {
        	return list;
        }
        
        private menuItem currentObj = null;
        private String content = "";
        private int tabIndex;
        private DropDownBox d = null;
        private enum element
        {
        	ROOT,
        	TITLE,
        	TIPS,
        	SUBCONTENT,
        	DROPDOWNBOX1,
        	DROPDOWNBOX2,
        	DROPDOWNBOX3,
        	ITEM
        }
        private element index;
        public void startDocument() throws SAXException {
            System.out.println("开始解析文档...");
            list = new ArrayList<menuItem>();
        }

        // xml 文档解析结束
        public void endDocument() throws SAXException {
            System.out.println("...文档解析完毕");
        }

        // 访问某一个元素
        public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        	 System.out.println("userName: " + qName);
        	 content = "";
        	 if(qName.equals("title")) 
        	 {
        		 currentObj = new menuItem();
        		 list.add(currentObj);
        		 index = element.TITLE;
        	 }
        	 else if(qName.equals("tips")) 
        	 {
        		 index = element.TIPS;
        	 }
        	 else if(qName.equals("subcontent")) 
        	 {
        		 index = element.SUBCONTENT;
        	 }
        	 else if(qName.equals("dropDownBox")) 
        	 {
        		 index = element.DROPDOWNBOX1;
    			 d = new DropDownBox();
    			 d.setTitle(atts.getValue("title"));
    			 d.setTips(atts.getValue("tips"));
    			 currentObj.addDropDownBox(d); 
   
        	 }
        	
        }

        // 结束访问元素
        public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        	 System.out.println("userName: " + qName);

        }

        // 访问元素正文
        public void characters(char[] ch, int start, int length) {
           
                String context = new String(ch, start, length);
                System.out.println("userName: " + context);
                content += context;
                if(index == element.TITLE) 
                {
                	currentObj.setTitle(content);
                }else if(index == element.TIPS) 
                {
                	currentObj.setTips(content);
                }
                else if(index == element.SUBCONTENT) 
                {
                	currentObj.setContent(content);
                }else if(index == element.DROPDOWNBOX1) 
                {
                	 d.setContent(content);
                }
                

        }

    }
}
