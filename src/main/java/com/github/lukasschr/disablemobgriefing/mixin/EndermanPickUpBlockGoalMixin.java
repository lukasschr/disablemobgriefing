package com.github.lukasschr.disablemobgriefing.mixin;

import com.github.lukasschr.disablemobgriefing.DisableMobGriefingEntrypoint;
import net.minecraft.entity.mob.EndermanEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.entity.mob.EndermanEntity$PickUpBlockGoal")
public class EndermanPickUpBlockGoalMixin {

    @Shadow @Final private EndermanEntity enderman;

    /**
     * @author <a href="http://github.com/lukasschr">lukasschr</a>
     */
    @Inject(method = "canStart", at = @At("HEAD"), cancellable = true)
    private void canStartMixin(CallbackInfoReturnable<Boolean> cir) {
        if (
                this.enderman.getEntityWorld().getServer() != null &&
                this.enderman.getEntityWorld().getServer().getGameRules()
                        .getBoolean(DisableMobGriefingEntrypoint.DISABLE_ENDERMAN_GRIEFING)
        ) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
