package team.dovecotmc.glasses.datagen.handler;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.datagen.provider.DummyBlockTagProvider;
import team.dovecotmc.glasses.datagen.provider.ItemTagProvider;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Glasses.MODID)
public class DataGenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final PackOutput output = generator.getPackOutput();
        final ExistingFileHelper fh = event.getExistingFileHelper();
        final CompletableFuture<HolderLookup.Provider> lookup = event.getLookupProvider();
        final BlockTagsProvider blockTags = new DummyBlockTagProvider(output, lookup, fh);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ItemTagProvider(output, lookup, blockTags.contentsGetter(), fh));
    }
}
