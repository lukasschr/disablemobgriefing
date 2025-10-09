package com.github.lukasschr.disablemobgriefing;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.minecraft.world.GameRules;

public class DisableMobGriefingEntrypoint implements ModInitializer {

    public static final GameRules.Key<GameRules.BooleanRule> DISABLE_CREEPER_GRIEFING =
            GameRuleRegistry.register(
                    "disableCreeperGriefing",
                    GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(false)
            );

    public static final GameRules.Key<GameRules.BooleanRule> DISABLE_ENDERMAN_GRIEFING =
            GameRuleRegistry.register(
                    "disableEndermanGriefing",
                    GameRules.Category.PLAYER,
                    GameRuleFactory.createBooleanRule(false)
            );

    @Override
    public void onInitialize() {
    }
}
