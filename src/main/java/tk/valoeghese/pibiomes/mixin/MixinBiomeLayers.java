package tk.valoeghese.pibiomes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.biome.layer.BiomeLayers;
import net.minecraft.world.biome.source.BiomeLayerSampler;
import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;
import net.minecraft.world.level.LevelGeneratorType;
import tk.valoeghese.pibiomes.BiomeCalculator;

@Mixin(BiomeLayers.class)
public class MixinBiomeLayers {
	@Inject(at = @At("HEAD"), method = "build")
	private static void build(long seed, LevelGeneratorType generatorType, OverworldChunkGeneratorConfig settings, CallbackInfoReturnable<BiomeLayerSampler> info) {
		BiomeCalculator.setSeed(seed);
	}
}
