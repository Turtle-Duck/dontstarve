package main;

import java.util.Date;

import javax.swing.ImageIcon;

public class getmap implements normal {

	static ImageIcon f(int num)
	{
		Date nowtime=new Date();
		if(num<=9)
		{
			if(nowtime.getTime()-player.temptime2 < everytime)
				return icon1;
			else if(nowtime.getTime()-player.temptime2 < everytime*2)
				return icon16;
			else if(nowtime.getTime()-player.temptime2 < everytime*3)
				return icon19;
			else if(nowtime.getTime()-player.temptime2 < everytime*4)
				return icon22;
			else if(nowtime.getTime()-player.temptime2 < everytime*6)
				return icon25;
			else if(nowtime.getTime()-player.temptime2 < everytime*7)
				return icon22;
			else if(nowtime.getTime()-player.temptime2 < everytime*8)
				return icon19;
			else if(nowtime.getTime()-player.temptime2 < everytime*9)
				return icon16;
			else {
				player.temptime2=nowtime.getTime();
				return icon1;
			}
		}
		else if(num<=18)
		{
			if(nowtime.getTime()-player.temptime2 < everytime)
				return icon2;
			else if(nowtime.getTime()-player.temptime2 < everytime*2)
				return icon17;
			else if(nowtime.getTime()-player.temptime2 < everytime*3)
				return icon20;
			else if(nowtime.getTime()-player.temptime2 < everytime*4)
				return icon23;
			else if(nowtime.getTime()-player.temptime2 < everytime*6)
				return icon26;
			else if(nowtime.getTime()-player.temptime2 < everytime*7)
				return icon23;
			else if(nowtime.getTime()-player.temptime2 < everytime*8)
				return icon20;
			else if(nowtime.getTime()-player.temptime2 < everytime*9)
				return icon17;
			else {
				player.temptime2=nowtime.getTime();
				return icon2;
			}
		}
		else if(num<=27)
		{
			if(nowtime.getTime()-player.temptime2 < everytime)
				return icon3;
			else if(nowtime.getTime()-player.temptime2 < everytime*2)
				return icon18;
			else if(nowtime.getTime()-player.temptime2 < everytime*3)
				return icon21;
			else if(nowtime.getTime()-player.temptime2 < everytime*4)
				return icon24;
			else if(nowtime.getTime()-player.temptime2 < everytime*6)
				return icon27;
			else if(nowtime.getTime()-player.temptime2 < everytime*7)
				return icon24;
			else if(nowtime.getTime()-player.temptime2 < everytime*8)
				return icon21;
			else if(nowtime.getTime()-player.temptime2 < everytime*9)
				return icon18;
			else {
				player.temptime2=nowtime.getTime();
				return icon3;
			}
		}
		else if(num<=159)
		{
			if(nowtime.getTime()-player.temptime2 < everytime)
				return icon4;
			else if(nowtime.getTime()-player.temptime2 < everytime*2)
				return icon32;
			else if(nowtime.getTime()-player.temptime2 < everytime*3)
				return icon33;
			else if(nowtime.getTime()-player.temptime2 < everytime*4)
				return icon34;
			else if(nowtime.getTime()-player.temptime2 < everytime*6)
				return icon35;
			else if(nowtime.getTime()-player.temptime2 < everytime*7)
				return icon34;
			else if(nowtime.getTime()-player.temptime2 < everytime*8)
				return icon33;
			else if(nowtime.getTime()-player.temptime2 < everytime*9)
				return icon32;
			else {
				player.temptime2=nowtime.getTime();
				return icon4;
			}
		}
		else if(num<=187)
			return icon14;
		else {
			if(nowtime.getTime()-player.temptime2 < everytime)
				return icon15;
			else if(nowtime.getTime()-player.temptime2 < everytime*2)
				return icon28;
			else if(nowtime.getTime()-player.temptime2 < everytime*3)
				return icon29;
			else if(nowtime.getTime()-player.temptime2 < everytime*4)
				return icon30;
			else if(nowtime.getTime()-player.temptime2 < everytime*6)
				return icon31;
			else if(nowtime.getTime()-player.temptime2 < everytime*7)
				return icon30;
			else if(nowtime.getTime()-player.temptime2 < everytime*8)
				return icon29;
			else if(nowtime.getTime()-player.temptime2 < everytime*9)
				return icon28;
			else {
				player.temptime2=nowtime.getTime();
				return icon15;
			}
		}
	}
	static int getindx(int num)
	{
		if(num<=9)
			return (num-1)%3;
		else if(num<=18)
			return (num-10)%3;
		else if(num<=27)
			return (num-19)%3;
		else if(num<=159)
			return (num-28)%12;
		else if(num<=187)
			return (num-160)%4;
		else {
			return (num-188)%3;
		}
	}
	static int getindy(int num)
	{
		if(num<=9)
			return (num-1)/3;
		else if(num<=18)
			return (num-10)/3;
		else if(num<=27)
			return (num-19)/3;
		else if(num<=159)
			return (num-28)/12;
		else if(num<=187)
			return (num-160)/4;
		else {
			return (num-188)/3;
		}
	}

}
