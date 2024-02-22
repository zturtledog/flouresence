package com.confusedparrotfish.fluorescence.custom.tile;

import javax.annotation.Nullable;

import com.confusedparrotfish.fluorescence.itemregistry;
import com.confusedparrotfish.fluorescence.light;
import com.confusedparrotfish.fluorescence.tileregistry;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.client.model.data.EmptyModelData;
import net.minecraftforge.items.ItemStackHandler;

public class hidden_light extends light implements EntityBlock {
    public hidden_light(Properties props) {
        super(props);
    }

    @Override
    @Nullable
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return tileregistry.HIDDEN_LIGHT.get().create(pos, state);
    }

    @Override
    public RenderShape getRenderShape(BlockState shape) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    public static hidden_light build(Properties props, boolean lit, int defmode) {
        hidden_light temp = new hidden_light(props);
        temp.registerDefaultState(temp.defaultBlockState()
                .setValue(LIT, lit)
                .setValue(MODE, defmode));
        // temp.setupd((state, level, pos)->{
        // System.out.println();
        // });
        return temp;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
            BlockHitResult hit) {
        ItemStack itm = player.getItemInHand(hand);
        if (itm.getItem().getClass().isAssignableFrom(BlockItem.class)) {
            BlockState blk = ((BlockItem) itm.getItem()).getBlock().defaultBlockState();
            hidden_light_tile tle;
            if ((tle = ((hidden_light_tile) level.getBlockEntity(pos))) != null
                    && tle.item_handler.getStackInSlot(0).is(Items.AIR.asItem())
                    && !tle.item_handler.getStackInSlot(0).is(itemregistry.HIDDEN_LIGHT.get())) {
                ItemStack stk = itm.copy();
                stk.setCount(1);
                tle.item_handler.setStackInSlot(0, stk);

                if (!player.getAbilities().instabuild) {
                    itm.shrink(1);
                    player.setItemInHand(hand, itm);
                }
                return InteractionResult.CONSUME;
                // System.out.println(((hidden_light_tile)level.getBlockEntity(pos)).state);
            }
        }

        return super.use(state, level, pos, player, hand, hit);
    }

    @Override
    public void onRemove(BlockState pstate, Level level, BlockPos pos, BlockState nstate,
            boolean moving) {
        if (pstate.getBlock() != nstate.getBlock()) {
            BlockEntity ent = level.getBlockEntity(pos);
            if (ent instanceof hidden_light_tile) {
                ((hidden_light_tile) ent).drops();
            }
        }
        super.onRemove(pstate, level, pos, nstate, moving);
    }

    public static class hidden_light_tile extends BlockEntity {
        private final ItemStackHandler item_handler = new ItemStackHandler(1) {
            protected void onContentsChanged(int slot) {
                setChanged();
            };
        };
        // private LazyOptional<IItemHandler> lazy_item_handler = LazyOptional.empty();

        public hidden_light_tile(BlockPos p_155229_, BlockState p_155230_) {
            super(tileregistry.HIDDEN_LIGHT.get(), p_155229_, p_155230_);
        }

        @Override
        protected void saveAdditional(CompoundTag tag) {
            tag.put("stack", item_handler.serializeNBT());
            super.saveAdditional(tag);
            System.out.println("save: " + tag);
        }

        @Override
        public void load(CompoundTag tag) {
            super.load(tag);
            item_handler.deserializeNBT(tag.getCompound("stack"));
            System.out.println("load: " + tag);
        }

        public void drops() {
            SimpleContainer inv = new SimpleContainer(item_handler.getSlots());
            for (int i = 0; i < item_handler.getSlots(); i++) {
                inv.setItem(i, item_handler.getStackInSlot(i));
            }
            Containers.dropContents(level, worldPosition, inv);
        }
    }

    public static class hidden_light_renderer implements BlockEntityRenderer<hidden_light_tile> {
        private final BlockEntityRendererProvider.Context context;

        public hidden_light_renderer(BlockEntityRendererProvider.Context context) {
            this.context = context;
        }

        @Override
        public void render(hidden_light_tile entity, float partial_ticks, PoseStack stack, MultiBufferSource buffer,
                int combined_overlay, int packed_light) {
            final BlockRenderDispatcher dispatcher = this.context.getBlockRenderDispatcher();
            System.out.println(entity.item_handler.getStackInSlot(0));
            // System.out.println(entity.lazy_item_handler.getStackInSlot(0));
            // if (entity.lazy_item_handler.isPresent()) {
            // System.out.println("_"+entity.lazy_item_handler.resolve().get().getStackInSlot(0));
            // }

            ItemStack itm = entity.item_handler.getStackInSlot(0);
            if (itm.getItem().getClass().isAssignableFrom(BlockItem.class)) {
                dispatcher.renderSingleBlock(
                        ((BlockItem) entity.item_handler.getStackInSlot(0).getItem()).getBlock().defaultBlockState(),
                        stack, buffer, combined_overlay, packed_light,
                        EmptyModelData.INSTANCE);
            }
        }
    }
}

// @Override
// @Nonnull
// public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap,
// @Nullable Direction side) {
// if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
// return lazy_item_handler.cast();
// }

// return super.getCapability(cap, side);
// }

// @Override
// public void onLoad() {
// super.onLoad();
// lazy_item_handler = LazyOptional.of(() -> item_handler);
// }

// @Override
// public void invalidateCaps() {
// super.invalidateCaps();
// lazy_item_handler.invalidate();
// }