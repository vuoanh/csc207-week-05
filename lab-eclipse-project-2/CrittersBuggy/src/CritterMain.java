import java.io.*;
import java.util.*;

/**
 *  CritterMain is the main driver of the critter zoo program.
 */
public class CritterMain {

	///// PROGRAM CONSTANTS --- MODIFY THESE FOR TESTING PURPOSES /////
	
	public static final int WIDTH  = 50;
	public static final int HEIGHT = 50;
	public static final int RANDOM_GRASS_PROB = 512;
	public static final int HUNGER_LIMIT = 50;
	public static final int NUM_INITIAL_CRITTERS = 25;
	public static final int NUM_ACTIVE_SPECIES = 8;
	public static final double INITIAL_GRASS_RATIO = .15;
	
	///// ======================================================= /////

	/**
	 * @ return true if the given Class object is a valid Critter subclass.
	 * @param cls the class object to test
	 */
	public static boolean isValidCritterClass(Class<?> cls) {
		if (!cls.equals(Critter.class) && Critter.class.isAssignableFrom(cls)) {
			return cls.getConstructors().length == 1;
		} else {
			return false;
		}
	}

	/**
	 * @return an array of Class objects that correspond to subclasses of the
	 * Critter class found in the given directory
	 * @param dir the directory to search
	 */
	public static List<Class<?>> discoverCritters(File dir) {
		List<Class<?>> classes = new ArrayList<Class<?>>();
		String[] names = dir.list(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".class");
			}
		});
		for (String name : names) {
			name = name.substring(0, name.lastIndexOf(".class"));
			// NOTE: we make the assumption that the fully-qualified class name
			// coincides directly with the name of the file.  This is only true
			// when the class is in the default package (i.e., has no qualifications).
			Class<?> cls = null;
			try {
				cls = Class.forName(name);
			} catch (ClassNotFoundException e) {
				System.out.println("WARNING: class with non-trivial qualified name: " + name);
			}
			if (cls != null && isValidCritterClass(cls) && cls.getAnnotation(NoLoad.class) == null) {
				classes.add(cls);
			}
		}
		return classes;
	}

	public static void main(String[] args) {
		String path = System.getProperty("user.dir");
		List<Class<?>> species = discoverCritters(new File(path));
		System.out.println("Loaded " + species.size() + " critters...");
		CritterModel model = new CritterModel(WIDTH, HEIGHT, RANDOM_GRASS_PROB, HUNGER_LIMIT,
		                                      species, NUM_INITIAL_CRITTERS, NUM_ACTIVE_SPECIES,
														  INITIAL_GRASS_RATIO);
		CritterFrame frame = new CritterFrame(WIDTH, HEIGHT, model);
		frame.pack();
		frame.setVisible(true);
		frame.repaint();
	}
}
