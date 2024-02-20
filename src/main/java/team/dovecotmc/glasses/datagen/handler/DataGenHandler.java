package team.dovecotmc.glasses.datagen.handler;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;
import team.dovecotmc.glasses.Glasses;
import team.dovecotmc.glasses.datagen.provider.DummyBlockTagProvider;
import team.dovecotmc.glasses.datagen.provider.ItemTagProvider;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = Glasses.MODID)
public class DataGenHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        final DataGenerator generator = event.getGenerator();
        final ExistingFileHelper fh = event.getExistingFileHelper();
        final BlockTagsProvider blockTags = new DummyBlockTagProvider(generator, fh);
        if (event.includeServer()) {
            generator.addProvider(blockTags);
            generator.addProvider(new ItemTagProvider(generator, blockTags, fh));
        }
    }
}
