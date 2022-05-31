package ru.minersdream.stalker.main;

import org.lwjgl.input.Keyboard;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class KeybindsRegister {
	private static final String catergory = "S.T.A.L.K.E.R.";
    public static final KeyBinding
            KEY_PDA = new KeyBinding("key.pdaopen", Keyboard.KEY_P, catergory);
    public static void register()
    {
        setRegister(KEY_PDA);
    }

    private static void setRegister(KeyBinding binding)
    {
        ClientRegistry.registerKeyBinding(binding);
    }
}
