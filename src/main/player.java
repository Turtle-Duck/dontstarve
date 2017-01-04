package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;
import javax.swing.ImageIcon;


public class player extends Thread implements normal{
	static int playerframex = gamex/2;
	static int playerframey = gamey/2;
	static int playerx;
	static int playery;
	static int deltx;
	static int delty;
	static int step;
	static boolean up = false;
	static boolean down = false;
	static boolean left = false;
	static boolean right = false;
	static long temptime1,temptime2,temptime3,temptime4;
	static int towards;//0正面（下），1背面（上），2左，3右
	static int uptimes = 0;//角色的移动累积量
	static int downtimes = 0;
	static int lefttimes = 0;
	static int righttimes = 0;
	
	static int healthy;
	static int full;
	static int treenum;
	static int flowernum;
	static int berrynum;
	static int carrotnum;
	static int grassnum;
	static int islight=0;
	static int [] manu1 = new int [10];
	static int [] manudelt = {0,46,87,130,175,227,271,315,358,403};
	class myobject{
		int x;//位置
		int y;
	}
	static class tempclass{
		int thingnum=0;
		int index=-1;
	}
	public class Comparator1 implements Comparator<myobject> {
		@Override
		public int compare(myobject arg0, myobject arg1) {
			if(arg0.x<arg1.x)
				return -1;
			else if(arg0.x==arg1.x)
			{
				if(arg0.y<=arg1.y)
					return -1;
				else 
					return 1;
			}
			else
				return 1;
		}
	}
	class tree extends myobject{
		int state;//状态
	}
	class berry extends myobject{
		int state;//状态
	}
	static LinkedList<tree> treelist= new LinkedList<tree>();
	static LinkedList<myobject> flowerlist= new LinkedList<myobject>();
	static LinkedList<berry> berrylist= new LinkedList<berry>();
	static LinkedList<myobject> carrotlist= new LinkedList<myobject>();
	static LinkedList<myobject> grasslist= new LinkedList<myobject>();
	public player(){
		try {
			int last=0;
			Date mydate=new Date();
			Comparator1 com = new Comparator1();
			temptime1=mydate.getTime();
			temptime2=mydate.getTime();
			temptime3=mydate.getTime();
			Scanner inread = new Scanner(new File("src/save.txt"));
			String str;
			str=inread.nextLine();
			playerx = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			playery = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			deltx = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			delty = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			step = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			towards = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			healthy = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			full = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			treenum = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			flowernum = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			berrynum = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			carrotnum = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			grassnum = Integer.parseInt(str.substring(str.indexOf(",")+1));		
			
			str=inread.nextLine();
			Date atime=new Date();
			temptime2 = atime.getTime()-Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			last=str.indexOf(",",0)+1;
			for(int i=0;i<10;i++)
			{
				manu1[i]=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
			}
			str=inread.nextLine();
			last=str.indexOf(",",0)+1;
			while(!str.substring(last).isEmpty())
			{
				tree treetemp =new tree();
				treetemp.x=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				treetemp.y=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				treetemp.state=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				treelist.add(treetemp);
			}
			Collections.sort (treelist, com);
			str=inread.nextLine();
			last=str.indexOf(",",0)+1;
			while(!str.substring(last).isEmpty())
			{
				myobject fltemp =new myobject();
				fltemp.x=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				fltemp.y=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				flowerlist.add(fltemp);
			}
			Collections.sort (flowerlist, com);
			str=inread.nextLine();
			last=str.indexOf(",",0)+1;
			while(!str.substring(last).isEmpty())
			{
				berry berrytp=new berry();
				berrytp.x=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				berrytp.y=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				berrytp.state=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				berrylist.add(berrytp);
			}
			Collections.sort (berrylist, com);
			str=inread.nextLine();
			last=str.indexOf(",",0)+1;
			while(!str.substring(last).isEmpty())
			{
				myobject cartemp =new myobject();
				cartemp.x=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				cartemp.y=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				carrotlist.add(cartemp);
			}
			Collections.sort (carrotlist, com);
			str=inread.nextLine();
			last=str.indexOf(",",0)+1;
			while(!str.substring(last).isEmpty())
			{
				myobject gratemp =new myobject();
				gratemp.x=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				gratemp.y=Integer.parseInt(str.substring(last, str.indexOf(",",last)));
				last=str.indexOf(",",last)+1;
				grasslist.add(gratemp);
			}
			Collections.sort (grasslist, com);
			inread.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static void reset(){
		try {
			Scanner inread;
			inread = new Scanner(new File("src/save.txt"));
			String str;
			str=inread.nextLine();
			playerx = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			playery = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			deltx = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			delty = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			step = Integer.parseInt(str.substring(str.indexOf(",")+1));
			str=inread.nextLine();
			towards = Integer.parseInt(str.substring(str.indexOf(",")+1));
			inread.close();
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	public static void save(){
		try {
			PrintStream p = new PrintStream( new FileOutputStream(new File("src/save.txt")));
			p.println("playerx,"+playerx);
			p.println("playery,"+playery);
			p.println("deltx,"+deltx);
			p.println("delty,"+delty);
			p.println("step,"+step);
			p.println("towards,"+towards);
			p.println("healthy,"+healthy);
			p.println("full,"+full);
			p.println("treenum,"+treenum);
			p.println("flowernum,"+flowernum);
			p.println("berrynum,"+berrynum);
			p.println("carrotnum,"+carrotnum);
			p.println("grassnum,"+grassnum);
			Date thetime=new Date();
			int timedelt=(int) (thetime.getTime()-temptime2);
			p.println("time,"+ timedelt);
			p.print("manu,");
			for(int i=0;i<10;i++)
			{
				p.print(manu1[i]+",");
			}
			p.println("");
			
			p.print("tree,");
			for(tree tm:treelist)
			{
				p.print(tm.x+","+tm.y+","+tm.state+",");
			}
			p.println("");
			p.print("flower,");
			for(myobject tm:flowerlist)
			{
				p.print(tm.x+","+tm.y+",");
			}
			p.println("");
			p.print("berry,");
			for(berry tm:berrylist)
			{
				p.print(tm.x+","+tm.y+","+tm.state+",");
			}
			p.println("");
			p.print("carrot,");
			for(myobject tm:carrotlist)
			{
				p.print(tm.x+","+tm.y+",");
			}
			p.println("");
			p.print("grass,");
			for(myobject tm:grasslist)
			{
				p.print(tm.x+","+tm.y+",");
			}
			p.println("");
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		while(true){
			moveUD();
			moveLR();
			Date mydate2=new Date();
			if(mydate2.getTime()-temptime1>savetime)
			{
				save();
				temptime1=mydate2.getTime();
			}
			if(mydate2.getTime()-temptime3>starvetime)
			{
				full=full-1;
				if(full<0) {
					player.dead();
					full=0;
				}
				temptime3=mydate2.getTime();
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private static void dead() {
		// TODO 自动生成的方法存根
	}
	private void moveUD() {
		if(up){
			uptimes++;
			if(uptimes>6*everystep) uptimes=0;
			if(readmap.mapunder[playery/mapsize][playerx/mapsize]<=159 && readmap.mapunder[playery/mapsize][playerx/mapsize]>=28
					||readmap.maphigh[playery/mapsize-2][playerx/mapsize]>=176 && readmap.maphigh[playery/mapsize][playerx/mapsize]<=187
					||havething(playerx,playery,1).thingnum>0){}
			else {
				playery=playery-step;
				delty=delty-step;
			}
		}
		if(down){
			downtimes++;
			if(downtimes>6*everystep) downtimes=0;
			if(readmap.mapunder[playery/mapsize+1][playerx/mapsize]<=159 && readmap.mapunder[playery/mapsize+1][playerx/mapsize]>=28
					||readmap.maphigh[playery/mapsize][playerx/mapsize]>=176 && readmap.maphigh[playery/mapsize][playerx/mapsize]<=187
					||havething(playerx,playery,0).thingnum>0){}
			else {
				playery=playery+step;
				delty=delty+step;
			}
		}
	}
	private void moveLR() {
		if(left){
			lefttimes++;
			if(lefttimes>6*everystep) lefttimes=0;
			if(readmap.mapunder[playery/mapsize][playerx/mapsize]<=159 && readmap.mapunder[playery/mapsize][playerx/mapsize]>=28
					||readmap.maphigh[playery/mapsize-2][playerx/mapsize]>=168 && readmap.maphigh[playery/mapsize-2][playerx/mapsize]<=187
					||havething(playerx,playery,2).thingnum>0){}
			else {
				playerx=playerx-step;
				deltx=deltx-step;
			}
		}
		if(right){
			righttimes++;
			if(righttimes>6*everystep) righttimes=0;
			if(readmap.mapunder[playery/mapsize][playerx/mapsize+1]<=159 && readmap.mapunder[playery/mapsize][playerx/mapsize+1]>=28
					||readmap.maphigh[playery/mapsize-2][playerx/mapsize]>=168 && readmap.maphigh[playery/mapsize-2][playerx/mapsize]<=187
					||havething(playerx,playery,3).thingnum>0){}
			else{
				playerx=playerx+step;
				deltx=deltx+step;
			}
		}
	}
	public static tempclass havething(int x,int y,int towa){
		tempclass cl=new tempclass();
		cl.thingnum=0;
		cl.index=-1;
		int count=0;
		for (tree atree : treelist) {
			if(towa==0)
			{
				if(atree.y-playery<30 && atree.y-playery>=0 && playerx<=atree.x+20 && playerx>=atree.x-20)
				{
					cl.index=count;
					cl.thingnum=5;
					return cl;
				}
			}else if(towa==1)
			{
				if(playery-atree.y<60 && playery-atree.y>=0 && playerx<=atree.x+20 && playerx>=atree.x-20)
				{
					cl.index=count;
					cl.thingnum=5;
					return cl;
				}
			}
			else if(towa==2)
			{
				if(playerx-atree.x<30 && playerx-atree.x>=0 && playery<=atree.y+20 && playery>=atree.y-20)
				{
					cl.index=count;
					cl.thingnum=5;
					return cl;
				}
			}
			else //if(towa==3)
			{
				if(atree.x-playerx<30 && atree.x-playerx>=0 && playery<=atree.y+20 && playery>=atree.y-20)
				{
					cl.index=count;
					cl.thingnum=5;
					return cl;
				}
			}
			count++;
		}
		count=0;
		for (myobject ath: flowerlist) {
			if(towa==0)
			{
				if(ath.y-playery<30 && ath.y-playery>=0 && playerx<=ath.x+20 && playerx>=ath.x-20)
				{
					cl.index=count;
					cl.thingnum=3;
					return cl;
				}
			}else if(towa==1)
			{
				if(playery-ath.y<60 && playery-ath.y>=0 && playerx<=ath.x+20 && playerx>=ath.x-20)
				{
					cl.index=count;
					cl.thingnum=3;
					return cl;
				}
			}
			else if(towa==2)
			{
				if(playerx-ath.x<30 && playerx-ath.x>=0 && playery<=ath.y+20 && playery>=ath.y-20)
				{
					cl.index=count;
					cl.thingnum=3;
					return cl;
				}
			}
			else //if(towa==3)
			{
				if(ath.x-playerx<30 && ath.x-playerx>=0 && playery<=ath.y+20 && playery>=ath.y-20)
				{
					cl.index=count;
					cl.thingnum=3;
					return cl;
				}
			}
			count++;
		}
		count=0;
		for (berry aberry : berrylist) {
			if(towa==0)
			{
				if(aberry.y-playery<30 && aberry.y-playery>=0 && playerx<=aberry.x+20 && playerx>=aberry.x-20)
				{
					cl.index=count;
					cl.thingnum=1;
					return cl;
				}
			}else if(towa==1)
			{
				if(playery-aberry.y<60 && playery-aberry.y>=0 && playerx<=aberry.x+20 && playerx>=aberry.x-20)
				{
					cl.index=count;
					cl.thingnum=1;
					return cl;
				}
			}
			else if(towa==2)
			{
				if(playerx-aberry.x<30 && playerx-aberry.x>=0 && playery<=aberry.y+20 && playery>=aberry.y-20)
				{
					cl.index=count;
					cl.thingnum=1;
					return cl;
				}
			}
			else //if(towa==3)
			{
				if(aberry.x-playerx<30 && aberry.x-playerx>=0 && playery<=aberry.y+20 && playery>=aberry.y-20)
				{
					cl.index=count;
					cl.thingnum=1;
					return cl;
				}
			}
			count++;
		}
		count=0;
		for (myobject ath : carrotlist) {
			if(towa==0)
			{
				if(ath.y-playery<30 && ath.y-playery>=0 && playerx<=ath.x+20 && playerx>=ath.x-20)
				{
					cl.index=count;
					cl.thingnum=2;
					return cl;
				}
			}else if(towa==1)
			{
				if(playery-ath.y<60 && playery-ath.y>=0 && playerx<=ath.x+20 && playerx>=ath.x-20)
				{
					cl.index=count;
					cl.thingnum=2;
					return cl;
				}
			}
			else if(towa==2)
			{
				if(playerx-ath.x<30 && playerx-ath.x>=0 && playery<=ath.y+20 && playery>=ath.y-20)
				{
					cl.index=count;
					cl.thingnum=2;
					return cl;
				}
			}
			else //if(towa==3)
			{
				if(ath.x-playerx<30 && ath.x-playerx>=0 && playery<=ath.y+20 && playery>=ath.y-20)
				{
					cl.index=count;
					cl.thingnum=2;
					return cl;
				}
			}
			count++;
		}
		count=0;
		for (myobject ath : grasslist) {
			if(towa==0)
			{
				if(ath.y-playery<30 && ath.y-playery>=0 && playerx<=ath.x+20 && playerx>=ath.x-20)
				{
					cl.index=count;
					cl.thingnum=4;
					return cl;
				}
			}else if(towa==1)
			{
				if(playery-ath.y<60 && playery-ath.y>=0 && playerx<=ath.x+20 && playerx>=ath.x-20)
				{
					cl.index=count;
					cl.thingnum=4;
					return cl;
				}
			}
			else if(towa==2)
			{
				if(playerx-ath.x<30 && playerx-ath.x>=0 && playery<=ath.y+20 && playery>=ath.y-20)
				{
					cl.index=count;
					cl.thingnum=4;
					return cl;
				}
			}
			else //if(towa==3)
			{
				if(ath.x-playerx<30 && ath.x-playerx>=0 && playery<=ath.y+20 && playery>=ath.y-20)
				{
					cl.index=count;
					cl.thingnum=4;
					return cl;
				}
			}
			count++;
		}
		return cl;//没找到
	}
	public static void drawunder(Graphics gra){
		ImageIcon icon;
		int indx,indy;
		for(int i=player.wherex()-16;i<=player.wherex()+16;i++)
		{
			for(int j=player.wherey()-9;j<=player.wherey()+9;j++)
			{
				if(i<0 || j<0) continue;
				//System.out.println(readmap.mapunder[j][i]);
				icon=getmap.f(readmap.mapunder[j][i]);
				indx=getmap.getindx(readmap.mapunder[j][i]);
				indy=getmap.getindy(readmap.mapunder[j][i]);
				gra.drawImage(icon.getImage(),
						player.playerframex-mapsize/2+mapsize*(i-player.wherex())-player.deltx%mapsize,
						player.playerframey-mapsize/2+mapsize*(j-player.wherey())-player.delty%mapsize,
						player.playerframex-mapsize/2+mapsize*(1+i-player.wherex())-player.deltx%mapsize,
						player.playerframey-mapsize/2+mapsize*(1+j-player.wherey())-player.delty%mapsize,
						indx*mapsize,indy*mapsize,(indx+1)*mapsize,(indy+1)*mapsize,null);
			}
		}
	}
	public static void drawplayer(Graphics gra){
		if(!up && !down && !left && !right){
			switch (towards) {//战立
			case 0:
				gra.drawImage(icon100.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝下
				break;
			case 1:
				gra.drawImage(icon105.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝上
				break;
			case 2:
				gra.drawImage(icon115.getImage(), playerframex-50, playerframey-80, 100,100,null);//炒作
				break;
			case 3:
				gra.drawImage(icon110.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝右
				break;
			default:
				break;
			}
		}
		else{
			if(up)
			{
				if(uptimes<=everystep)
				{
					gra.drawImage(icon107.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(uptimes<=everystep*2)
				{
					gra.drawImage(icon106.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(uptimes<=everystep*3)
				{
					gra.drawImage(icon107.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(uptimes<=everystep*4)
				{
					gra.drawImage(icon108.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(uptimes<=everystep*5)
				{
					gra.drawImage(icon109.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(uptimes<=everystep*6)
				{
					gra.drawImage(icon108.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
			}
			else if(down){
				if(downtimes<=everystep)
				{
					gra.drawImage(icon102.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(downtimes<=everystep*2)
				{
					gra.drawImage(icon101.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(downtimes<=everystep*3)
				{
					gra.drawImage(icon102.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(downtimes<=everystep*4)
				{
					gra.drawImage(icon103.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(downtimes<=everystep*5)
				{
					gra.drawImage(icon104.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(downtimes<=everystep*6)
				{
					gra.drawImage(icon103.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
			}
			else if(left){
				if(lefttimes<=everystep)
				{
					gra.drawImage(icon117.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(lefttimes<=everystep*2)
				{
					gra.drawImage(icon116.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(lefttimes<=everystep*3)
				{
					gra.drawImage(icon117.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(lefttimes<=everystep*4)
				{
					gra.drawImage(icon118.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(lefttimes<=everystep*5)
				{
					gra.drawImage(icon119.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(lefttimes<=everystep*6)
				{
					gra.drawImage(icon118.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
			}
			else{
				if(righttimes<=everystep)
				{
					gra.drawImage(icon112.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(righttimes<=everystep*2)
				{
					gra.drawImage(icon111.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(righttimes<=everystep*3)
				{
					gra.drawImage(icon112.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(righttimes<=everystep*4)
				{
					gra.drawImage(icon113.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(righttimes<=everystep*5)
				{
					gra.drawImage(icon114.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
				else if(righttimes<=everystep*6)
				{
					gra.drawImage(icon113.getImage(), playerframex-50, playerframey-80, 100,100,null);//朝s
				}
			}
		}
	}
	public static void drawobj(Graphics gra){
		for(tree atree:treelist){//画树
			if(atree.x+80+playerframex>playerx && atree.x-80-playerframex<playerx
					&& atree.y+playerframey>playery && atree.y-245-playerframey<playery)
			{
				if(atree.state==1)
					gra.drawImage(icon9.getImage(), atree.x-80-playerx+playerframex, atree.y-245-playery+playerframey, 160,245,null);
				else {
					gra.drawImage(icon10.getImage(),atree.x-80-playerx+playerframex, atree.y-245-playery+playerframey, 160,245,null);
				}
			}
		}
		for(myobject fl:flowerlist){
			if(fl.x+23+playerframex>playerx && fl.x-23-playerframex<playerx
					&& fl.y+playerframey>playery && fl.y-52-playerframey<playery)
			{
				gra.drawImage(icon5.getImage(), fl.x-23-playerx+playerframex, fl.y-52-playery+playerframey, 46,52,null);
			}
		}
		for(berry aberry:berrylist){//画树
			if(aberry.x+45+playerframex>playerx && aberry.x-45-playerframex<playerx
					&& aberry.y+playerframey>playery && aberry.y-98-playerframey<playery)
			{
				if(aberry.state==1)
					gra.drawImage(icon6.getImage(), aberry.x-45-playerx+playerframex, aberry.y-98-playery+playerframey, 91,98,null);
				else {
					gra.drawImage(icon7.getImage(),aberry.x-45-playerx+playerframex, aberry.y-98-playery+playerframey, 91,98,null);
				}
			}
		}
		for(myobject fl:carrotlist){
			if(fl.x+20+playerframex>playerx && fl.x-20-playerframex<playerx
					&& fl.y+playerframey>playery && fl.y-40-playerframey<playery)
			{
				gra.drawImage(icon8.getImage(), fl.x-20-playerx+playerframex, fl.y-40-playery+playerframey, 40,40,null);
			}
		}
		for(myobject fl:grasslist){
			if(fl.x+23+playerframex>playerx && fl.x-23-playerframex<playerx
					&& fl.y+playerframey>playery && fl.y-85-playerframey<playery)
			{
				gra.drawImage(icon11.getImage(), fl.x-23-playerx+playerframex, fl.y-85-playery+playerframey, 46,85,null);
			}
		}
		ImageIcon icon;
		int indx,indy;
		for(int i=player.wherex()-16;i<=player.wherex()+16;i++)
		{
			for(int j=player.wherey()-9;j<=player.wherey()+9;j++)
			{	
				if(readmap.maphigh[j][i]==0) continue;	
				icon=getmap.f(readmap.maphigh[j][i]);
				indx=getmap.getindx(readmap.maphigh[j][i]);
				indy=getmap.getindy(readmap.maphigh[j][i]);
				gra.drawImage(icon.getImage(),
						player.playerframex-mapsize/2+mapsize*(i-player.wherex())-player.deltx%mapsize,
						player.playerframey-mapsize/2+mapsize*(j-player.wherey())-player.delty%mapsize,
						player.playerframex-mapsize/2+mapsize*(1+i-player.wherex())-player.deltx%mapsize,
						player.playerframey-mapsize/2+mapsize*(1+j-player.wherey())-player.delty%mapsize,
						indx*mapsize,indy*mapsize,(indx+1)*mapsize,(indy+1)*mapsize,null);
			}
		}
	}
	public static void drawstate(Graphics gra){
		Font font = new Font("TimesRoman", Font.PLAIN, 15);
		gra.drawImage(icon36.getImage(), player.playerframex-300, player.playerframey+190, 623,61,null);
		if(player.full>=80)
			gra.drawImage(icon80.getImage(), player.playerframex+300, player.playerframey-230, 60,60,null);
		else if(player.full>=60)
			gra.drawImage(icon81.getImage(), player.playerframex+300, player.playerframey-230, 60,60,null);
		else if(player.full>40)
			gra.drawImage(icon82.getImage(), player.playerframex+300, player.playerframey-230, 60,60,null);
		else if(player.full>20)
			gra.drawImage(icon83.getImage(), player.playerframex+300, player.playerframey-230, 60,60,null);
		else
			gra.drawImage(icon84.getImage(), player.playerframex+300, player.playerframey-230, 60,60,null);
		if(player.healthy>80)
			gra.drawImage(icon85.getImage(), player.playerframex+380, player.playerframey-230, 60,60,null);
		else if(player.healthy>60)
			gra.drawImage(icon86.getImage(), player.playerframex+380, player.playerframey-230, 60,60,null);
		else if(player.healthy>40)
			gra.drawImage(icon87.getImage(), player.playerframex+380, player.playerframey-230, 60,60,null);
		else if(player.healthy>20)
			gra.drawImage(icon88.getImage(), player.playerframex+380, player.playerframey-230, 60,60,null);
		else
			gra.drawImage(icon89.getImage(), player.playerframex+380, player.playerframey-230, 60,60,null);
		for(int i=0;i<10;i++)
		{
			switch (manu1[i]) {
			case 0:
				break;
			case 1:
				gra.drawImage(icon75.getImage(), player.playerframex-280+manudelt[i], player.playerframey+210, 35,35,null);
				gra.setColor(Color.WHITE);
				gra.setFont(font);
				gra.drawString(berrynum+"", player.playerframex-265+manudelt[i], player.playerframey+225);
				break;
			case 2:
				gra.drawImage(icon76.getImage(), player.playerframex-280+manudelt[i], player.playerframey+210, 35,35,null);
				gra.setColor(Color.WHITE);
				gra.setFont(font);
				gra.drawString(carrotnum+"", player.playerframex-265+manudelt[i], player.playerframey+225);
				break;
			case 3:
				gra.drawImage(icon77.getImage(), player.playerframex-280+manudelt[i], player.playerframey+210, 35,35,null);
				gra.setColor(Color.WHITE);
				gra.setFont(font);
				gra.drawString(flowernum+"", player.playerframex-265+manudelt[i], player.playerframey+225);
				break;
			case 4:
				gra.drawImage(icon78.getImage(), player.playerframex-280+manudelt[i], player.playerframey+210, 35,35,null);
				gra.setColor(Color.WHITE);
				gra.setFont(font);
				gra.drawString(grassnum+"", player.playerframex-265+manudelt[i], player.playerframey+225);
				break;
			case 5:
				gra.drawImage(icon79.getImage(), player.playerframex-280+manudelt[i], player.playerframey+210, 35,35,null);
				gra.setColor(Color.WHITE);
				gra.setFont(font);
				gra.drawString(treenum+"", player.playerframex-265+manudelt[i], player.playerframey+225);
				break;
			default:
				break;
			}
		}
	}
	public static void dosomething(int th){

		if(th==0){//空格
			tempclass tc=havething(playerx, playery, towards);
			switch (tc.thingnum) {
			case 0:
				break;
			case 1:
				if(berrylist.get(tc.index).state==2)
					break;
				if(berrynum==0)
				{
					int i=0;
					while(manu1[i]!=0) i++;
					manu1[i]=1;
				}
				berrynum++;
				berry cha;
				cha=berrylist.get(tc.index);
				cha.state=2;
				berrylist.remove(tc.index);
				berrylist.add(cha);
				break;
			case 2:
				if(carrotnum==0)
				{
					int i=0;
					while(manu1[i]!=0) i++;
					manu1[i]=2;
				}
				carrotnum++;
				carrotlist.remove(tc.index);
				break;
			case 3:
				if(flowernum==0)
				{
					int i=0;
					while(manu1[i]!=0) i++;
					manu1[i]=3;
				}
				flowernum++;
				flowerlist.remove(tc.index);
				break;
			case 4:
				if(grassnum==0)
				{
					int i=0;
					while(manu1[i]!=0) i++;
					manu1[i]=4;
				}
				grassnum=grassnum+2;
				grasslist.remove(tc.index);
				break;
			case 5:
				if(treenum==0)
				{
					int i=0;
					while(manu1[i]!=0) i++;
					manu1[i]=5;
				}
				treenum=treenum+4;
				treelist.remove(tc.index);
				break;
			default:
				break;
			}
		}
		else{
			switch (manu1[th-1]) {
			case 0:
				break;
			case 1:
				berrynum--;
				if(berrynum==0) manu1[th-1]=0;
				full=full+20;
				if(full>=100) full=100;
				break;
			case 2:
				carrotnum--;
				if(carrotnum==0) manu1[th-1]=0;
				full=full+10;
				if(full>=100) full=100;
				break;
			case 3:
				flowernum--;
				if(flowernum==0) manu1[th-1]=0;
				healthy=healthy+20;
				if(healthy>=100) healthy=100;
				break;
			case 4:
				grassnum--;
				if(grassnum==0) manu1[th-1]=0;
				//full=full+20;
				//if(full>=100) full=100;
				break;
			case 5:
				treenum--;
				if(treenum==0) manu1[th-1]=0;
				islight=1;
				//full=full+20;
				//if(full>=100) full=100;
				break;
			default:
				break;
			}
		}
	}
	public static int wherex(){
		return (playerx-mapsize/2)/mapsize;
	}
	public static int wherey(){
		return (playery-mapsize/2)/mapsize;
	}
}
