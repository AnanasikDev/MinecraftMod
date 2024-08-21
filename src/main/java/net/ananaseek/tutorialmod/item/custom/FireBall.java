package net.ananaseek.tutorialmod.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

public class FireBall extends ThrowableItemProjectile {

    private int lives = 0;
    private int maxLives = 3;

    public FireBall(EntityType<? extends FireBall> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public FireBall(Level pLevel, LivingEntity pShooter) {
        super(EntityType.SNOWBALL, pShooter, pLevel);
    }

    public FireBall(Level pLevel, double pX, double pY, double pZ) {
        super(EntityType.SNOWBALL, pX, pY, pZ, pLevel);
    }

    protected Item getDefaultItem() {
        return Items.SNOWBALL;
    }

    private ParticleOptions getParticle() {
        ItemStack itemstack = this.getItemRaw();
        return (ParticleOptions)(itemstack.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemParticleOption(ParticleTypes.ITEM, itemstack));
    }

    /**
     * Handles an entity event received from a {@link net.minecraft.network.protocol.game.ClientboundEntityEventPacket}.
     */
    public void handleEntityEvent(byte pId) {
        if (pId == 3) {
            ParticleOptions particleoptions = this.getParticle();

            for(int i = 0; i < 8; ++i) {
                this.level().addParticle(particleoptions, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
            }
        }

    }

    /**
     * Called when the arrow hits an entity
     */
    protected void onHitEntity(EntityHitResult pResult) {
        super.onHitEntity(pResult);
        Entity entity = pResult.getEntity();
        int i = entity instanceof Blaze ? 3 : 0;
        entity.hurt(this.damageSources().thrown(this, this.getOwner()), (float)i);
    }

    /**
     * Called when this EntityFireball hits a block or entity.
     */
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        var pos = pResult.getLocation();
        if (lives < maxLives){
            if (true){ //!this.level().isClientSide
                this.level().broadcastEntityEvent(this, (byte)3);
                Vec3 delta = this.getDeltaMovement();
                float factor = 0.75f;

                this.shoot(delta.x, -delta.y, delta.z, (float)delta.length() * factor, 0.7f);
                lives++;
                this.level().explode(this, pos.x, pos.y, pos.z, 1.6F, false, Level.ExplosionInteraction.TNT);
            }
            else{
                this.level().addParticle(ParticleTypes.LAVA, pos.x, pos.y, pos.z, 1, 1,1);
            }
        }
        else{
            if (true) //!this.level().isClientSide
            {
                this.discard();

                this.level().explode(this, pos.x, pos.y, pos.z, 2.7F, true, Level.ExplosionInteraction.TNT);

                // adding lava & magma

                int halfwidth = 4;

                for (int x = -halfwidth; x < halfwidth; x++){
                    for (int y = -halfwidth; y < halfwidth; y++){
                        for (int z = -halfwidth; z < halfwidth; z++)
                        {
                            BlockPos blockPos = new BlockPos((int)pos.x + x, (int)pos.y + y, (int)pos.z + z);

                            if (level().getBlockState(blockPos).isAir()) continue;

                            BlockState blockState = Math.random() < 0.6 ? Blocks.MAGMA_BLOCK.defaultBlockState() : Blocks.LAVA.defaultBlockState();

                            level().setBlockAndUpdate(blockPos, blockState);
                            this.level().addParticle(ParticleTypes.LAVA, pos.x, pos.y + 1, pos.z, 1, 1,1);
                        }
                    }
                }
            }

        }
    }
}