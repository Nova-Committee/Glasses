package team.dovecotmc.glasses.common.init;

import team.dovecotmc.glasses.common.ref.CreativeModeTabRef;
import team.dovecotmc.glasses.common.ref.ItemRef;

public class RegistryHandler {
    public static void init() {
        ItemRef.init();
        CreativeModeTabRef.init();
    }
}
