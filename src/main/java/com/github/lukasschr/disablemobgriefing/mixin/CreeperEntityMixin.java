package com.github.lukasschr.disablemobgriefing.mixin;


import com.github.lukasschr.disablemobgriefing.DisableMobGriefingEntrypoint;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin extends HostileEntity {

    @Shadow private int explosionRadius;

    protected CreeperEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author <a href="http://github.com/lukasschr">lukasschr</a>
     */
    @Inject(method = "explode", at = @At("HEAD"), cancellable = true)
    private void explodeMixin(CallbackInfo ci){
        if (
                this.getWorld().getServer().getGameRules()
                .getBoolean(DisableMobGriefingEntrypoint.DISABLE_CREEPER_GRIEFING)
        ) {
            ci.cancel();
            this.getWorld().createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    this.explosionRadius,
                    false,
                    World.ExplosionSourceType.NONE
            );
            this.discard();
        }
    }
}
