package com.nicjames2378.IEClocheCompat.utils;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import java.awt.*;


/*
Everything contained in this class is unused anywhere in this mod and will be removed in the future. It is simply a
repository of things I have learned that I think would be useful in future mod ideas. Please ignore them (or feel free
to use and learn from!) :-)
 */
public class UNUSED {
    @SideOnly(Side.CLIENT)
    public static void renderBlockAtPos(Tessellator tessellator, IBlockState stateToRender, BlockPos blockPos) {
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
    public void renderWireFrame(int minX, int minY, int minZ, int maxX, int maxY, int maxZ, Tessellator tessellator, Color color) {
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
            for(int i = 0; i <= x; i++) {
                buffer.pos(i, 0.001F, 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= x; i++) {
                buffer.pos(i, y - 0.001F, 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= x; i++) {
                buffer.pos(i, y - 0.001F, z - 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= x; i++) {
                buffer.pos(i, 0.001F, z - 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();

            //y edges
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= y; i++) {
                buffer.pos(0.001F, i, 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= y; i++) {
                buffer.pos(x - 0.001F, i, 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= y; i++) {
                buffer.pos(x - 0.001F, i, z - 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= y; i++) {
                buffer.pos(0.001F, i, z - 0.001F).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();

            //z edges
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= z; i++) {
                buffer.pos(0.001F, 0.001F, i).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= z; i++) {
                buffer.pos(x - 0.001F, 0.001F, i).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= z; i++) {
                buffer.pos(x - 0.001F, y - 0.001F, i).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();
            buffer.begin(3, DefaultVertexFormats.POSITION_COLOR);
            for(int i = 0; i <= z; i++) {
                buffer.pos(0.001F, y - 0.001F, i).color(red, green, blue, alpha).endVertex();
            }
            tessellator.draw();

            GL11.glTranslatef(-minX, -minY, -minZ);
            GlStateManager.enableLighting();
            GlStateManager.enableTexture2D();
    }


    @SideOnly(Side.CLIENT)
    public void renderTileEntityFast(double x, double y, double z, Tessellator tessellator) {//BufferBuilder vertexBuffer) {
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
    public void renderBlock(Tessellator tessellator) {
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
    public void exampleUsagesOfMethods() {
        Tessellator tessellator = Tessellator.getInstance();
        EntityPlayer player = Minecraft.getMinecraft().player; //Only used for getting the block to render
        IBlockState stateToRender = Minecraft.getMinecraft().world.getBlockState(new BlockPos(player.posX, 1, player.posZ));
        //How far to move the render from it's parent object. Warning: render will 'vanish' if parent object is occluded.
        BlockPos blockPos = new BlockPos(0, 0, 0); //Is relative to position of calling object?

        renderBlockAtPos(tessellator, stateToRender, blockPos);

        renderWireFrame(0, 0, 0, 5, 5, 5, tessellator, new Color(128, 128, 128, 255));
    }
}
