package 四则运算;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) {
		Scanner in =new Scanner (System.in);
		int n=in.nextInt();
		CreatQuestion(n);
		in.close();
		
	}
	

	public static void CreatQuestion(int n) {
		char[] ch={'+','-','*','/'};
		Random random=new Random();  //随机数
		int sum=0;	
		File file =new File("subject.txt");  //删除之前的文件
			file.delete();
		
		while(sum<n) {
			int n1=random.nextInt(2)+2;   //产生2或3
			char[] ch1=new char[n1-1];
			for(int j=0;j<n1-1;j++) 
				ch1[j]=ch[random.nextInt(4)];
			int[] num=new int[n1];
			for(int j=0;j<n1;j++)
				num[j]=random.nextInt(100);
			for(int j=0;j<n1-1;j++)
				if(ch1[j]=='/'&&num[j+1]==0)
					continue;
			String str=new String();
			if(n1==2) 
				 str=num[0]+" "+ch1[0]+" "+num[1];
			if(n1==3) 
				str=num[0]+" "+ch1[0]+" "+num[1]+" "+ch1[1]+" "+num[2];
			
				
				float a=judge(str);
				if(a%1.0==0&&a>0) {
					System.out.println(str+" "+"="+" "+(int)a);
					save(str+" "+"="+" "+(int)a);
				}
				    
				else
					continue;
			sum++;
		}
		
		
		
	}
	
	
	public static float judge(String str) {	
		float sum=0;
		String[] str1=str.split(" "+"");
		int num=0;
		while(str1.length>2) {
			for(int i=0;i<str1.length;i++)
			  if(str1[i].equals("/")) {  
				  sum=0;
				sum+=Float.valueOf(str1[i-1])/Float.valueOf(str1[i+1]);
				str1[i-1]=String.valueOf(sum);
				str1[i]=" ";
				str1[i+1]=" ";
				num++;
				str1=change(str1);
				break;
			}
			for(int i=0;i<str1.length;i++)
			  if(str1[i].equals("*")) {
				  sum=0;
				sum+=Float.valueOf(str1[i-1])*Float.valueOf(str1[i+1]);
			    str1[i-1]=String.valueOf(sum);
			    str1[i]=" ";
			    str1[i+1]=" ";
			    num++;
			    str1=change(str1);
			    break;
			}
			for(int i=0;i<str1.length;i++)
				if(str1[i].equals("-")) {
					sum=0;
					sum+=Float.valueOf(str1[i-1])-Float.valueOf(str1[i+1]);
					str1[i-1]=String.valueOf(sum);
					str1[i]=" ";
					str1[i+1]=" ";
					num++;
					str1=change(str1);
					break;
				}
			for(int i=0;i<str1.length;i++)	
			if(str1[i].equals("+")) {
				sum=0;
				sum+=Float.valueOf(str1[i-1])+Float.valueOf(str1[i+1]);
				str1[i-1]=String.valueOf(sum);
				str1[i]=" ";
				str1[i+1]=" ";
				num++;
				str1=change(str1);
				break;
			}
		}
		
		return sum;
		
	}

	
	
	public static String[] change(String[] str1) {
		StringBuffer str=new StringBuffer();
		for(int i=0;i<str1.length ;i++)
			if(!str1[i].equals(" "))
			str.append(str1[i]+" ");
		return str.toString().split(" ");
       
		
	}
	
	
	public static void save(String str) {
		File file =new File("subject.txt"); 
		try {
			FileWriter output=new FileWriter(file,true);
			output.append(str+"\r\n");
			
			output.close();
		} catch (IOException e) {
						e.printStackTrace();
		}
	
		
			
			
		
	}
	

}

