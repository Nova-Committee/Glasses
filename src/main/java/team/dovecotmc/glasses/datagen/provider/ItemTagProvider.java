package team.dovecotmc.glasses.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.item.base.GlassesItem;

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
        tag(curios_glasses).add(ForgeRegistries.ITEMS.getValues().stream()
                .filter(i -> i instanceof GlassesItem)
                .toArray(Item[]::new));
    }
}
