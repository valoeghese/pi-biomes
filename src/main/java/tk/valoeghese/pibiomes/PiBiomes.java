package tk.valoeghese.pibiomes;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.minecraft.world.biome.Biomes;

public class PiBiomes implements ModInitializer {
	@Override
	public void onInitialize() {
		OverworldBiomes.addHillsBiome(Biomes.BADLANDS, Biomes.BADLANDS_PLATEAU, 1.0f);
	}
}
