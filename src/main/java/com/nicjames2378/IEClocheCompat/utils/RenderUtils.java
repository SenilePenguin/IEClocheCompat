package com.nicjames2378.IEClocheCompat.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;


/*
Most things contained in this class are unused anywhere in this mod and will be removed in the future. It is simply a
repository of things I have learned that I think would be useful in future mod ideas. Please ignore them (or feel free
to use and learn from!) :-)
 */
public class RenderUtils {
    public enum FACING {
        WEST,
        NORTH,
        EAST,
        SOUTH,
        UP,
        DOWN
    }

    @SideOnly(Side.CLIENT)
    public static void renderAgri(BlockPos blockPos, ResourceLocation resourceLocation, Tessellator tessellator) {
        GlStateManager.pushMatrix();
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();

        float border = 0.25f;

//        renderSideDouble(FACING.WEST, border, blockPos, resourceLocation, tessellator);

        renderSideDouble(FACING.NORTH, 0.25f, blockPos, resourceLocation, tessellator);
        renderSideDouble(FACING.EAST, 0.25f, blockPos, resourceLocation, tessellator);
        renderSideDouble(FACING.NORTH, 0.75f, blockPos, resourceLocation, tessellator);
        renderSideDouble(FACING.EAST, 0.75f, blockPos, resourceLocation, tessellator);

        //GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    @SideOnly(Side.CLIENT)
    private static void renderSideDouble(FACING side, float offset, BlockPos blockPos, ResourceLocation resourceLocation, Tessellator tessellator) {
        FACING opposite;
        if (side==FACING.NORTH) { opposite = FACING.SOUTH; }
        else if (side==FACING.SOUTH) { opposite = FACING.NORTH; }
        else if (side==FACING.WEST) { opposite = FACING.EAST; }
        else if (side==FACING.EAST) { opposite = FACING.WEST; }
        else if (side==FACING.DOWN) { opposite = FACING.UP; }
        else { opposite = FACING.DOWN; }

        renderSideSingle(side, offset, blockPos, resourceLocation, tessellator);
        renderSideSingle(opposite, offset, blockPos, resourceLocation, tessellator);
//        renderSideSingle(asd.In, side, BORDER, blockPos, resourceLocation, tessellator);
//        renderSideSingle(asd.Out, side, BORDER, blockPos, resourceLocation, tessellator);
    }

    @SideOnly(Side.CLIENT)
    private static void renderSideSingle(FACING side, float offset, BlockPos blockPos, ResourceLocation resourceLocation, Tessellator tessellator) {
        //        final float PX = 1f / 16f;
//        final float YOFF = 1 * PX;
//        final float MAXHEIGHT = 10 * PX;
//        final float LOW = 5.9f * PX;
//        float actualHeight = (MAXHEIGHT * 3) + YOFF;
        Minecraft.getMinecraft().getTextureManager().bindTexture(resourceLocation);

        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.setTranslation(blockPos.getX(), blockPos.getY(), blockPos.getZ());

        float minX = 0;
        float minY = 0;
        float maxX = 1;
        float maxY = 1;

        float x1, x2, x3, x4;
        float y1, y2, y3, y4;
        float z1, z2, z3, z4;
        float u1, u2, u3, u4;
        float v1, v2, v3, v4;
        final int uv = 17;
        switch (side) {
            case UP: {
                x1 = x4 = maxX;
                x2 = x3 = minX;
                z1 = z2 = minY;
                z3 = z4 = maxY;
                y1 = y2 = y3 = y4 = offset;
                u2 = u3 = (minX % uv);
                u1 = u4 = (maxX % uv);
                v3 = v4 = maxY % uv;
                v1 = v2 = minY % uv;
                break;
            }
            case DOWN: {
                x1 = x2 = maxX;
                x3 = x4 = minX;
                z1 = z4 = minY;
                z2 = z3 = maxY;
                y1 = y2 = y3 = y4 = offset;
                u1 = u2 = maxX % uv;
                u3 = u4 = minX % uv;
                v1 = v4 = 16 - (minY % uv);
                v2 = v3 = 16 - (maxY % uv);
                break;
            }
            case WEST: {
                z1 = z2 = maxX;
                z3 = z4 = minX;
                y1 = y4 = minY;
                y2 = y3 = maxY;
                x1 = x2 = x3 = x4 = offset;
                u1 = u2 = maxX % uv;
                u3 = u4 = minX % uv;
                v1 = v4 = (16 - minY % uv);
                v2 = v3 = (16 - maxY % uv);
                break;
            }
            case EAST: {
                z1 = z2 = minX;
                z3 = z4 = maxX;
                y1 = y4 = minY;
                y2 = y3 = maxY;
                x1 = x2 = x3 = x4 = offset;
                u1 = u2 = (16 - minX % uv);
                u3 = u4 = (16 - maxX % uv);
                v1 = v4 = (16 - minY % uv);
                v2 = v3 = (16 - maxY % uv);
                break;
            }
            case NORTH: {
                x1 = x2 = maxX;
                x3 = x4 = minX;
                y1 = y4 = maxY;
                y2 = y3 = minY;
                z1 = z2 = z3 = z4 = offset;
                u1 = u2 = (16 - maxX % uv);
                u3 = u4 = (16 - minX % uv);
                v1 = v4 = (16 - maxY % uv);
                v2 = v3 = (16 - minY % uv);
                break;
            }
            case SOUTH: {
                x1 = x2 = maxX;
                x3 = x4 = minX;
                y1 = y4 = minY;
                y2 = y3 = maxY;
                z1 = z2 = z3 = z4 = offset;
                u1 = u2 = maxX % uv;
                u3 = u4 = minX % uv;
                v1 = v4 = (16 - minY % uv);
                v2 = v3 = (16 - maxY % uv);
                break;
            }
            default:
                return;
        }

        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferBuilder.pos(x1, y1, z1).tex(u1, v1).color(1f, 1f, 1f, 1f).endVertex();//1
        bufferBuilder.pos(x2, y2, z2).tex(u2, v2).color(1f, 1f, 1f, 1f).endVertex();//2
        bufferBuilder.pos(x3, y3, z3).tex(u3, v3).color(1f, 1f, 1f, 1f).endVertex();//3
        bufferBuilder.pos(x4, y4, z4).tex(u4, v4).color(1f, 1f, 1f, 1f).endVertex();//4

        //Labeled asd to the North
        //UVs measure from top-left(origin, y down). Blocks measure from top-back-left (AntiClockwise).
//        if (asd == asd.In) {
//            //LEFT face(in)
//            bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
//            bufferBuilder.pos(BORDER, 0, 0).tex(1, 1).color(1f, 1f, 1f, 1f).endVertex();//1
//            bufferBuilder.pos(BORDER, 1, 0).tex(1, 0).color(1f, 1f, 1f, 1f).endVertex();//2
//            bufferBuilder.pos(BORDER, 1, 1).tex(0, 0).color(1f, 1f, 1f, 1f).endVertex();//3
//            bufferBuilder.pos(BORDER, 0, 1).tex(0, 1).color(1f, 1f, 1f, 1f).endVertex();//4
//        } else if (asd == asd.Out) {
//            //LEFT face(out)
//            bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
//            bufferBuilder.pos(BORDER, 0, 0).tex(0, 1).color(1f, 1f, 1f, 1f).endVertex();//1
//            bufferBuilder.pos(BORDER, 0, 1).tex(1, 1).color(1f, 1f, 1f, 1f).endVertex();//4
//            bufferBuilder.pos(BORDER, 1, 1).tex(1, 0).color(1f, 1f, 1f, 1f).endVertex();//3
//            bufferBuilder.pos(BORDER, 1, 0).tex(0, 0).color(1f, 1f, 1f, 1f).endVertex();//2
//    }
        tessellator.draw();
    }

    @SideOnly(Side.CLIENT)
    private static void renderBlockAtPos(Tessellator tessellator, IBlockState stateToRender, BlockPos blockPos) {
        //NOTE: I am still learning about this. Comments may or may not accurately reflect what the code is actually
        //          doing, and you should look into it yourself before copying and assuming it works. This just gives a
        //          a place to start!

        //Unsure what this does, or if it is even needed.
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        //Copies item in top of matrix stack
        GlStateManager.pushMatrix();
        //Disables lighting from effecting next render. Commented out because it is unneeded in this example (afaik)
//        GlStateManager.disableLighting();

        //Learn how to do transparency and Alpha/Blending

        //Allows for building a render buffer that we can give back to the tessellator later
        BufferBuilder vertexbuffer = tessellator.getBuffer();

        //Begin a buffer that uses quads in the Block format
        vertexbuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        //if a hardcoded shift more than what is manually passed is desired, it can be acquired by offsetting blockPos
        //      or using the below line.
        //      WARNING: OFFSETTING USING THE GLSTATEMANAGER OFFSETS ALL ADDITIONAL CALLS PRECEEDING THE POPMATRIX!!
//        GlStateManager.translate(position.getX() + offsetAmount#f, position.getY() + offsetAmount#f, position.getZ() + offsetAmount#f);

        //Gets the thing that handles retrieving block renders?
        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        //Tells it to render using some parameters (no idea what the last one is for, so feel free to test. The commented part doesn't seem to break or do anything.
        blockrendererdispatcher.getBlockModelRenderer().renderModel(Minecraft.getMinecraft().world, blockrendererdispatcher.getModelForState(stateToRender), stateToRender, blockPos, vertexbuffer, false);//, MathHelper.getPositionRandom(position));
        //Makes the tessellator draw the changes. CRASHES OTHERWISE!
        tessellator.draw();

        //Turns lighting back on. Commented out because it is unneeded in this example (afaik)
//        GlStateManager.enableLighting();
        //Saves changes to matrix stack?
        GlStateManager.popMatrix();
    }

    @SideOnly(Side.CLIENT)
    private void renderWireFrame(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Tessellator tessellator, Color color) {
        BufferBuilder buffer = tessellator.getBuffer();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GL11.glTranslatef(minX, minY, minZ);

        int xSize = maxX - minX;
        int ySize = maxY - minY;
        int zSize = maxZ - minZ;

        int x = xSize;
        int y = ySize;
        int z = zSize;

        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
        int alpha = color.getAlpha();

        //x edges
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= x; i++) {
            buffer.pos(i, 0.001F, 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= x; i++) {
            buffer.pos(i, y - 0.001F, 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= x; i++) {
            buffer.pos(i, y - 0.001F, z - 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= x; i++) {
            buffer.pos(i, 0.001F, z - 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();

        //y edges
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= y; i++) {
            buffer.pos(0.001F, i, 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= y; i++) {
            buffer.pos(x - 0.001F, i, 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= y; i++) {
            buffer.pos(x - 0.001F, i, z - 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= y; i++) {
            buffer.pos(0.001F, i, z - 0.001F).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();

        //z edges
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= z; i++) {
            buffer.pos(0.001F, 0.001F, i).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= z; i++) {
            buffer.pos(x - 0.001F, 0.001F, i).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= z; i++) {
            buffer.pos(x - 0.001F, y - 0.001F, i).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();
        buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
        for (int i = 0; i <= z; i++) {
            buffer.pos(0.001F, y - 0.001F, i).color(red, green, blue, alpha).endVertex();
        }
        tessellator.draw();

        GL11.glTranslatef(-minX, -minY, -minZ);
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
    }


    @SideOnly(Side.CLIENT)
    private void renderTileEntityFast(double x, double y, double z, Tessellator tessellator) {//BufferBuilder vertexBuffer) {
        GlStateManager.pushMatrix();
        BufferBuilder vertexBuffer = tessellator.getBuffer();


        final float PX = 1f / 16f;
        final float YOFF = 1 * PX;
        final float BORDER = 1.9f * PX;
        final float MAXHEIGHT = 10 * PX;
        final float LOW = 5.9f * PX;

        float actualHeight = (MAXHEIGHT * 3) + YOFF;
        BlockModelShapes bm = Minecraft.getMinecraft().getBlockRendererDispatcher().getBlockModelShapes();
        TextureAtlasSprite still = bm.getTexture(Blocks.WATER.getDefaultState());

        vertexBuffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);


        vertexBuffer.setTranslation(x, y, z);

        //UP face
        vertexBuffer.pos(BORDER, actualHeight, BORDER).tex(still.getMinU(), still.getMinV()).color(1f, 1f, 1f, 0.8f).endVertex();
        vertexBuffer.pos(1 - BORDER, actualHeight, BORDER).tex(still.getMaxU(), still.getMinV()).color(1f, 1f, 1f, 0.8f).endVertex();
        vertexBuffer.pos(1 - BORDER, actualHeight, 1 - BORDER).tex(still.getMaxU(), still.getMaxV()).color(1f, 1f, 1f, 0.8f).endVertex();
        vertexBuffer.pos(BORDER, actualHeight, 1 - BORDER).tex(still.getMinU(), still.getMaxV()).color(1f, 1f, 1f, 0.8f).endVertex();

        tessellator.draw();
        GlStateManager.popMatrix();
    }

    @SideOnly(Side.CLIENT)
    private void renderBlock(Tessellator tessellator) {
        EntityPlayer player = Minecraft.getMinecraft().player;
        BlockPos position = new BlockPos(0, 0, 0);

        IBlockState stateToRender = Minecraft.getMinecraft().world.getBlockState(new BlockPos(player.posX, 1, player.posZ));
        Minecraft.getMinecraft().renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);

        GlStateManager.pushMatrix();
        //GlStateManager.disableLighting();
        //GlStateManager.enableAlpha();

        BufferBuilder bufferBuilder = tessellator.getBuffer();
        bufferBuilder.begin(GL11.GL_QUADS, DefaultVertexFormats.BLOCK);

        GlStateManager.translate(position.getX(), position.getY(), position.getZ());

        BlockRendererDispatcher blockrendererdispatcher = Minecraft.getMinecraft().getBlockRendererDispatcher();
        blockrendererdispatcher.getBlockModelRenderer().renderModel(Minecraft.getMinecraft().world, blockrendererdispatcher.getModelForState(stateToRender), stateToRender, position, bufferBuilder, false);
        tessellator.draw();

        //GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    //Never called; just shows how one would theoretically call methods in this class
    private void exampleUsagesOfMethods() {
        Tessellator tessellator = Tessellator.getInstance();
        EntityPlayer player = Minecraft.getMinecraft().player; //Only used for getting the block to render
        IBlockState stateToRender = Minecraft.getMinecraft().world.getBlockState(new BlockPos(player.posX, 1, player.posZ));
        //How far to move the render from it's parent object. Warning: render will 'vanish' if parent object is occluded.
        BlockPos blockPos = new BlockPos(0, 0, 0); //Is relative to position of calling object?

        renderBlockAtPos(tessellator, stateToRender, blockPos);

        renderWireFrame(0, 0, 0, 5, 5, 5, tessellator, new Color(128, 128, 128, 255));
    }
}
