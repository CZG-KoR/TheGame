package map;

public class Feld {
    // Terrain, Beschaffen des Feldes
    private Terrain t;

    // Höhe des Feldes, (Flat, Hilly, Mountainous)
    private int height;

    // Besetzt?
    private boolean occupied;

    // möglich da hin zu bewegen, nachdem Figur angeklickt wurde
    private boolean highlighted;

    // Position
    private int xPosition;
    private int yPosition;

    public Feld(Terrain t, int height, int xPosition, int yPosition) {
        this.t = t;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        occupied = false;
    }

    public Terrain getT() {
        return t;
    }

    public String getTerrainName() {
        return t.getName();
    }

    public int getHeight() {
        return height;
    }

    //nur zu Testzwecken
    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setT(String terrainName) {
        this.t = new Terrain(terrainName);
    }

}
