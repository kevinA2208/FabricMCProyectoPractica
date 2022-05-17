package net.nexus.primermod.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.nexus.primermod.PrimerMod;

public class MythrilBlasterScreen extends HandledScreen<MythrilBlasterScreenHandler> {


    //Se agrega la textura del gui screen
    private static final Identifier TEXTURE =
            new Identifier(PrimerMod.MOD_ID, "textures/gui/mythril_blaster_gui.png");

    public MythrilBlasterScreen(MythrilBlasterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    //Boilerplate, se agrega la configuración del gui, se agrega la textura creada al inicio y se pinta el gui
    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        //SE DIBUJAN LAS TEXTURAS DE LAS BARRAS DE PROGRESO DE FUEL Y CRAFTING

        //Se revisa si está crafteando algo en el bloque
        if(handler.isCrafting()) {
            //si es así, que se ubique en las coordenadas 84 x, 22 y, que son las coordenadas donde se encuentra la flecha vacia de progreso
            //en el gui del bloque, y que tome la textura que se encuentra en las coordenadas 176 y 14, y que está textura que tomó, la vaya pintando o dibujando
            //segun el progreso que se tiene del handler, asi hasta que se pinte toda la flecha, que seria al llegar al maxProgress
            drawTexture(matrices, x + 84, y + 22, 176, 14, handler.getScaledProgress(), 36);
        }

        //Esa tambien dibuja la barra de combustible en el inventario del bloque, pero ya está totalmente pintada con la textura
        //solo que se va quitando esta textura de arriba hacia abajo segun el fuelProgress que se le da el handler
        //cuando el fuelProgress llegue a 0 se quita la textura de fuego
        if(handler.hasFuel()) {
            drawTexture(matrices, x + 18, y + 33 + 14 - handler.getScaledFuelProgress(), 176,
                    14 - handler.getScaledFuelProgress(), 14, handler.getScaledFuelProgress());
        }
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
        renderBackground(matrices);
        super.render(matrices, mouseX, mouseY, delta);
        drawMouseoverTooltip(matrices, mouseX, mouseY);
    }
}
