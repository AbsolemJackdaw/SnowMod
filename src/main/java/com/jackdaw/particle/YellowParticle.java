package com.jackdaw.particle;

import com.jackdaw.network.NetworkHandler;
import com.jackdaw.network.server.SPacketParticleBlock;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.state.BlockState;

public class YellowParticle extends TextureSheetParticle {

    public YellowParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
        super(level, x, y, z, dx, dy, dz);
        this.setColor(1.0f, 240.0f / 255.0f, 128.0f / 255.0f);
        this.friction = 0.7F;
        this.gravity = 0.5F;
        this.xd *= 0.1F;
        this.yd *= 0.1F;
        this.zd *= 0.1F;
        this.xd += dx * 0.4D;
        this.yd += dy * 0.4D;
        this.zd += dz * 0.4D;
        this.quadSize *= 0.5F;
        this.lifetime = 50;
        this.hasPhysics = true;


    }

    @Override
    public void tick() {
        super.tick();
        if (this.age < 50 && this.random.nextInt(500) == 0 && this.onGround) {

            BlockState state = this.level.getBlockState(new BlockPos(x, y, z));
            BlockState bellow = this.level.getBlockState(new BlockPos(x, y, z).below());

            if (state.is(BlockTags.SNOW) || state.is(BlockTags.CAMPFIRES) || state.is(BlockTags.FIRE)) {
                NetworkHandler.NETWORK.sendToServer(new SPacketParticleBlock(new BlockPos(x, y, z)));
            } else if (bellow.is(BlockTags.SNOW) || bellow.is(BlockTags.ICE)) {
                NetworkHandler.NETWORK.sendToServer(new SPacketParticleBlock(new BlockPos(x, y, z).below()));
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Factory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet set;

        public Factory(SpriteSet set) {
            this.set = set;
        }

        @Override
        public Particle createParticle(SimpleParticleType typeIn, ClientLevel level, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            YellowParticle particle = new YellowParticle(level, x, y, z, xSpeed, ySpeed, zSpeed);
            particle.pickSprite(set);
            return particle;
        }
    }
}
