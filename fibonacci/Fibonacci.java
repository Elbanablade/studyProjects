import java.math.BigInteger;
import java.util.*;

import java.awt.*;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class Fibonacci
{
	public static void main(String[] args)
	{

		if(args.length > 0)
		{
			java.util.List<BigInteger> fib = getFib(Integer.valueOf(args[0]) + 1);
			int[][] pic = fillPicture(fib);


			for(int i = 0; i < pic.length; i++)
			{
				for(int j = 0; j < pic[i].length; j++)
				{
//					System.out.print(pic[i][j]);
				}
//				System.out.println();
			}
			writePictureToFile(pic);
/*
			for(int i = 0; i < fib.size(); i++)
			{
				System.out.println(i + ":\t" + fib.get(i));
			}
*/
		}
		else
		{
			System.out.println("enter a number of runs");
		}
	}

	public static java.util.List<BigInteger> getFib(long max)
	{
		BigInteger fib = new BigInteger("1");
		BigInteger two = new BigInteger("2");
		java.util.List<BigInteger> res = new ArrayList<BigInteger>();
		res.add(fib);
		res.add(fib);

		for(int i = 2; i < max; i++)
		{
			fib = fib.add(res.get(i-1));
			res.add(fib);
		}

		return res;
	}

	public static int[][] fillPicture(java.util.List<BigInteger> fib)
	{
		int[][] pic = new int[fib.get(fib.size() - 1).intValue()*3][fib.get(fib.size() - 1).intValue()*3];
		int center = pic.length/2;
		int prev = center;
		int pos = fib.get(0).intValue();

		pic = drawLine(center, center, center+pos, center, pic, 1);

		prev = pos;

		for(int i = 1; i < fib.size(); i++)
		{
			pos = fib.get(i).intValue();
			switch(i%4)
			{
				case 0:
					pic[center+pos][center] = i;
					pic = drawLine(center-prev, center+pos, center, center+pos, pic, i);
					pic = drawLine(center-prev, center, center-prev, center+pos, pic, i);
					break;
				case 1:
					pic = drawLine(center+pos, center+prev, center, center+prev, pic, i);
					pic = drawLine(center+pos, center, center+pos, center+prev, pic, i);
					break;
				case 2:
					pic = drawLine(center+prev, center-pos, center, center-pos, pic, i);
					pic = drawLine(center+prev, center, center+prev, center-pos, pic, i);
					break;
				case 3:
					pic = drawLine(center-pos, center-prev, center, center-prev, pic, i);
					pic = drawLine(center-pos, center, center-pos, center-prev, pic, i);
					break;
			}
			prev = pos;
		}

		return pic;
	}

	private static int[][] drawLine(int sX, int sY, int eX, int eY, int[][] pic, int num)
	{
		if(sX > eX)
		{
			int temp = sX;
			sX = eX;
			eX = temp;
		}
		if(sY > eY)
		{
			int temp = sY;
			sY = eY;
			eY = temp;
		}


		for(int i = sX; i <= eX; i++)
		{
			for(int j = sY; j <= eY; j++)
			{
				pic[j][i] = 1000;
			}
		}
		return pic;

	}

	public static void writePictureToFile(int[][] picture)
	{
		try
		{
			BufferedImage img=new BufferedImage(picture.length,picture.length,BufferedImage.TYPE_INT_RGB);
			int rgb = 1;
			for(int i = 0; i < picture.length; i++)
			{
				for(int j = 0; j < picture.length; j++)
				{
					img.setRGB(i, j, picture[i][j]);
				}
			}
			ImageIO.write(img, "jpg", new File("abc.jpg"));
		}
		catch(Exception e)
		{
			System.out.println("failed: " + e);
		}
	}

	public static Image getImageFromArray(int[] pixels, int width, int height) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            WritableRaster raster = (WritableRaster) image.getData();
            raster.setPixels(0,0,width,height,pixels);
            return image;
        }

	public static int[] convert3dTo2d(int[][] arr)
	{
		int[] ret = new int[arr.length*arr[0].length];
		for(int i = 0; i < arr.length; i++)
		{
			for(int j = 0; j < arr[i].length; j++)
			{
				ret[i+j] = arr[i][j];
			}
		}
		return ret;
	}
}
