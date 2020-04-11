package tk.valoeghese.pibiomes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.biome.layer.AddHillsLayer;
import net.minecraft.world.biome.layer.util.LayerSampler;
import tk.valoeghese.pibiomes.BiomeCalculator;
import tk.valoeghese.pibiomes.Targets;

@Mixin(AddHillsLayer.class)
public class MixinAddHillsLayer {
	// haha yes
	@Redirect(at = @At(value = "INVOKE", target = Targets.LAYERSAMPLER_SAMPLE, ordinal = 0), method = Targets.ADDHILLS_SAMPLE)
	public int calculateBiome(LayerSampler sampler, int x, int z) {
		if (this.pibiomes$calculator == null) {
			this.pibiomes$calculator = BiomeCalculator.create();
		}

		return this.pibiomes$calculator.calculate(sampler, x, z);
	}

	private BiomeCalculator pibiomes$calculator;
}
