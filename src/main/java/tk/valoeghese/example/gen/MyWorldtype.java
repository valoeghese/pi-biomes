package tk.valoeghese.example.gen;

import net.minecraft.world.World;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSource;
import net.minecraft.world.biome.source.VanillaLayeredBiomeSourceConfig;
import net.minecraft.world.gen.chunk.CavesChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.chunk.ChunkGeneratorConfig;
import net.minecraft.world.gen.chunk.ChunkGeneratorType;
import net.minecraft.world.level.LevelGeneratorType;
import tk.valoeghese.example.mixin.AccessorLevelGeneratorType;

public class MyWorldtype {
	// ========== API Stuff: change world type stuff here =========

	// this example generator string will become generator.myworldtype for the language key
	public static final String LEVEL_GENERATOR_STRING = "myworldtype";

	public static ChunkGenerator<? extends ChunkGeneratorConfig> createOverworldChunkGenerator(World world) {
		// example: nether terrain with overworld biomes
		return ChunkGeneratorType.CAVES.create(world,
				new VanillaLayeredBiomeSource(
						new VanillaLayeredBiomeSourceConfig(world.getLevelProperties())
						),
				new CavesChunkGeneratorConfig() {
			@Override
			public int getMaxY() {
				return 256;
			}
		});
	}

	// ========= Impl Stuff ============
	private static int getNextAvailableId() {
		for (int i = currentId; i < LevelGeneratorType.TYPES.length; ++i) {
			if (LevelGeneratorType.TYPES[i] == null) {
				currentId = i;
				return i;
			}
		}

		throw new RuntimeException("No valid level-type ids remaining! Could not retrieve a level type id!");
	}

	public static final LevelGeneratorType LEVEL_TYPE = AccessorLevelGeneratorType.create(getNextAvailableId(), LEVEL_GENERATOR_STRING);
	private static int currentId = 0;
}
