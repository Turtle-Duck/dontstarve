package main;

import java.io.File;
import java.util.Scanner;

public class readmap {
	static int [][] mapunder;
	static int [][] maphigh;
	static void readf(String path){
		try{
			Scanner inread = new Scanner(new File("src/map.txt"));
			String str;
			str = inread.nextLine();
			int maxX,maxY;
			maxX = Integer.parseInt(str.substring(0, str.indexOf(",",0)));
			maxY = Integer.parseInt(str.substring(str.indexOf(",",0)+1));
			mapunder = new int [maxX][maxY];
			maphigh = new int [maxX][maxY];
			int last=0;
			for(int i=0;i<maxX;i++)
			{
				str = inread.nextLine();
				last=0;
				for(int j=0;j<maxY;j++)
				{
					mapunder[i][j]=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
					last=str.indexOf(",",last)+1;
				}
			}
			str = inread.nextLine();
			for(int i=0;i<maxX;i++)
			{
				str = inread.nextLine();
				last=0;
				for(int j=0;j<maxY;j++)
				{
					maphigh[i][j]=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
					last=str.indexOf(",",last)+1;
				}
			}
			inread.close();
		}
		catch(Exception exc){
			exc.printStackTrace();
		}
	}
}
