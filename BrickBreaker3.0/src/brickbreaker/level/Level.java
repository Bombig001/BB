package brickbreaker.level;

import java.util.ArrayList;
import brickbreaker.model.Brick;
import brickbreaker.model.Item;

public class Level {
	private int maxLevel = 3;
	private ArrayList<Item> brickList;
	private int __ = 0, aa = 1, bb =2,cc = 3;
	private int[][] level1 = {
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,aa,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__}
	};
	
	private int[][] level2 = {
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,bb,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__},
			{ __,__,__,__,__,__,__,__,__,__,__}
	};
	
	private int[][] level3 = {
			{ __,__,__,__,__,cc,__,__,__,__,__},
			{ __,__,__,__,cc,cc,cc,__,__,__,__},
			{ __,__,__,cc,cc,cc,cc,cc,__,__,__},
			{ __,__,cc,cc,cc,cc,cc,cc,cc,__,__},
			{ __,cc,cc,cc,cc,cc,cc,cc,cc,cc,__},
			{ __,cc,cc,bb,cc,cc,cc,bb,cc,cc,__},
			{ __,cc,bb,cc,bb,cc,bb,cc,bb,cc,__},
			{ __,cc,cc,cc,cc,cc,cc,cc,cc,cc,__},
			{ __,cc,cc,cc,cc,cc,cc,cc,cc,cc,__},
			{ __,cc,cc,cc,cc,cc,cc,cc,cc,cc,__},
			{ __,cc,cc,cc,bb,cc,bb,cc,cc,cc,__},
			{ __,__,cc,cc,cc,bb,cc,cc,cc,__,__},
			{ __,__,__,cc,cc,cc,cc,cc,__,__,__},
			{ __,__,__,__,cc,cc,cc,__,__,__,__}
	};
	
//	public Level(int lvl) {
//		setCurrentLevel(lvl);
//	}
	
	private void initLevel(int[][] mulLvl) {		
		brickList = new ArrayList<Item>();
		for (int y = 0; y < mulLvl.length; y++) {
			for (int x = 0; x < mulLvl[y].length; x++) {
				if (mulLvl[y][x] == __) {
					
				} else {
					//Item brick = new Brick((17)+x*(78),(60)+y*(27),77,26,mulLvl[y][x]);
					Item brick = new Brick((50)+x*55,(100)+y*19,54,18,mulLvl[y][x]);
					//g.fillRect(brickX+(x*31), brickY+(y*31), 30, 30);
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
		}
		initLevel(current);
	}
	
	public ArrayList<Item> getBrickList() {
		return brickList;
	}

	public int getMaxLevel() {
		return maxLevel;
	}
	
	

//	public void draw(Graphics gfx) {
//		for( Item b : brickList) {
//			int x = b.getPos().getPosX().intValue();
//			int y = b.getPos().getPosY().intValue();
//			int w = b.getPos().getWidth().intValue();
//			int h = b.getPos().getHeight().intValue();
//			if (!((Brick) b).getIsSmashed()) {
//				b.draw(gfx);
//			}
//		}
//	}
}
