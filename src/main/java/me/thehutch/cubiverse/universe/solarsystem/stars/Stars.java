package me.thehutch.cubiverse.universe.solarsystem.stars;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
/**
 * @author thehutch
 */
public class Stars {

	public static final int NUM_STAR_NAMES = 100;

	public static String generateStarName() {
		return "Test_Star";
		/*
		try (BufferedReader br = new BufferedReader(new FileReader(new File("star-names.txt")))){
			int line = new Random().nextInt(NUM_STAR_NAMES);
			for(int i=0 ; i<line ; i++) {
				br.readLine();
			}
			return br.readLine();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return "NULL_STAR_NAME";*/
	}
}