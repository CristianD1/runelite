package net.runelite.client.plugins.worldping;

import net.runelite.api.ItemID;
import net.runelite.api.coords.WorldPoint;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.screenmarkers.ScreenMarkerCreationOverlay;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.util.ImageUtil;
import net.runelite.client.ui.overlay.worldmap.WorldMapPointManager;
import net.runelite.client.game.ItemManager;

import javax.inject.Inject;
import java.awt.image.BufferedImage;

@PluginDescriptor(
        name = "World Ping",
        description = "Ping your location on other people's minimap.",
        tags = {"world","ping","location"}
)
public class worldpingPlugin extends Plugin {

    private BufferedImage mapArrow;

    private boolean worldMapPointsSet = false;

    private WorldMapPointManager worldMapPointManager;

    private ItemManager itemManager;

    private OverlayManager overlayManager;

    @Inject
    private ScreenMarkerCreationOverlay overlay;

    @Inject
    private PingWorldmapOverlay pingWorldmapOverlay;

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(pingWorldmapOverlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(pingWorldmapOverlay);
    }

    BufferedImage getMapArrow()
    {
        if (mapArrow != null)
        {
            return mapArrow;
        }

        mapArrow = ImageUtil.getResourceStreamFromClass(getClass(), "/util/clue_arrow.png");

        return mapArrow;
    }

    BufferedImage getMarkerImage()
    {
        return itemManager.getImage(ItemID.CLUE_SCROLL_MASTER);
    }

    private void addMapPoints(WorldPoint... points)
    {
        if (worldMapPointsSet)
        {
            return;
        }

        worldMapPointsSet = true;
        worldMapPointManager.removeIf(PingWorldMapPoint.class::isInstance);

        for (final WorldPoint point : points)
        {
            worldMapPointManager.add(new PingWorldMapPoint(point, this));
        }
    }

}
