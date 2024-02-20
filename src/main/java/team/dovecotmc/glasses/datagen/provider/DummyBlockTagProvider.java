package team.dovecotmc.glasses.datagen.provider;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.Glasses;

public class DummyBlockTagProvider extends BlockTagsProvider {
    public DummyBlockTagProvider(DataGenerator dataGen, @Nullable ExistingFileHelper existingFileHelper) {
        super(dataGen, Glasses.MODID, existingFileHelper);
    }
}
