package de.tudarmstadt.informatik.fop.portal.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import de.tudarmstadt.informatik.fop.portal.constants.GameParameters;

/**
 * Map parser
 * @author Timo Küster, Diana Kolev
 *
 */
public class MapParser implements GameParameters{
	
	private int[][] map = new int[MAP_HEIGHT][MAP_WIDTH];
	
	/**
	 * Constructor
	 * @param mapName map name
	 * @throws IOException
	 */
	public MapParser(String mapName) throws IOException {
		String[] lines = new String[MAP_HEIGHT];
		
		BufferedReader br = new BufferedReader(new FileReader(mapName));
		
		for(int i = 0; i < MAP_HEIGHT; i++){
			lines[i] = br.readLine();
		}
		
		br.close();
		
		int counter = 0;
		
		for(int i = 0; i < MAP_HEIGHT; i++){
			for(int j = 0; j < 2 * MAP_WIDTH; j += 2){
				map[i][counter] = (int) (lines[i].charAt(j) - '0');
				counter++;
				if(counter == MAP_WIDTH){
					counter = 0;
				}
			}
		}
	}
	
	/**
	 * Get the map
	 * @return map
	 */
	public int[][] getMap(){
		return map;
	}
}
