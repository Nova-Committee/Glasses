package team.dovecotmc.glasses.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.ref.ItemRef;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends ItemTagsProvider {
    static final TagKey<Item> curios_glasses = ItemTags.create(new ResourceLocation("curios", "glasses"));

    public ItemTagProvider(
            PackOutput output, CompletableFuture<HolderLookup.Provider> holderLookup,
            CompletableFuture<TagLookup<Block>> tagLookup, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, holderLookup, tagLookup, Glasses.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider holderLookup) {
        tag(curios_glasses).add(Arrays.stream(ItemRef.values())
                .map(ItemRef::get)
                .toArray(Item[]::new));
    }
}
