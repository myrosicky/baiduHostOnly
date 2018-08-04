package mainprogram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;



public class core_processer 
{
    String host_name;
    String url,data;
    Pattern p,p2,p3,p4,last_page=Pattern.compile(".*<a href=.*?pn=.*>βҳ</a>.*");
    Matcher m;
    int page=1,last_page_number=1;
    URL u;
    HttpURLConnection channel;
    BufferedReader br;
    PrintWriter pw;
   
    public core_processer()
    {}
	public void execute(String uu) throws IOException
	{
		
		url=uu;
		url_revise(page);
				
		File f=new File("e:\\target.html");
		
		pw=new PrintWriter(new FileWriter(f));
		
	//get host name
		p=Pattern.compile(".*a name=.#1.*");
		
		data=br.readLine();
		
		while(data!=null)
		{
			
		//new 
			m=last_page.matcher(data);
			if(m.matches())
			{
				data=data.substring(0,data.indexOf("βҳ"));
				data=data.substring(data.lastIndexOf("=")+1,data.lastIndexOf("\""));
				last_page_number=Integer.parseInt(data);
				//last_page_number--;
			}
			
			
		//end new
			m=p.matcher(data);			
			pw.println(data);
			if(m.matches())
			{ 
				
				data=br.readLine();pw.println(data);
				data=data.substring(data.indexOf("name")+7); 
				data=data.substring(0,data.indexOf("\""));
				host_name=data;
				p3=Pattern.compile(".*<div class=.l_post.>.*");			
				data=br.readLine();
				m=p3.matcher(data);
				while(!m.matches())
				{
					pw.println(data);data=br.readLine();m=p3.matcher(data);
				}
				
				break;	
			}
			p2=Pattern.compile(".*floor.:[^1],");
			m=p2.matcher(data);
			if(m.matches())
			{ 
				JOptionPane.showConfirmDialog(null, "error");
				return;	
			}
			data=br.readLine();
		}
		
		//JOptionPane.showMessageDialog(null, "last_page_number:"+last_page_number);
		
		
		
	//end get host name
	
			
		
	//load the webpage data into the destination file
		data=br.readLine();
		p=Pattern.compile(".*name.:."+host_name+".*");
		
		while(page <= last_page_number)
		{
			perpage_oper();
			url_revise(++page);
			data=br.readLine();
		}
		
		
	//end load the webpage data into the destination file
		br.close();
		pw.close();
		
	
		Process pro=Runtime.getRuntime().exec("cmd /c E:\\target.html");
		
	}
	
	public void perpage_oper()            //only detect the the end of the file and max page 
	{
		while(data!=null)
		{	
			try
			{					
				read_and_write();
				if(data==null) break;
				data=br.readLine();
			}catch(IOException ee){ee.printStackTrace();}
						
		}
	}
	
	
	public void read_and_write() throws IOException
	{
		m=p.matcher(data);
		if(m.matches())
		{ 
			data="<div class=\"l_post\">"+data;
			p2=Pattern.compile(".*div class=.l_post.*");
			p4=Pattern.compile(".*div id=.p_ding_last.*");
			pw.println(data);
			data=br.readLine();
			m=p2.matcher(data);
			while(!m.matches())
			{
				pw.println(data);
				data=br.readLine();	
				m=p4.matcher(data);
				if(data==null || m.matches()) break;
				
				m=p2.matcher(data);
			}
			
		}
		
	}
	public void url_revise(int i)
	{
		
		if(url.indexOf("?")==-1)
			url+="?pn="+i;
		else
	     url=url.substring(0,url.indexOf("=")+1)+i;	
		//JOptionPane.showMessageDialog(null, url);
		try{
			u=new URL(url);
			channel=(HttpURLConnection)u.openConnection();
			br=new BufferedReader(new InputStreamReader(channel.getInputStream()));
		}catch(MalformedURLException ee){ee.printStackTrace();}catch(IOException ee){ee.printStackTrace();}
		
		
	}
	

}
