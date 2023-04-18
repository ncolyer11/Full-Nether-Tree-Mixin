// just mess around with these variables to your hearts content (just don't go to the nether xd)

package net.fabricmc.example.rtf.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.HugeFungusFeature;
import net.minecraft.world.gen.feature.HugeFungusFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(HugeFungusFeature.class)
abstract
class HugeFungusFeatureMixin {

	@Shadow protected abstract void placeWithOptionalVines(WorldAccess world, Random random, BlockPos pos, BlockState state, boolean vines);

	@Shadow protected abstract void placeHatBlock(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos.Mutable pos, float decorationChance, float generationChance, float vineChance);


	@Overwrite
	private void generateHat(WorldAccess world, Random random, HugeFungusFeatureConfig config, BlockPos pos, int hatHeight, boolean thickStem) {
		int j;
		BlockPos.Mutable mutable = new BlockPos.Mutable();
		boolean bl = config.hatState.isOf(Blocks.NETHER_WART_BLOCK);
		int i = Math.min(random.nextInt(27 + hatHeight * hatHeight * hatHeight / 27) + 125, hatHeight * hatHeight * hatHeight);
		for (int k = j = hatHeight * hatHeight - i; k <= hatHeight * hatHeight * hatHeight; ++k) {
			int l;
			int n = l = k < hatHeight * hatHeight - random.nextInt(30) ? 2 * 15 : 15;
			if (i > 6 && k < j + 70) {
				l = 3 * 15;
			}
			if (thickStem) {
				l = l * 10;
			}
			for (int m = -l; m <= l; ++m) {
				for (int n2 = -l; n2 <= l; ++n2) {
					boolean bl2 = m == -l || m == l;
					boolean bl3 = n2 == -l || n2 == l;
					boolean bl4 = !bl2 && !bl3 && k != hatHeight * hatHeight * hatHeight;
					boolean bl5 = bl2 && bl3;
					boolean bl6 = k < j + 3;
					mutable.set(pos, m, k, n2);
					// if (!HugeFungusFeature.isReplaceable(world, mutable, false)) continue;
					// if (config.planted && !world.getBlockState((BlockPos)mutable.down()).isAir()) {
					//	world.breakBlock(mutable, true);
					//}
					if (bl6) {
						if (bl4) continue;
						this.placeWithOptionalVines(world, random, mutable, config.hatState, bl);
						continue;
					}
					if (bl4) {
						this.placeHatBlock(world, random, config, mutable, 0.05f, 0.1f, bl ? 0.1f : 0.0f);
						continue;
					}
					if (bl5) {
						this.placeHatBlock(world, random, config, mutable, 0.1f, 0.6f, bl ? 0.083f : 0.0f);
						continue;
					}
					this.placeHatBlock(world, random, config, mutable, 0.05f, 0.90f, bl ? 0.07f : 0.0f);
				}
			}
		}
	}
}
