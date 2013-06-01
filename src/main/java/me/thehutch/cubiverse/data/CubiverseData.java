package me.thehutch.cubiverse.data;

import me.thehutch.cubiverse.universe.solarsystem.SolarSystem;
import me.thehutch.cubiverse.universe.solarsystem.planets.Planet;
import me.thehutch.cubiverse.universe.solarsystem.stars.Star;
import org.spout.api.map.DefaultedKey;
import org.spout.api.map.DefaultedKeyImpl;
import org.spout.api.math.Vector3;

/**
 * @author thehutch
 */
public class CubiverseData {

	//Solar-System
	public static final DefaultedKey<String> SOLAR_SYSTEM_NAME = new DefaultedKeyImpl<>("SolarSystemName", SolarSystem.DEFAULT_SOLAR_SYSTEM_NAME);
	public static final DefaultedKey<Star> SOLAR_SYSTEM_STAR = new DefaultedKeyImpl<>("SolarSystemStar", SolarSystem.DEFAULT_STAR);
	//Star
	public static final DefaultedKey<String> STAR_NAME = new DefaultedKeyImpl<>("StarName", Star.DEFAULT_NAME);
	public static final DefaultedKey<Integer> STAR_LIFESPAN = new DefaultedKeyImpl<>("StarLifespan", Star.DEFAULT_LIFESPAN);
	public static final DefaultedKey<Integer> STAR_AGE = new DefaultedKeyImpl<>("StarAge", 0);
	//Planet
	public static final DefaultedKey<Climate> PLANET_CLIMATE = new DefaultedKeyImpl<>("PlanetClimate", Planet.DEFAULT_CLIMATE);
	public static final DefaultedKey<Double> PLANET_DISTANCE = new DefaultedKeyImpl<>("PlanetDistance", -1.0);
	public static final DefaultedKey<Vector3> PLANET_LOCATION = new DefaultedKeyImpl<>("PlanetLocation", Vector3.ZERO);
	public static final DefaultedKey<Integer> PLANET_ROTATION_TIME = new DefaultedKeyImpl<>("PlanetRotation", Planet.DEFAULT_ROTATION_TIME);
	//Entity
	//Player
}