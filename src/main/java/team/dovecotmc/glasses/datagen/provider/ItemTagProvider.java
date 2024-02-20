package team.dovecotmc.glasses.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.common.ref.ItemRef;

import java.util.Arrays;

public class ItemTagProvider extends ItemTagsProvider {
    static final TagKey<Item> curios_glasses = ItemTags.create(new ResourceLocation("curios", "glasses"));


    public ItemTagProvider(
            DataGenerator dataGen, BlockTagsProvider provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGen, provider, Glasses.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(curios_glasses).add(Arrays.stream(ItemRef.values())
                .map(ItemRef::get)
                .toArray(Item[]::new));
    }
}
