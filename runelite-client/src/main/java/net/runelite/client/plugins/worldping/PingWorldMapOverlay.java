package net.runelite.client.plugins.worldping;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import javax.inject.Inject;
import net.runelite.client.plugins.cluescrolls.clues.ClueScroll;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class PingWorldmapOverlay extends Overlay
{
    public static final int IMAGE_Z_OFFSET = 30;

    public static final Color CLICKBOX_BORDER_COLOR = Color.ORANGE;
    public static final Color CLICKBOX_HOVER_BORDER_COLOR = CLICKBOX_BORDER_COLOR.darker();
    public static final Color CLICKBOX_FILL_COLOR = new Color(0, 255, 0, 20);

    private final worldpingPlugin plugin;

    @Inject
    public PingWorldmapOverlay(worldpingPlugin plugin)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        this.plugin = plugin;
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        ClueScroll clue = plugin.getClue();

        if (clue != null)
        {
            clue.makeWorldOverlayHint(graphics, plugin);
        }

        return null;
    }
}
