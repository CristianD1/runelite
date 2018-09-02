package net.runelite.client.plugins.worldping;

import net.runelite.api.Point;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.cluescrolls.ClueScrollPlugin;
import net.runelite.client.ui.overlay.worldmap.WorldMapPoint;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PingWorldMapPoint extends WorldMapPoint
{

    private final worldpingPlugin plugin;
    private final BufferedImage clueScrollWorldImage;
    private final Point clueScrollWorldImagePoint;

    PingWorldMapPoint(final WorldPoint worldPoint, worldpingPlugin plugin)
    {
        super(worldPoint, null);

        clueScrollWorldImage = new BufferedImage(plugin.getMapArrow().getWidth(), plugin.getMapArrow().getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics graphics = clueScrollWorldImage.getGraphics();
        graphics.drawImage(plugin.getMapArrow(), 0, 0, null);
        graphics.drawImage(plugin.getMarkerImage(), 0, 0, null);
        clueScrollWorldImagePoint = new Point(
                clueScrollWorldImage.getWidth() / 2,
                clueScrollWorldImage.getHeight());

        this.plugin = plugin;
        this.setSnapToEdge(true);
        this.setJumpOnClick(true);
        this.setImage(clueScrollWorldImage);
        this.setImagePoint(clueScrollWorldImagePoint);
    }

    @Override
    public void onEdgeSnap()
    {
        this.setImage(plugin.getMarkerImage());
        this.setImagePoint(null);
    }

    @Override
    public void onEdgeUnsnap()
    {
        this.setImage(clueScrollWorldImage);
        this.setImagePoint(clueScrollWorldImagePoint);
    }

}
