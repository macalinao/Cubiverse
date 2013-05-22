package me.thehutch.cubiverse.data;

import me.thehutch.cubiverse.universe.solarsystem.Star;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyFactory;
import org.spout.api.map.DefaultedKeyImpl;
/**
 * @author thehutch
 */
public class CubiverseData {

	//Universe


	//Solar-System
	public static final DefaultedKey<String> SOLAR_SYSTEM_NAME = new DefaultedKeyImpl<>("name", "NULL_NAME");
	public static final DefaultedKey<Star> SOLAR_SYSTEM_STAR = new DefaultedKeyFactory<>("solar_system_star", Star.class);
	

	//Star
	public static final DefaultedKey<String> STAR_NAME = new DefaultedKeyImpl<>("star_name", "NULL_NAME");
	public static final DefaultedKey<Long> STAR_AGE = new DefaultedKeyImpl<>("star_age", 0L);
	public static final DefaultedKey<Long> STAR_LIFESPAN = new DefaultedKeyImpl<>("star_lifespan", Long.MAX_VALUE);

	//Planet

	//Entity


	//Player

}