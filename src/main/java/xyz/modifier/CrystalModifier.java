package xyz.modifier;

import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.core.setting.BooleanSetting;
import org.rusherhack.core.setting.NumberSetting;
import org.rusherhack.core.setting.Setting;

public class CrystalModifier extends ToggleableModule {

    public Setting<Boolean> spin = new BooleanSetting("Spin", "Change the spin speed", true);
    public Setting<Float> spinMultiplier = new NumberSetting<>("SpinMultiplier", 1.0f, 0.0f, 2.0f);

    public Setting<Boolean> disableBounce = new BooleanSetting("DisableBounce", "Disable crystal bounce.", true);

    public Setting<Boolean> changeScale = new BooleanSetting("Scale", "Change the crystal scale", true);
    public Setting<Float> scaleMultiplier = new NumberSetting<>("ScaleMultiplier", 1.0f, 0.1f, 2.0f);

    public CrystalModifier() {
        super("CrystalModifier", ModuleCategory.RENDER);

        this.registerSettings(
                spin,
                disableBounce,
                changeScale
        );

        spin.addSubSettings(spinMultiplier);

        changeScale.addSubSettings(scaleMultiplier);
    }

}
