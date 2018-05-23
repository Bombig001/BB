package brickbreaker.level;

import java.util.ArrayList;

import brickbreaker.controller.GameController;
import brickbreaker.model.Brick;
import brickbreaker.model.Item;

public class Level {
	private static int maxLevel = 6;
	private ArrayList<Item> brickList;
	// aa = stone, bb = blue, cc = red, dd = yellow, ee = brown, ff = orange, gg = green
	private int __ = 0, aa = 1, bb = 2,cc = 3, dd = 4, ee = 5, ff = 6, gg = 7;
	
//	private int[][] level1 = {
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,bb,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__},
//			{__,__,__,__,__,__,__,__,__,__,__}
//	};
	private int[][] level1 = {
			{__,ee,ee,ee,ee,ee,ee,ee,ee,ee,__},
			{__,ee,ee,ee,ee,ee,ee,ee,ee,ee,__},
			{__,ff,aa,ff,ff,ff,ff,ff,aa,ff,__},
			{__,ff,__,ff,ff,ff,ff,ff,__,ff,__},
			{__,ff,__,ff,ff,ff,ff,ff,__,ff,__},
			{__,ff,__,ff,ff,ff,ff,ff,__,ff,__},
			{__,ff,__,ff,ff,ff,ff,ff,__,ff,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__}
	};
	
	private int[][] level2 = {
			{gg,__,gg,__,dd,aa,dd,__,gg,__,gg},
			{gg,__,gg,__,dd,__,dd,__,gg,__,gg},
			{gg,__,gg,__,gg,__,gg,__,gg,__,gg},
			{gg,__,gg,__,gg,__,gg,__,gg,__,gg},
			{gg,__,gg,__,gg,__,gg,__,gg,__,gg},
			{gg,__,gg,__,gg,__,gg,__,gg,__,gg},
			{gg,__,gg,__,gg,__,gg,__,gg,__,gg},
			{dd,__,dd,__,gg,__,gg,__,dd,__,dd},
			{dd,__,dd,__,gg,__,gg,__,dd,__,dd},
			{dd,__,dd,__,gg,__,gg,__,dd,__,dd},
			{dd,aa,dd,__,gg,__,gg,__,dd,aa,dd},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__}
	};
	
	private int[][] level3 = {
			{__,__,__,__,__,dd,__,__,__,__,__},
			{__,__,__,__,dd,dd,dd,__,__,__,__},
			{__,__,__,dd,dd,dd,dd,dd,__,__,__},
			{__,__,dd,dd,dd,dd,dd,dd,dd,__,__},
			{__,dd,dd,dd,dd,dd,dd,dd,dd,dd,__},
			{__,dd,dd,cc,dd,dd,dd,cc,dd,dd,__},
			{__,dd,cc,dd,cc,dd,cc,dd,cc,dd,__},
			{__,dd,dd,dd,dd,dd,dd,dd,dd,dd,__},
			{__,dd,dd,dd,dd,dd,dd,dd,dd,dd,__},
			{__,dd,dd,dd,dd,dd,dd,dd,dd,dd,__},
			{__,dd,dd,dd,cc,dd,cc,dd,dd,dd,__},
			{__,__,dd,dd,dd,cc,dd,dd,dd,__,__},
			{__,__,__,dd,dd,dd,dd,dd,__,__,__},
			{__,__,__,__,dd,dd,dd,__,__,__,__}
	};
	
	private int[][] level4 = {
			{__,__,dd,__,__,__,__,__,ff,__,__},
			{__,dd,dd,dd,__,__,__,ff,ff,ff,__},
			{dd,dd,dd,dd,dd,__,ff,ff,ff,ff,ff},
			{dd,dd,dd,dd,dd,ff,ff,ff,ff,ff,ff},
			{dd,dd,dd,dd,ff,ff,ff,ff,ff,ff,cc},
			{dd,dd,dd,ff,ff,ff,ff,ff,ff,cc,cc},
			{dd,dd,ff,ff,ff,ff,ff,ff,cc,cc,cc},
			{dd,ff,ff,ff,ff,aa,ff,cc,cc,cc,cc}, 
			{ff,ff,ff,ff,ff,ff,cc,cc,cc,cc,cc},
			{__,ff,ff,ff,ff,cc,cc,cc,cc,cc,__},
			{__,__,ff,ff,cc,cc,cc,cc,cc,__,__},
			{__,__,__,cc,cc,cc,cc,cc,__,__,__},
			{__,__,__,__,cc,cc,cc,__,__,__,__},
			{__,__,__,__,__,cc,__,__,__,__,__}
	};
	
	private int[][] level5 = {
			{__,__,aa,aa,__,__,__,aa,aa,__,__},
			{__,aa,aa,__,__,bb,__,__,aa,aa,__},
			{__,aa,__,__,bb,bb,bb,__,__,aa,__},
			{__,aa,__,bb,bb,bb,bb,bb,__,aa,__},
			{__,aa,__,bb,ee,aa,ee,bb,__,aa,__},
			{__,aa,__,ee,bb,bb,bb,ee,__,aa,__},
			{__,aa,__,ee,bb,bb,bb,ee,__,aa,__},
			{__,aa,__,ee,ee,bb,ee,ee,__,aa,__},
			{__,aa,__,ee,ee,ee,ee,ee,__,aa,__},
			{__,aa,__,ee,ee,ee,ee,ee,__,aa,__},
			{__,aa,__,aa,aa,aa,aa,aa,__,aa,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__},
			{__,__,__,__,__,__,__,__,__,__,__}
	};
	
	
	private int[][] level6 = {
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__},
			{__,__,__,__,__,bb,__,bb,__,__,__}
	};
	
//	public Level(int lvl) {
//		setCurrentLevel(lvl);
//	}
	
	private void initLevel(int[][] mulLvl) {
		int space = (((GameController.defWidth-16) / 2) - 605) / 2;
		
		brickList = new ArrayList<Item>();
		for (int y = 0; y < mulLvl.length; y++) {
			for (int x = 0; x < mulLvl[y].length; x++) {
				if (mulLvl[y][x] == __) {
					
				} else {
					Item brick = new Brick((space)+x*55,(100)+y*19,54,18,mulLvl[y][x]);
					brickList.add(brick);
				}
			}
		}
	}
	
	public void setCurrentLevel(int lvl) {
		int[][] current = level1;
		if (lvl == 1) {
			current = level1;
		} else if (lvl == 2) {
			current = level2;
		} else if (lvl == 3) {
			current = level3;
		}else if (lvl == 4) {
			current = level4;
		}else if (lvl == 5) {
			current = level5;
		}else if (lvl == 6) {
			current = level6;
		}
		initLevel(current);
	}
	
	public ArrayList<Item> getBrickList() {
		return brickList;
	}

	public static int getMaxLevel() {
		return maxLevel;
	}
}
