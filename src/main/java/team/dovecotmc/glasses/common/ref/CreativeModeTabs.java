package team.dovecotmc.glasses.common.ref;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CreativeModeTabs {
    public static final CreativeModeTab GLASSES = new CreativeModeTab("glasses.glasses") {
        @Override
        public ItemStack makeIcon() {
            return ItemRef.GLASSES_5.get().getDefaultInstance();
        }
    };
}
