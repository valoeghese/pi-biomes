package tk.valoeghese.pibiomes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.biome.layer.util.LayerSampler;

public class BiomeCalculator {
	public BiomeCalculator(long seed) {
		Random random = new Random(seed);
		IntRandom rand = random::nextInt;

		CategoricalBiomeAccessor accessor = new CategoricalBiomeAccessor();
		overworldBiomes.forEach(accessor::add);

		List<Biome> result = new ArrayList<>();

		// add 10 biomes
		result.add(accessor.pluck(Biome.Category.DESERT, rand, Biomes.DESERT));
		result.add(accessor.pluck(Biome.Category.EXTREME_HILLS, rand, Biomes.MOUNTAINS));
		result.add(accessor.pluck(Biome.Category.FOREST, rand, Biomes.FOREST));
		result.add(accessor.pluck(Biome.Category.ICY, rand, Biomes.SNOWY_TUNDRA));
		result.add(accessor.pluck(Biome.Category.JUNGLE, rand, Biomes.DESERT));
		result.add(accessor.pluck(Biome.Category.MESA, rand, Biomes.BADLANDS));
		result.add(accessor.pluck(Biome.Category.PLAINS, rand, Biomes.DESERT));
		result.add(accessor.pluck(Biome.Category.TAIGA, rand, Biomes.FOREST));
		result.add(accessor.pluck(Biome.Category.SAVANNA, rand, Biomes.SAVANNA));
		result.add(accessor.pluck(Biome.Category.SWAMP, rand, Biomes.SWAMP));

		// shuffle
		Collections.shuffle(result, random);

		// remove two to make 8 biomes
		result.remove(0);
		result.remove(0);

		// create array
		this.biomes = result.toArray(new Biome[8]);
	}

	private final Biome[] biomes;

	public int calculate(LayerSampler far, int x, int z) {
		int squaredDist = x * x + z * z;

		if (squaredDist < CENTRE_SQR_DIST) {
			return 1; // plains
		} else if (squaredDist < MIN_SQR_DIST) {
			return 0; // ocean
		} else if (squaredDist < MAX_SQR_DIST) {
			int abx = Math.abs(x);
			int abz = Math.abs(z);
			int index = (x > 0 ? 4 : 0) | (z > 0 ? 2 : 0) | (abz > abx ? 1 : 0);
			return Registry.BIOME.getRawId(this.biomes[index]);
		} else {
			return far.sample(x, z);
		}
	}

	public static BiomeCalculator create() {
		return new BiomeCalculator(genSeed);
	}

	public static void setSeed(long seed) {
		genSeed = seed;
	}

	// not at normal block nor chunk scale: much larger
	public static final int CENTRE_SQR_DIST = 1 * 1; // centre plains island
	public static final int MIN_SQR_DIST = 3 * 3;
	public static final int MAX_SQR_DIST = 5 * 5;
	public static final int REVERT_SQR_DIST = 7 * 7; // for reverting to normal terrain gen, at larger biome size

	public static final Set<Biome> overworldBiomes = new HashSet<>();
	private static long genSeed;

	static {
		overworldBiomes.add(Biomes.BADLANDS);
		overworldBiomes.add(Biomes.BIRCH_FOREST);
		overworldBiomes.add(Biomes.DARK_FOREST);
		overworldBiomes.add(Biomes.DESERT);
		overworldBiomes.add(Biomes.FOREST);
		overworldBiomes.add(Biomes.GIANT_TREE_TAIGA);
		overworldBiomes.add(Biomes.JUNGLE);
		overworldBiomes.add(Biomes.MOUNTAINS);
		overworldBiomes.add(Biomes.PLAINS);
		overworldBiomes.add(Biomes.SAVANNA);
		overworldBiomes.add(Biomes.SNOWY_TAIGA);
		overworldBiomes.add(Biomes.SNOWY_TUNDRA);
		overworldBiomes.add(Biomes.SWAMP);
	}
}
