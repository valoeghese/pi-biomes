package tk.valoeghese.pibiomes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.fabric.api.biomes.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biomes.v1.OverworldClimate;
import net.minecraft.world.biome.Biome;
import tk.valoeghese.pibiomes.BiomeCalculator;

@Mixin(remap = false, value = OverworldBiomes.class)
public class MixinOverworldBiomes {
	@Inject(at = @At("HEAD"), method = "addContinentalBiome")
	private static void catchOverworldBaseBiome(Biome biome, OverworldClimate climate, double weight, CallbackInfo info) {
		BiomeCalculator.overworldBiomes.add(biome);
	}

	@Inject(at = @At("HEAD"), method = "addBiomeVariant")
	private static void catchOverworldBaseBiome2(Biome replaced, Biome variant, double chance, OverworldClimate[] climates, CallbackInfo info) {
		BiomeCalculator.overworldBiomes.add(variant);
	}
}
