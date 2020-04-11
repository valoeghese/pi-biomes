package tk.valoeghese.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.level.LevelGeneratorType;
import tk.valoeghese.example.gen.MyWorldtype;

public class ExampleMod implements ModInitializer {
	@Override
	public void onInitialize() {
	}

	// used to initialise on client
	public static LevelGeneratorType LEVEL_TYPE = MyWorldtype.LEVEL_TYPE;
}
