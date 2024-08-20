package net.ananaseek.tutorialmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public abstract class AbstractSillyArrow extends AbstractArrow {

    protected AbstractSillyArrow(EntityType<? extends AbstractArrow> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    protected AbstractSillyArrow(EntityType<? extends AbstractArrow> pEntityType, double pX, double pY, double pZ, Level pLevel) {
        this(pEntityType, pLevel);
        this.setPos(pX, pY, pZ);
    }

    protected AbstractSillyArrow(EntityType<? extends AbstractArrow> pEntityType, LivingEntity pShooter, Level pLevel) {
        this(pEntityType, pShooter.getX(), pShooter.getEyeY() - (double)0.1F, pShooter.getZ(), pLevel);
        this.setOwner(pShooter);
        if (pShooter instanceof Player) {
            this.pickup = AbstractArrow.Pickup.ALLOWED;
        }

    }

    public static AbstractSillyArrow createArrow(Level pLevel, ItemStack pStack, LivingEntity pShooter) {
        AbstractSillyArrow arrow = new SillyArrow(pLevel, pShooter);
        //arrow.setEffectsFromItem(pStack);
        return arrow;
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult){
        super.onHitEntity(pResult);
        var pos = pResult.getLocation();
        Hit(pos, 8, true);
    }

    @Override
    protected void onHit(HitResult pResult){
        super.onHit(pResult);
        var pos = pResult.getLocation();
        Hit(pos, 5.5f, Math.random() < 0.5);
    }

    private void Hit(Vec3 pos, float radius, boolean fire){
        float r = (float)Math.sqrt(this.getDeltaMovement().length() * radius) + 1.5f;
        level().explode(this, pos.x, pos.y, pos.z, r, fire, Level.ExplosionInteraction.TNT);
        this.discard();
    }

//    @Override
//    public void tick(){
//        super.tick();
//        Vec3 pos = this.position();
//        long time = level().getDayTime();
//        this.setPos(pos.x, pos.y + Math.sin(time) * 12, pos.z);
//    }
}
