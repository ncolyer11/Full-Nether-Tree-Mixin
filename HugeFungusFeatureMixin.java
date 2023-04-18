package net.fabricmc.example.rtf.mixin;

import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.HugeFungusFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HugeFungusFeature.class)
class HugeFungusFeatureMixin {

	private static final float NEW_TALL_FUNGUS_CHANCE = 0.40F;

	@ModifyArg(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextInt(I)I"))
	private int increaseTallFungusChance(int bound) {
		return (int) (1 / NEW_TALL_FUNGUS_CHANCE);
	}

	@ModifyArg(method = "generateHat", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/HugeFungusFeature;placeHatBlock(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/gen/feature/HugeFungusFeatureConfig;Lnet/minecraft/util/math/BlockPos$Mutable;FFF)V")
			, index = 4)
	private float increaseFungusShroomBloat(float decorationChance) {
		return 0;
	}

	@ModifyArg(method = "generateHat", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/HugeFungusFeature;placeHatBlock(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/gen/feature/HugeFungusFeatureConfig;Lnet/minecraft/util/math/BlockPos$Mutable;FFF)V")
			, index = 5)
	private float increaseFungusWartBloat(float generationChance) {
		return 0;
	}

	@Redirect(method = "placeWithOptionalVines", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/random/Random;nextFloat()F"))
	private float maxVRM(Random instance) {
		return 0.0F;
	}

	/*@Redirect(method = "generateHat", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/HugeFungusFeature;generateHat(Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/random/Random;Lnet/minecraft/world/gen/feature/HugeFungusFeatureConfig;Lnet/minecraft/util/math/BlockPos;IZ)V"))
	private int chonkFungus() {
		return 50;
	}*/
}
