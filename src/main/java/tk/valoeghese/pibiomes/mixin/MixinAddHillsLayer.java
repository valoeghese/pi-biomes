package tk.valoeghese.pibiomes.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.world.biome.layer.AddHillsLayer;
import net.minecraft.world.biome.layer.type.MergingLayer;
import net.minecraft.world.biome.layer.util.LayerFactory;
import net.minecraft.world.biome.layer.util.LayerSampleContext;
import net.minecraft.world.biome.layer.util.LayerSampler;
import tk.valoeghese.pibiomes.BiomeCalculator;
import tk.valoeghese.pibiomes.CommitBadLayer;

@Mixin(AddHillsLayer.class)
public abstract class MixinAddHillsLayer implements MergingLayer {
	@Override
	public <R extends LayerSampler> LayerFactory<R> create(LayerSampleContext<R> context, LayerFactory<R> biome, LayerFactory<R> noise) {
		LayerFactory<R> modifiedBiome = new CommitBadLayer(BiomeCalculator.create()).create(context, biome);

		return () -> {
			R layerSampler = modifiedBiome.make();
			R layerSampler2 = noise.make();
			return context.createSampler((i, j) -> {
				context.initSeed((long)i, (long)j);
				return this.sample(context, layerSampler, layerSampler2, i, j);
			}, layerSampler, layerSampler2);
		};
	}
}
