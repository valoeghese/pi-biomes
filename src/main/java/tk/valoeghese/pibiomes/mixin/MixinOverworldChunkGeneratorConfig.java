package tk.valoeghese.pibiomes.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import net.minecraft.world.gen.chunk.OverworldChunkGeneratorConfig;

@Mixin(OverworldChunkGeneratorConfig.class)
public class MixinOverworldChunkGeneratorConfig {
	@Overwrite
	public int getBiomeSize() {
		return 5;
	}
}
