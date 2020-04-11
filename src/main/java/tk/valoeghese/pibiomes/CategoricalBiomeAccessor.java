package tk.valoeghese.pibiomes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.world.biome.Biome;

class CategoricalBiomeAccessor {
	private final Map<Biome.Category, List<Biome>> internal = new HashMap<>();

	public void add(Biome biome) {
		this.getListFor(biome.getCategory()).add(biome);
	}

	private List<Biome> getListFor(Biome.Category category) {
		return this.internal.computeIfAbsent(category, e -> new ArrayList<>());
	}

	// pluck a biome from the list
	public Biome pluck(Biome.Category category, IntRandom rand, Biome defaultBiome) {
		List<Biome> biomes = this.getListFor(category);

		if (biomes.isEmpty()) {
			return defaultBiome;
		}

		int index = rand.next(biomes.size());
		Biome result = biomes.get(index);
		biomes.remove(index);
		return result;
	}
}
