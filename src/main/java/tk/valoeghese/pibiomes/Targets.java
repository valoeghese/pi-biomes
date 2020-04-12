package tk.valoeghese.pibiomes;

public class Targets {
	public static final String LAYERSAMPLER_SAMPLE = "Lnet/minecraft/world/biome/layer/util/LayerSampler;sample(II)I";
	public static final String ADDHILLS_SAMPLE = "Lnet/minecraft/world/biome/layer/AddHillsLayer;sample(Lnet/minecraft/world/biome/layer/util/LayerRandomnessSource;Lnet/minecraft/world/biome/layer/util/LayerSampler;Lnet/minecraft/world/biome/layer/util/LayerSampler;II)I";
	public static final String BIOMELAYERS_BUILDSAMPLER = "Lnet/minecraft/world/biome/layer/BiomeLayers;build(JLnet/minecraft/world/level/LevelGeneratorType;Lnet/minecraft/world/gen/chunk/OverworldChunkGeneratorConfig;)Lnet/minecraft/world/biome/source/BiomeLayerSampler;";
	public static final String BIOMELAYERS_BUILDLAYERS = "build(Lnet/minecraft/world/level/LevelGeneratorType;Lnet/minecraft/world/gen/chunk/OverworldChunkGeneratorConfig;Ljava/util/function/LongFunction;)Lnet/minecraft/world/biome/layer/util/LayerFactory;";
}
