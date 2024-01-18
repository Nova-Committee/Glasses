package team.dovecotmc.glasses.datagen.provider;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import team.dovecotmc.glasses.Glasses;

import java.util.concurrent.CompletableFuture;

public class DummyBlockTagProvider extends BlockTagsProvider {
    public DummyBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Glasses.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider holderLookup) {

    }
}
