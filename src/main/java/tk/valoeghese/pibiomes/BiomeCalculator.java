package tk.valoeghese.pibiomes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
	}

	public static BiomeCalculator create() {
		return new BiomeCalculator(genSeed);
	}

	public static void setSeed(long seed) {
		genSeed = seed;
	}

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
