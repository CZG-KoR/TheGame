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
    private String occupiedby;
    private int movement;

    // Position
    private int xPosition;
    private int yPosition;

    public Feld(Terrain t, int height, int xPosition, int yPosition) {
        this.t = t;
        this.height = height;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        occupied = false;
        occupiedby=null;
        movement=0;
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

    public boolean isOccupied() {
        return occupied;
    }

    public String getOccupiedby() {
        return occupiedby;
    }

    public void setOccupiedby(String occupiedby) {
        this.occupiedby = occupiedby;
    }

    //Testzwecke, muss eigentlich wieder entfernt werden
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
    
    

    public boolean isHighlighted() {
        return highlighted;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setT(String terrainName) {
        this.t = new Terrain(terrainName);
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }
    
    

}
