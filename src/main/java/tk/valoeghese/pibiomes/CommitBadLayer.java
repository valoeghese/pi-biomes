package tk.valoeghese.pibiomes;

import net.minecraft.world.biome.layer.type.ParentedLayer;
import net.minecraft.world.biome.layer.util.IdentityCoordinateTransformer;
import net.minecraft.world.biome.layer.util.LayerSampleContext;
import net.minecraft.world.biome.layer.util.LayerSampler;

public class CommitBadLayer implements ParentedLayer, IdentityCoordinateTransformer {
	public CommitBadLayer(BiomeCalculator calculator) {
		this.calculator = calculator;
	}

	private final BiomeCalculator calculator;

	@Override
	public int sample(LayerSampleContext<?> context, LayerSampler parent, int x, int z) {
		return this.calculator.calculate(parent, x, z);
	}
}
