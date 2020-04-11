package tk.valoeghese.example.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.level.LevelGeneratorType;
import tk.valoeghese.example.gen.MyWorldtype;

@Mixin(LevelGeneratorType.class)
public class MixinLevelGeneratorType {
	// Neccessary for server side
	@Inject(at = @At("HEAD"), method = "getTypeFromName", cancellable = true)
	private static void getTypeFromName(String name, CallbackInfoReturnable<LevelGeneratorType> info) {
		if (name.equalsIgnoreCase(MyWorldtype.LEVEL_GENERATOR_STRING)) {
			info.setReturnValue(MyWorldtype.LEVEL_TYPE);
		}
	}
}
